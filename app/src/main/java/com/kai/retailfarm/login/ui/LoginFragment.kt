package com.kai.retailfarm.login.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.kai.retailfarm.R
import com.kai.retailfarm.firebase.FirebaseUtility
import com.kai.retailfarm.login.listeners.LoginResultListener
import kotlinx.android.synthetic.main.fragment_login.view.*

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        setActionBarTitle()

        view.username_edittext.addTextChangedListener( object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                TODO("Not yet implemented")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                TODO("Not yet implemented")
            }

        })

        view.password_edittext.addTextChangedListener( object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                TODO("Not yet implemented")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                TODO("Not yet implemented")
            }

        })

        view.button1.setOnClickListener {
            FirebaseUtility.login( view.username_edittext.text.toString(), view.password_edittext.text.toString(), object : LoginResultListener {
                override fun loginResultReceived( bSuccess: Boolean ) {
                    if( bSuccess )
                    {
                        //TODO
                    }
                    else
                    {
                        //TODO
                    }
                }
            } )
        }

        view.button2.setOnClickListener {
            //TODO add navigation support
        }

        return view
    }

    private fun setActionBarTitle(){
        val activity = activity as AppCompatActivity
        activity.supportActionBar?.title = "Login"
    }
}
