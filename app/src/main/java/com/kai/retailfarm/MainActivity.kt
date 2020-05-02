package com.kai.retailfarm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import com.kai.retailfarm.utility.firebase.FirebaseUtility

class MainActivity : AppCompatActivity() {

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setHomeAsUpIndicator(  R.drawable.action_bar_icon )
        supportActionBar?.setDisplayHomeAsUpEnabled( true )

        if( FirebaseUtility.isFirebaseUserLogged() )
        {
            Navigation.findNavController( this, R.id.nav_host_fragment ).navigate( R.id.action_loginFragment_to_userHomeFragment )
        }
    }
}
