package com.me.walkingsnackbar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.forEach
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.me.walkingsnackbar.databinding.DefaultSnackBarBinding
import java.util.*

class WalkingSnackbar private constructor(
      parent: ViewGroup,
      viewChild: View,
) : BaseTransientBottomBar<WalkingSnackbar>(
    parent,
    viewChild,
    object : com.google.android.material.snackbar.ContentViewCallback {
        override fun animateContentIn(delay: Int, duration: Int) {

        }

        override fun animateContentOut(delay: Int, duration: Int) {

        }
    }) {
    var snackBarContainerView = getView()
        private set

    init {
        snackBarContainerView.setPadding(0, 0, 0, 0)
    }

    interface Decorator {
        fun contentIn(view: View)
        fun withCustomLayout(inflater: LayoutInflater, containerView: ViewGroup): View?
    }

    companion object {
        private var decoratorGlobal: Decorator? = null
        fun make(
            containerView: View,
            message: String,
            decorator: Decorator? = null
        ): WalkingSnackbar {
            val container = findSuitableParentView(containerView) ?: throw IllegalArgumentException(
                "No suitable parent found"
            )
            decoratorGlobal = decorator
            return if (decorator?.withCustomLayout(
                    LayoutInflater.from(container.context),
                    container
                ) != null
            ) {

                WalkingSnackbar(
                    container,
                    decorator.withCustomLayout(LayoutInflater.from(container.context), container)!!
                )

            } else {
                val binding = setUpDefaultViewWhenDecoratorIsNull(container, message)
                WalkingSnackbar(container, binding.root)
            }
        }

        private fun setUpDefaultViewWhenDecoratorIsNull(
            containerView: ViewGroup,
            message: String
        ): DefaultSnackBarBinding {

            val binding = DefaultSnackBarBinding.inflate(
                LayoutInflater.from(
                    containerView.context
                ),
                containerView, false
            )
            binding.tvMessage.text = message
            binding.root.initView(binding.root)
            return binding
        }

        private fun transverseWithDFS(v: View?): WalkingSnackbarContainer? {
            val stack = Stack<View>()
            val mapId = HashMap<Int?, View?>()
            stack.push(v)
            mapId[v?.id] = v
            while (stack.isNotEmpty()) {
                val currentNode = stack.pop()

                if (currentNode is WalkingSnackbarContainer) {
                    return currentNode
                }

                val upNode = currentNode.parent
                //check up node is visit
                if (upNode is ViewGroup && mapId[upNode.id] == null) {
                    stack.push(upNode)
                    mapId[upNode.id] = upNode
                }
                //down parent node will add to stack
                //child view node will be leaf node
                if (currentNode is ViewGroup) {
                    currentNode.forEach {
                        //check is visited node
                        if (mapId[it.id] == null) {
                            stack.push(it)
                            mapId[it.id] = it
                        }
                    }
                }
            }


            return null
        }

        private fun findSuitableParentView(v: View?): ViewGroup? {
            if (v is WalkingSnackbarContainer) {
                //O(1)
                return v
            } else {
                //level order transverse O(E + V)
                var view = v
                val libSpecificView = transverseWithDFS(v)
                if (libSpecificView != null) {
                    return libSpecificView
                } else {
                    //default search parent view
                    var fallback: ViewGroup? = null
                    do {
                        if (view is CoordinatorLayout) {
                            return view
                        } else if (view is FrameLayout) {
                            if (view.id == android.R.id.content) {

                                return view
                            } else {

                                fallback = view
                            }
                        }

                        if (view != null) {
                            val parent = view.parent
                            view = if (parent is View) parent else null
                        }
                    } while (view != null)
                    return fallback
                }
            }
        }
    }


    override fun show() {
        decoratorGlobal?.contentIn(getView())
        super.show()
    }
}