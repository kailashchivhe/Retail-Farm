package com.kai.retailfarm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setHomeAsUpIndicator(  R.drawable.action_bar_icon )
        supportActionBar?.setDisplayHomeAsUpEnabled( true )
    }
}
