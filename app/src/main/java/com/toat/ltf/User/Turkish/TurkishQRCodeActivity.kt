package com.toat.ltf.User.Turkish

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.Result
import com.toat.ltf.R
import kotlinx.android.synthetic.main.activity_turkish_qr_code.*
import kotlinx.android.synthetic.main.toolbar_main.*
import me.dm7.barcodescanner.zxing.ZXingScannerView

class TurkishQRCodeActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    private var TAG : String = this.javaClass.simpleName

    private var scannerView:ZXingScannerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_turkish_qr_code)

        this.SetToolbar()
        this.OnClick()

        this.scannerView = findViewById(R.id.scanner)
    }

    private fun SetToolbar(){
        text_toolbar.text = this.getString(R.string.title_activity_TurkishGrade)

        this.setSupportActionBar(toolbar_head_main)
        if(this.supportActionBar != null){
            this.supportActionBar!!.title = getString(R.string.txt_empty)
            this.supportActionBar!!.setHomeAsUpIndicator(R.drawable.icon_back_white)
            this.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

    }

    private fun OnClick(){
        this.toolbar_head_main.setNavigationOnClickListener {
            this.onBackPressed()
        }

        this.bt_go_grade.setOnClickListener {
            Toast.makeText(applicationContext, "ไป  ไป  ไป ไป ไป ตีเกรด", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()

        this.scannerView?.let {
            it.setResultHandler(this)
            it.startCamera()
            Log.d(TAG,"scannerView startCamera")
        }
    }

    override fun onPause() {
        super.onPause()
        this.scannerView?.let { it.stopCamera() }
    }


    override fun handleResult(result: Result?) {

        Log.w(TAG, result!!.text); // Prints scan results
        Log.w(TAG, result.barcodeFormat.toString()); // Prints the scan format (qrcode, pdf417 etc.)

        Toast.makeText(applicationContext,  result.text.toString(), Toast.LENGTH_SHORT).show()

        // If you would like to resume scanning, call this method below:
//        this.scannerView!!.resumeCameraPreview(this);
        this.onResume()
    }


    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }
}