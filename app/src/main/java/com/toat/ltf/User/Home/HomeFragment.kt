package com.toat.ltf.User.Home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.toat.ltf.R
import com.toat.ltf.User.Turkish.TurkishGradeActivity
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment  : Fragment()  {

    private var mContext:Context? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bt_go.setOnClickListener {
//            Toast.makeText(this.mContext, "bt_go Click", Toast.LENGTH_SHORT).show()
            this.Goto_TurkishGradeActivity()
        }

    }

    private fun Goto_TurkishGradeActivity(){
        val intent = Intent(this.mContext!!, TurkishGradeActivity::class.java)
       this.mContext!!.startActivity(intent)
    }


}