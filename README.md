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
<h5>Use <code>.show()</code> to start showing snackbar </h5>
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
