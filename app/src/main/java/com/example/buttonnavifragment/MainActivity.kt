package com.example.buttonnavifragment

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.util.Log
import com.example.buttonnavifragment.fragments.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override val TAG = MainActivity::class.java.simpleName
    override val LAYOUT_ID = R.layout.activity_main

    val FRAG_HOME = "home_frag"
    val FRAG_DASHBOARD = "dashboard_frag"
    val FRAG_NOTIFICATIONS = "notifications_frag"
    val FRAG_FAVORITES = "favorites_frag"
    val FRAG_SETTINGS = "settings_frag"

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                loadFragByTag(FRAG_HOME)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                loadFragByTag(FRAG_DASHBOARD)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                loadFragByTag(FRAG_NOTIFICATIONS)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorites -> {
                loadFragByTag(FRAG_FAVORITES)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                loadFragByTag(FRAG_SETTINGS)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        BottomNavigationViewHelper.disableShiftMode(navigation)

        loadFragByTag(FRAG_HOME)
    }

    private fun loadFragByTag(tag : String) {
        var frag = supportFragmentManager.findFragmentByTag(tag)
        if (frag == null) {
            Log.d(TAG, "$tag not found, creating a new one.")
            when (tag) {
                FRAG_HOME -> {
                    frag = HomeFrag()
                }
                FRAG_DASHBOARD -> {
                    frag = DashboardFrag()
                }
                FRAG_NOTIFICATIONS -> {
                    frag = NotificationsFrag()
                }
                FRAG_FAVORITES -> {
                    frag = FavoritesFrag()
                }
                FRAG_SETTINGS -> {
                    frag = SettingsFrag()
                }
            }

        } else {
            Log.d(TAG, "$tag found.")
        }

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fl_main, frag, tag)
                .commit()
    }

}
