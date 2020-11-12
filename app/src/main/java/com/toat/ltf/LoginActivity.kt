package com.toat.ltf

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.toat.ltf.Model.UserLoginModel
import com.toat.ltf.ServiceAPI.GetData
import com.toat.ltf.ServiceAPI.PostData
import com.toat.ltf.User.Main.UserMainActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private var TAG : String = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.OnClick()


    }
    private fun get(){

        val url= "http://192.168.137.1:5000/testget"
//        val url ="https://webhook.toat.co.th/linebot/web/index.php/api/Api_User/list_log_login"
        GetData(url, object : GetData.CallBackPostData{
            override fun OnSuccess(str_data: String?) {
                Log.d(TAG ,"OnSuccess + $str_data")
            }

            override fun OnErrorIOException(IOException_message: String?) {
                Log.d(TAG ,"OnErrorIOException ")
            }

            override fun OnError() {
                Log.d(TAG ,"OnError ")
            }

        })
    }


    private fun post(){
        val url = "https://webhook.toat.co.th/linebot/web/index.php/api/Api_User/User_test"
        val json = Gson().toJson(UserLoginModel("002892", "002892"))
        Log.w(TAG, "json = $json")

        PostData(url,json, object : PostData.CallBackPostData{
            override fun OnSuccess(str_data: String?) {
                Log.d(TAG ,"OnSuccess + $str_data")
            }

            override fun OnErrorIOException(IOException_message: String?) {
                Log.d(TAG ,"OnErrorIOException ")
            }

            override fun OnError() {
                Log.d(TAG ,"OnError ")
            }

        })


    }

    private fun OnClick(){
        this.bt_login_login.setOnClickListener {
            this.Goto_MainActivity()
        }

//        this.bt_login_login.setOnClickListener{
//            this.post()
//        }

        this.bt_login_login2.setOnClickListener{
            this.get()
        }
    }

    private fun Goto_MainActivity(){
        val intent = Intent(this@LoginActivity, UserMainActivity::class.java)
        this@LoginActivity.startActivity(intent)
        finish()

    }


}