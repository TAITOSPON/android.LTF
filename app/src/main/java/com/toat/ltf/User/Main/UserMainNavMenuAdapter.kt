package com.toat.ltf.User.Main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.toat.ltf.R

class UserMainNavMenuAdapter (var mContext : Context,
                              var ListMenu : Array<String>,
                              var navAdapterLineManagerCallback: NavAdapterLineManagerCallback)
    : RecyclerView.Adapter<UserMainNavMenuAdapter.ViewHolder>() {

    interface NavAdapterLineManagerCallback{
        fun CallBackItem(holder: ViewHolder, position: Int, Menu:String)
    }

    override fun getItemCount(): Int { return  ListMenu.size }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.nav_menu_custom, parent, false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        navAdapterLineManagerCallback.CallBackItem(holder,position, ListMenu[position])


//        if(((position+1)%2) == 0){
//            holder.nav_bg!!.setBackgroundColor(mContext.resources.getColor(R.color.teal_700))
//        }else{
//            holder.nav_bg!!.setBackgroundColor(mContext.resources.getColor(R.color.white))
//        }

        holder.nav_text!!.text = ListMenu[position]


        when {
            ListMenu[position] == mContext.resources.getString(R.string.menu_home) -> {
                holder.nav_icon!!.setImageResource(R.drawable.ic_menu_camera)
            }

            ListMenu[position] == mContext.resources.getString(R.string.menu_two) -> {
                holder.nav_icon!!.setImageResource(R.drawable.ic_menu_camera)
            }

        }

    }





    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        var nav_bg   : LinearLayout?= null
        var nav_icon : ImageView? = null
        var nav_text : TextView? = null

        init {
            itemView.let {
                nav_bg     = itemView.findViewById(R.id.nav_bg)
                nav_icon   = itemView.findViewById(R.id.nav_icon)
                nav_text   = itemView.findViewById(R.id.nav_text)
            }
        }

    }
}