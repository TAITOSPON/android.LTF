package com.toat.ltf.ServiceAPI

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.util.Log

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.toat.ltf.Tool.CheckInternet
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONException
import org.json.JSONObject
import java.util.concurrent.TimeUnit


@SuppressLint("LongLogTag")

class PostDataWithParam:AsyncTask<String,Int,Boolean> {

    private val TAG = this.javaClass.simpleName
    @SuppressLint("StaticFieldLeak")
    private var mycontext:Context? = null
    private var str_response:String? = null
    private var str_url:String?  = null
    private var str_json:String? = null
    private var callBackGetData: CallBackGetData? = null
    private val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()

    private val internet = CheckInternet()

//    private var gettokenModel: GettokenModel? = null

    constructor(context:Context?,str_url:String?,str_json:String?,callBackGetData: CallBackGetData?){
        this.mycontext = context
        this.str_url  = str_url
        this.str_json = str_json
        this.callBackGetData = callBackGetData

       if (!internet.isInternetAccessible(this.mycontext)) run {
           internet.noInternet(this.mycontext)
       } else {



           executeOnExecutor(THREAD_POOL_EXECUTOR)

//           this.execute()
       }
    }

    interface CallBackGetData{

        fun OnSuccess(str_data:String)

        fun OnError()

    }

    override fun onPreExecute() {
        super.onPreExecute()

        Log.d(TAG,"onPreExecute")
    }

    override fun doInBackground(vararg parame:String): Boolean {

        try {

            val multipartBody = MultipartBody.Builder()
            multipartBody.setType(MultipartBody.FORM)

            // TODO Add Data Param //
            val jsonObject = JSONObject(str_json)
            try {
                val temp = jsonObject.keys()
                while (temp.hasNext()) {
                    val key = temp.next()
                    val value = jsonObject.get(key)
                    multipartBody.addFormDataPart(key.toString(),value.toString())
                }
//                multipartBody.addFormDataPart(gettokenModel!!.name, gettokenModel!!.key)

            } catch (e: JSONException) {
                e.printStackTrace()
            }

            Log.d(TAG,"multipartBody = $multipartBody")
//            val body = RequestBody.create(JSON,str_json)
            val request = Request.Builder()
                .post(multipartBody.build())
//                .addHeader("Cookie", gettokenModel!!.cookie_name + "=" + gettokenModel!!.key + ";")
                .url(str_url!!)
                .build()

            val client = OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build()

            val response = client.newCall(request).execute()
            if(response.isSuccessful){

                response?.let {
                    str_response = response.body?.string()

                }
                return true
            }

        }
        catch (e:Exception) {

            Log.d(TAG,"Exception ${e.printStackTrace()}")
            return false

        }

        return false
    }

    override fun onPostExecute(result: Boolean?) {
        super.onPostExecute(result)
        if (result!!){
            callBackGetData?.let {
                str_response?.let {
                        it1 -> it.OnSuccess(it1) }
            }
        }else{
            callBackGetData?.let {
                it.OnError()
            }
        }
    }



}