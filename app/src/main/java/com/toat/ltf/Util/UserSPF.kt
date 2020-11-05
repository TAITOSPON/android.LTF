package com.toat.ltf.Util

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.toat.ltf.Model.UserModel

class UserSPF {

    private var PRIVATE_MODE = 0
    private var mycontext:Context? = null
    private var sharedPreferences: SharedPreferences? = null
    private var editor:SharedPreferences.Editor?      = null


    companion object{

        val UserSPF_Name = "user_spf"

        val user_code = "user_code"
        val user_fname = "user_fname"
        val user_lname  = "user_lname"
        val dept_code = "dept_code"
        val de_name = "de_name"
        val dept_name = "dept_name"



    }

    @SuppressLint("CommitPrefEdits")
    constructor(context: Context?, user_spf_name:String?){

        this.mycontext = context
        this.sharedPreferences = mycontext?.getSharedPreferences(user_spf_name,PRIVATE_MODE)
        this.editor = this.sharedPreferences?.edit()

    }

    fun SetUserLogin( userModel: UserModel){

         this.editor?.let {
             it.putString(user_code,userModel.userCode)
//             it.putString(user_fname,listUserModel[0].userEmail)
//             it.putString(user_lname,listUserModel[0].userName)
//             it.putString(dept_code,listUserModel[0].userPhoneNumber)
//             it.putString(de_name,listUserModel[0].userPhoneNumber)
//             it.putString(dept_name,listUserModel[0].userPhoneNumber)
             it.commit()
         }
    }

    fun GetUserLogin(str_key:String?):String{

        this.sharedPreferences?.let {

            return it.getString(str_key,"")!!

        }

        return ""
    }

    fun ClearUserLogin() {
        editor!!.clear()
        editor!!.commit()
        Log.e("TAG", "Logout Member")
    }
}