package com.kai.retailfarm.user.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kai.retailfarm.R
import com.kai.retailfarm.firebase.FirebaseUtility
import com.kai.retailfarm.user.data.User
import com.kai.retailfarm.user.listeners.UserDetailsListner
import kotlinx.android.synthetic.main.fragment_user_home.*

class UserHomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        FirebaseUtility.getUserInfo( object : UserDetailsListner{
            override fun onUserDetailsReceived(user: User) {
                setUserName( user )
            }
        })
        return inflater.inflate(R.layout.fragment_user_home, container, false)
    }

    fun setUserName( user: User )
    {
        user_name.text = user.name
    }
}
