package com.toat.ltf.Model


import com.google.gson.annotations.SerializedName

data class UserLoginModel(
    @SerializedName("user_code")
    val userCode: String,
    @SerializedName("user_pass")
    val userPass: String
)