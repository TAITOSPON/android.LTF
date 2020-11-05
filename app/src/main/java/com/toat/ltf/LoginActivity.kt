package com.toat.ltf

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.toat.ltf.User.UserMainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.OnClick()
    }

    private fun OnClick(){
        this.bt_login_login.setOnClickListener {
            this.Goto_MainActivity()
        }
    }

    private fun Goto_MainActivity(){
        val intent = Intent(this@LoginActivity, UserMainActivity::class.java)
        this@LoginActivity.startActivity(intent)
        finish()

    }


}