package winwin.alwn.xsbnhnh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.dakulangsakalam.customwebview.presentation.ui.jump.JumpActivity
import com.dakulangsakalam.customwebview.presentation.ui.jump.JumpType
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class SplashActivity : JumpActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startJump(false)
    }

    private fun onNextActivity(){
        Handler(Looper.getMainLooper()).postDelayed(
            {
                startActivity(MainActivity.getStartIntent(this))
                finish()
            },1500
        )
    }

    private fun startJump(boolean: Boolean){
        if(boolean)
            splashAction(JumpType.JUMP_LINK, 3){_,_ ->
                onNextActivity()
            }
        else onNextActivity()
    }
}