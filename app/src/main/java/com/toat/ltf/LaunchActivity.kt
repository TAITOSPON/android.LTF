package com.toat.ltf

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.toat.ltf.Tool.CheckPermission
import com.toat.ltf.Tool.SetColorStatusBar
import kotlinx.android.synthetic.main.activity_launch.*

class LaunchActivity: AppCompatActivity() {

    private var TAG : String = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        // todo set color status bar //
        SetColorStatusBar().setStatusBarTransparent(this@LaunchActivity)

        this.Checkpermission()
        img_logo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_show))
    }


    private fun Start(){
        Handler().postDelayed({
            val intent = Intent(this@LaunchActivity, LoginActivity::class.java)
            this@LaunchActivity.startActivity(intent)
            overridePendingTransition(R.anim.fadein, R.anim.fadeout)
            finish()
        }, 1000)
    }


    private fun Checkpermission(){

        // TODO Check Permission //
        CheckPermission(this, object : CheckPermission.CheckPermissionCallback {
            override fun OnSuccessCallback() {
                Log.d(TAG, "CheckPermission OnSuccessCallback")

                this@LaunchActivity.Start()
            }

            override fun OnErrorCallback() {
                Log.d(TAG, "CheckPermission OnErrorCallback")

                Handler().postDelayed({
                    this@LaunchActivity.Checkpermission()
                }, 500)
            }

        }).requestCameraPermission()
    }
}