package com.toat.ltf

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.toat.ltf.Model.UserLoginModel
import com.toat.ltf.ServiceAPI.ConfigPathAPI
import com.toat.ltf.ServiceAPI.PostDataWithParam
import com.toat.ltf.User.UserMainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var TAG : String = this.javaClass.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.OnClick()

//        this.post()

    }

    private fun post(){

        val json = Gson().toJson(UserLoginModel("002892","002892"))

        Log.w(TAG,"json = $json")

        PostDataWithParam(this, ConfigPathAPI.User_Login,json,object : PostDataWithParam.CallBackGetData{

            override fun OnSuccess(str_data: String) {

                Log.d(TAG ,"OnSuccess + $str_data")

            }

            override fun OnError() {
                Log.d(TAG ,"ON ERROR")
            }
        })
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