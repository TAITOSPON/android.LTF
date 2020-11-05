package com.toat.ltf.User.Turkish

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.toat.ltf.R
import kotlinx.android.synthetic.main.activity_turkishgrade.*
import kotlinx.android.synthetic.main.toolbar_main.*

class TurkishGradeActivity : AppCompatActivity() {

    private var TAG : String = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_turkishgrade)

        this.SetToolbar()

        this.OnClick()
    }

    private fun SetToolbar(){
        text_toolbar.text = this.getString(R.string.title_activity_TurkishGrade)

        this.setSupportActionBar(toolbar_head_main)
        if(this.supportActionBar != null){
            this.supportActionBar!!.title = getString(R.string.string_empty)
            this.supportActionBar!!.setHomeAsUpIndicator(R.drawable.icon_back_white)
            this.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

    }

    private fun OnClick(){

        this.toolbar_head_main.setNavigationOnClickListener {
            this.onBackPressed()
        }

        bt_goto_qr_code.setOnClickListener {
            this.Goto_SacnnerActivity()
        }
    }

    private fun Goto_SacnnerActivity(){
        val intent = Intent(this@TurkishGradeActivity, TurkishQRCodeActivity::class.java)
        this@TurkishGradeActivity.startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }
}