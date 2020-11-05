package com.toat.ltf.Model


import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("de_name")
    val deName: String,
    @SerializedName("dept_code")
    val deptCode: String,
    @SerializedName("dept_name")
    val deptName: String,
    @SerializedName("user_code")
    val userCode: String,
    @SerializedName("user_fname")
    val userFname: String,
    @SerializedName("user_lname")
    val userLname: String
)