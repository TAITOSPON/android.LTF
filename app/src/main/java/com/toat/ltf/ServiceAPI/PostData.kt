package com.toat.ltf.ServiceAPI

import android.os.AsyncTask
import android.util.Log
import com.google.gson.Gson
import com.toat.ltf.Model.UserLoginModel
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.IOException
import java.util.concurrent.TimeUnit


class PostData(private var str_url: String?,
               private var str_json :String?,
               private var callBackPostData: CallBackPostData?) : AsyncTask<String, String, Boolean>() {

    private val TAG = GetData::class.java.simpleName
    private var str_response: String? = null
    private var IOException_message: String? = null


    init {
        this.execute()
    }

    interface CallBackPostData {
        fun OnSuccess(str_data: String?)
        fun OnErrorIOException(IOException_message: String?)
        fun OnError()
    }

    override fun doInBackground(vararg strings: String?): Boolean? {
        try {

            val client = OkHttpClient().newBuilder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .protocols(listOf(Protocol.HTTP_1_1))
                    .build()

            val MediaTypeJSON = "application/json; charset=utf-8".toMediaTypeOrNull()
            val body: RequestBody = RequestBody.create(MediaTypeJSON, this.str_json!!)
            val request: Request = Request.Builder()
                    .url(this.str_url!!)
                    .post(body)
                    .build()

            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                str_response = response.body!!.string()
                return true
            }
        } catch (e: IOException) {
            Log.d(TAG, "Exception $e")
            IOException_message = e.message
            return false
        }
        return false
    }

    override fun onPostExecute(aBoolean: Boolean) {
        super.onPostExecute(aBoolean)
        if (aBoolean) {
            if (str_response != null) {
                callBackPostData!!.OnSuccess(str_response)
            } else {
                callBackPostData!!.OnError()
            }
        } else {
            if (IOException_message != null) {
                callBackPostData!!.OnErrorIOException(IOException_message)
            } else {
                callBackPostData!!.OnError()
            }
        }
    }
}