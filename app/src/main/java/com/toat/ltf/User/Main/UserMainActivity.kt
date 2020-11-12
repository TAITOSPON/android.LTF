package com.toat.ltf.User.Main

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.toat.ltf.R
import com.toat.ltf.User.Home.HomeFragment
import com.toat.ltf.User.Home.TwoFragment
import com.toat.ltf.User.`interface`.Manage_page
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.*


class UserMainActivity : AppCompatActivity(),Manage_page {

    private var TAG : String = this.javaClass.simpleName
    private var fragmentManager     : FragmentManager? = null
    private var mDrawerLayout : DrawerLayout? = null



    companion object{
        var fragment     : Fragment? = null
        val FragmentHome : Fragment = HomeFragment()
        val TwoFragment : Fragment = TwoFragment()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.SetToolbar()
        this.ManagerFragment()
        this.SetNavigation()


    }

    private fun SetToolbar(){
        this.text_toolbar.text = this.getString(R.string.menu_home)

        this.setSupportActionBar(this.toolbar_head_main)
        if(this.supportActionBar != null){
            this.supportActionBar!!.title = getString(R.string.txt_empty)
            this.supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_format_list_bulleted)
            this.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

    }

    private fun ManagerFragment(){
        // TODO Get FragmentManager  //
        this.fragmentManager = supportFragmentManager
        this.fragmentManager!!.beginTransaction().add(R.id.contentContainer, TwoFragment).hide(TwoFragment).commit()
        this.fragmentManager!!.beginTransaction().add(R.id.contentContainer, FragmentHome).commit()

        fragment = FragmentHome
        this.changeFragment(FragmentHome)
    }

    private fun SetNavigation(){


        this.mDrawerLayout = this.findViewById(R.id.drawer_layout)

        var headerLayout = this.nav_view.getHeaderView(0)

        val actionBarDrawerToggle =  ActionBarDrawerToggle(this,
                this.drawer_layout,
                this.toolbar_head_main,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
        this.drawer_layout.addDrawerListener(actionBarDrawerToggle)

        val ListMenu = resources.getStringArray(R.array.item_menu)

        this.recyclerView_slider_menu.setHasFixedSize(true)
        this@UserMainActivity.runOnUiThread {
            this.recyclerView_slider_menu!!.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@UserMainActivity)
                adapter = UserMainNavMenuAdapter(this@UserMainActivity,ListMenu,
                        object : UserMainNavMenuAdapter.NavAdapterLineManagerCallback {
                            override fun CallBackItem(
                                    holder: UserMainNavMenuAdapter.ViewHolder,
                                    position: Int,
                                    Menu: String
                            ) {
                                holder.nav_bg!!.setOnClickListener {

                                    this@UserMainActivity.mDrawerLayout!!.closeDrawers()

                                    when (Menu) {
                                        this@UserMainActivity.resources.getString(R.string.menu_home) -> {
                                            text_toolbar.text = this@UserMainActivity.getString(R.string.menu_home)
                                            this@UserMainActivity.changeFragment(FragmentHome)

                                        }

                                        this@UserMainActivity.resources.getString(R.string.menu_two) -> {
                                            text_toolbar.text = this@UserMainActivity.getString(R.string.menu_two)
                                            this@UserMainActivity.changeFragment(TwoFragment)
                                        }


                                    }
                                }
                            }
                        })
            }
        }
    }


    override fun changeFragment(fragment: Fragment) {
//        this.ClickBack_Manage = fragment as ClickBack_Manage
        this.fragmentManager!!.beginTransaction().hide(Companion.fragment!!).show(fragment).commit()
        Companion.fragment = fragment


    }

    override fun onBackPressed() {

        if (this.mDrawerLayout!!.isDrawerOpen(GravityCompat.START)) {
            this.mDrawerLayout!!.closeDrawers()
        }else{
            super.onBackPressed()
            this.finish()
        }
    }
}