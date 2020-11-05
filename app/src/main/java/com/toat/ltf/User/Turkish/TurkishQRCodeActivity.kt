package com.toat.ltf.User.Turkish

import android.os.Bundle
import android.util.Log
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

        this.scannerView = findViewById(R.id.scanner)
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

        // If you would like to resume scanning, call this method below:
//        this.scannerView!!.resumeCameraPreview(this);
        this.onResume()
    }
}