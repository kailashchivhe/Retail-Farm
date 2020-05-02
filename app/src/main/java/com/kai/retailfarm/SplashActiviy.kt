package com.kai.retailfarm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rbddevs.splashy.Splashy

class SplashActivity : AppCompatActivity() {

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)

        Splashy( this )
            .setLogo( R.drawable.app_icon_hd )
            .setTitle( "RETAIL FARM" )
            .setTitleColor( R.color.white )
            .setBackgroundResource( R.color.colorPrimary )
            .setFullScreen( true )
            .setTime( 1500 )
            .show()

        Splashy.onComplete( object : Splashy.OnComplete {
            override fun onComplete() {
                startMainActivity()
            }
        })
    }

    fun startMainActivity()
    {
        val intent = Intent( this, MainActivity::class.java )
        finishAffinity()
        startActivity(intent)
    }
}