package com.dio.spotifyclonetostudie.presenter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.dio.spotifyclonetostudie.R
import com.dio.spotifyclonetostudie.presenter.model.Fragment.Home
import com.dio.spotifyclonetostudie.presenter.model.Fragment.Libery
import com.dio.spotifyclonetostudie.presenter.model.Fragment.Search
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class BaseActivity : AppCompatActivity() {

    private var  Content: FrameLayout? = null

    private var mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId){

            R.id.nav_home -> {
                val fragment = Home.newInstance()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_search -> {
                val fragment = Search.newInstance()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.nav_libery -> {
                val fragment = Libery.newInstance()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }

    }
        false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Content = content
        bottom_menu.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }


    private fun addFragment(fragment : Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content,fragment,fragment.javaClass.simpleName)
            .commit()
    }
}