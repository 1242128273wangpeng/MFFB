package com.wangpeng.firstfirebase

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics


class HomeActivity : AppCompatActivity() {
    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        findViewById<TextView>(R.id.centerTv).setOnClickListener {
            logReport(FirebaseAnalytics.Event.LOGIN)
        }
        findViewById<TextView>(R.id.bottomTv).setOnClickListener {
            logReport(FirebaseAnalytics.Event.SELECT_CONTENT)
        }
        findViewById<TextView>(R.id.topTv).setOnClickListener {
            mFirebaseAnalytics?.setUserProperty("property", "user_property_success")
        }
        findViewById<TextView>(R.id.topUserIdTv).setOnClickListener {
            mFirebaseAnalytics?.setUserId("user_id_success")
        }
        findViewById<TextView>(R.id.bottomScreenTv).setOnClickListener {
            mFirebaseAnalytics?.setCurrentScreen(this,"secondActivity","onClick")
        }
    }

    private fun logReport(type: String) {
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "id")
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "name")
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image")
        mFirebaseAnalytics?.logEvent(type, bundle)
    }
}