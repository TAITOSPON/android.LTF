package com.toat.ltf.User

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationView
import com.toat.ltf.LoginActivity
import com.toat.ltf.R
import com.toat.ltf.User.Home.HomeFragment
import com.toat.ltf.User.`interface`.Manage_page
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.*


class UserMainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener ,Manage_page {

    private var TAG : String = this.javaClass.simpleName
    private var fragmentManager     : FragmentManager? = null

    companion object{
        var fragment     : Fragment? = null
        val FragmentHome : Fragment = HomeFragment()
        val TwoFragment : Fragment = com.toat.ltf.User.Home.TwoFragment()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.SetToolbar()

        this.toolbar_head_main.setNavigationOnClickListener {
            drawer_layout.openDrawer(Gravity.LEFT)
        }

        nav_view.setNavigationItemSelectedListener(this)

//        Toast.makeText(applicationContext, "text", Toast.LENGTH_SHORT).show()

        // TODO Get FragmentManager  //
        this.fragmentManager = supportFragmentManager
        fragmentManager!!.beginTransaction().add(R.id.contentContainer, TwoFragment).hide(TwoFragment).commit()
        fragmentManager!!.beginTransaction().add(R.id.contentContainer, FragmentHome).commit()

        fragment = FragmentHome
        this.changeFragment(FragmentHome)

    }

    private fun SetToolbar(){
        this.text_toolbar.text = this.getString(R.string.menu_home)


        this.setSupportActionBar(toolbar_head_main)
        if(this.supportActionBar != null){
            this.supportActionBar!!.title = getString(R.string.string_empty)
            this.supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_format_list_bulleted)
            this.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when {
            item.itemId === R.id.nav_home -> {

                text_toolbar.text = this.getString(R.string.menu_home)
                this.changeFragment(FragmentHome)

            }
            item.itemId === R.id.nav_menu_2 -> {

                text_toolbar.text = this.getString(R.string.menu_two)
                this.changeFragment(TwoFragment)

            }

            item.itemId === R.id.nav_logout -> {
                text_toolbar.text = this.getString(R.string.menu_logout)
                val intent = Intent(this@UserMainActivity, LoginActivity::class.java)
                this@UserMainActivity.startActivity(intent)
                finish()
            }
        }

        drawer_layout.closeDrawer(Gravity.LEFT);
        return super.onOptionsItemSelected(item)
    }



    override fun changeFragment(fragment: Fragment) {

//        this.ClickBack_Manage = fragment as ClickBack_Manage
        fragmentManager!!.beginTransaction().hide(Companion.fragment!!).show(fragment).commit()
        Companion.fragment = fragment


    }

    override fun onBackPressed() {

        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(Gravity.LEFT)
        }else{
            super.onBackPressed()
            this.finish()
        }
    }
}