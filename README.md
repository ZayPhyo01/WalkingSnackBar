# WalkingSnackBar

<p><strong>WalkingSnackbar</strong> is a small library to help you implement own snackbar layout with custom position and animation in easily.</p>

<h1>Screenshot</h1>
<img src="https://github.com/ZayPhyo01/WalkingSnackBar/blob/main/app/image/home.png" width="200" height = "300" title="hover text">

<h1>Usage</h1>
<p>‼️ Current version <b>0.0.1</b> support only message text for default snackbar layout, if you need custom layout , you just need to implement Decorator interface</p>

<h1>Initialisation</h1>

<code> WalkingSnackbar.make(view , message , decorator)</code>

<h5>It need to set view or view group in first parameter.</h5>


<h5>For eg .  <code> WalkingSnackbar.make(binding.rootLayout,"hello message").show()</code> </h5>

<h5>For duration <code>.setDuration(10000)</code> </h5>

<h3>This is just a simple default snackbar usage.</h3>

<h1>Custom layout Usage</h1>

<p>You need to add Decorator interface in third parameter of <code>.make(view , message , decorator)</code></p>
<h3>Example usage</h3>
  <pre><div>object : WalkingSnackbar.Decorator {
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
  </div></pre>
  
  
  <h5><code>contentIn(view: View)</code> will called when you start <code>.show()</code> This method is to add animation when snackbar start</h5>
<h5>Use <code>.show()</code> to start showing snackbar </h5>

<h5><code>withCustomLayout(inflater: LayoutInflater,containerView: ViewGroup): View?</code> to add custom layout binding , default snackbar will use when return null</h5>

<p>For first parameter <b>view<b> </p>
  
  <h3>Usage of view parameter</h3>
  
  <p>When you add one of view in your layout hierarchy , <b>(not WalkingSnackbarContainer view)</b> then it will search up to suitable parent view<br>
    <i>Default searching behaviour of snackbar)</i>
</p>
  <p>‼️ But... when you add <code>WalkingSnackbarContainer</code> view , then it will use it as parent to customize your viewgroup position and measurement .</p>
  
  <h1>Note..</h1>
  
  <p>When you add <code>WalkingSnackbarContainer</code> view in your layout hierarchy
  and you add another parent or sibling view in first parameter , then it will search <code>WalkingSnackbarContainer</code> in your layout first,
    and it will only use default suitale view when no <code>WalkingSnackbarContainer</code> in your layout
  </p>
  
  <h1>Suggestion..</h1>
  
  <p>if you have <code>WalkingSnackbarContainer</code> view in your layout hierarchy , then directly add this view in parameter
    because it will take parent searching with complexity <h3>O(1)</h3>.
  </p>
  
  <img src = "https://github.com/ZayPhyo01/WalkingSnackBar/blob/main/app/image/image1.png" width = "400" height = "300"/>
    </br>
  <strong>With direct add <code>WalkingSnackbarContainer</code> container view </strong>
   </br> </br> </br>
  
  <p>Otherwise it will search  <code>WalkingSnackbarContainer</code> by using <code>Depth First Search Algorithm</code> before searching suitable view as default snackbar<h3> and as we known that with complexity O(V + E)</h3>.
  </p>
 
  
