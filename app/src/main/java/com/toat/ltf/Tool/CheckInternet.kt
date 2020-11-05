package com.toat.ltf.Tool

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.util.Log
import android.widget.Toast
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.TimeUnit

class CheckInternet {
    private var speed_internet = 0
    private var type_internet = "nan"
    private val TAG = CheckInternet::class.java.simpleName
    private var wifi: NetworkInfo? = null

    fun isConnectInternet(context: Context?): Boolean {
        // TODO Auto-generated method stub
        if (context != null) {
            val connect = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connect != null) {
                val info = connect.allNetworkInfo
                if (info != null)
                    for (i in info.indices)
                        if (info[i].state == NetworkInfo.State.CONNECTED) {
                            return true
                        }
            }
        }

        return false
    }

    private fun isWifiAvailable(context: Context?): Boolean {

        var status_wifi = false
        if (context != null) {
            val connManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            wifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            status_wifi = wifi!!.isConnected
            if (wifi!!.isConnected) {
                val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
                val wifiInfo = wifiManager.connectionInfo
                speed_internet = wifiInfo.linkSpeed
                Log.d(TAG, "speed_internet $speed_internet")
            }
        }

        return status_wifi
    }

    private fun isNetWorkAvailable(context: Context?): Boolean {
        var status_network = false
        if (context != null) {
            val connMageManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val mobile = connMageManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
            status_network = mobile!!.isConnected
        }
        return status_network
    }

    fun isInternetAccessible(context: Context?): Boolean {

        if (context != null) {

            if (isWifiAvailable(context) || isNetWorkAvailable(context)) {
                println("isNetWorked")
                return true
            }
        }

        return false
    }

    fun noInternet(context: Context?) {

        if(context!=null){
            Toast.makeText(context, "การเชื่อมต่อขัดข้อง กรุณาตรวจสอบการเชื่อมต่ออินเตอร์เน็ทของคุณ", Toast.LENGTH_SHORT).show()
        }
//        if (context != null) {
//            val builder = AlertDialog.Builder(context, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
//            builder.setTitle("ไม่มีการเชื่อมต่ออินเตอร์เน็ท!")
//                .setMessage("กรุณาตรวจสอบการเชื่อมต่ออินเตอร์เน็ทของคุณ")
//                .setPositiveButton("ปิด") {
//                        dialog, i -> dialog.dismiss()}
//            builder.setNegativeButton("ตั้งค่า") {dialog, whichButton ->context.startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS)) }
//            builder.show()
//        }

    }

    @Throws(IOException::class)
    fun getExternalIp(): String {
        val json: JSONObject
        var IP = ""

        val httpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)
            .build()

        val request = Request.Builder()
            .url("http://www.ip-api.com/json")
            .build()
        val response = httpClient.newCall(request).execute()

        if (response.isSuccessful) {
            val responseData = response.body!!.string()
            println(":::::: Response Success Start ::::::")
            println(response.message)
            println(responseData)
            try {
                json = JSONObject(responseData)
                IP = json.getString("query")
            } catch (ignored: JSONException) {
                IP = ""
            }

            println(":::::: Response Success End ::::::")

        } else {
            println(":::::: Response Failure Start post ::::::")
            println(response.message)
            println(response.body!!.string())
            println(":::::: Response Failure End post ::::::")
        }
        return IP
    }

    fun getSpeedInternet(context: Context?): Int {

        if (context != null) {

            val connManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            wifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            if (wifi!!.isConnected) {
                val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
                val wifiInfo = wifiManager.connectionInfo
                speed_internet = wifiInfo.linkSpeed
                Log.d(TAG, "speed_internet $speed_internet")
            }
        }

        return speed_internet
    }

    fun getTypInternet(context: Context?): String {

        if (context != null) {
            if (isWifiAvailable(context)) {
                type_internet = "Net_Wifi"
            } else if (isNetWorkAvailable(context)) {
                type_internet = "Net_MOBILE"
            }
        }

        return type_internet
    }
}