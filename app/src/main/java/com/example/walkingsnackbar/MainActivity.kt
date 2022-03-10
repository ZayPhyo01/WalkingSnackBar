package com.example.walkingsnackbar

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.walkingsnackbar.databinding.ActivityMainBinding
import com.example.walkingsnackbar.databinding.CustomSnackBarBinding
import com.me.walkingsnackbar.WalkingSnackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnBar.setOnClickListener {
            WalkingSnackbar.make(
                binding.rootLayout,
                "hello message",
                decorator = object : WalkingSnackbar.Decorator {
                    override fun contentIn(view: View) {
                        ObjectAnimator.ofFloat(
                            view, View.TRANSLATION_X, -500f, 0f
                        ).start()
                    }

                    override fun withCustomLayout(
                        inflater: LayoutInflater,
                        containerView: ViewGroup
                    ): View? {
                        val binding = CustomSnackBarBinding.inflate(
                            inflater, containerView, false
                        )
                        binding.tv.text = "Oh my god custom message"
                        return binding.root
                    }
                }
            ).setDuration(100000).show()
        }
    }
}