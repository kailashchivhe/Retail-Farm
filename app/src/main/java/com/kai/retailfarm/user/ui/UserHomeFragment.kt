package com.kai.retailfarm.user.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigator

import com.kai.retailfarm.R
import com.kai.retailfarm.utility.firebase.FirebaseUtility
import com.kai.retailfarm.user.data.User
import com.kai.retailfarm.user.listeners.UserDetailsListner
import kotlinx.android.synthetic.main.fragment_user_home.*

class UserHomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        FirebaseUtility.getUserInfo( object : UserDetailsListner{
            override fun onUserDetailsReceived(user: User) {
                setUserName( user )
            }
        })
        setActionBarTitle()
        return inflater.inflate(R.layout.fragment_user_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cardView1.setOnClickListener {
            Navigation.findNavController(it).navigate( R.id.action_userHomeFragment_to_buyerFragment,null,null, FragmentNavigator.Extras.Builder()
                .addSharedElement(cardView1_imageview, ViewCompat.getTransitionName(cardView1_imageview)!!)
                .build() )
        }
        cardView2.setOnClickListener {
            Navigation.findNavController(it).navigate( R.id.action_userHomeFragment_to_sellerFragment,null,null, FragmentNavigator.Extras.Builder()
                .addSharedElement(cardView2_imageview, ViewCompat.getTransitionName(cardView2_imageview)!!)
                .build() )
        }
        logout.setOnClickListener {
            FirebaseUtility.logout()
            Navigation.findNavController( it ).navigate( R.id.action_userHomeFragment_to_loginFragment )
        }
    }

    fun setUserName( user: User )
    {
        if( view != null ) {
            user_name.text = user.name
        }
    }

    private fun setActionBarTitle(){
        val activity = activity as AppCompatActivity
        activity.supportActionBar?.title = "Home"
    }
}
