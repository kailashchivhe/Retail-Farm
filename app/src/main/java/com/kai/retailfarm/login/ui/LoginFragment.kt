package com.kai.retailfarm.login.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.kai.retailfarm.R
import com.kai.retailfarm.utility.firebase.FirebaseUtility
import com.kai.retailfarm.login.listeners.FirebaseSuccessListener
import com.kai.retailfarm.utility.validation.ValidationUtility
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.button1
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.android.synthetic.main.fragment_login.view.username_edittext


class LoginFragment : Fragment() {

    var mbEmailValid = false
    var mbPasswordValid = false

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        setActionBarTitle()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button1.setOnClickListener {
            showLoadingAnimations()
            FirebaseUtility.login( view.username_edittext.text.toString(), view.password_edittext.text.toString(), object : FirebaseSuccessListener {
                override fun loginResultReceived( bSuccess: Boolean ) {
                    if( bSuccess )
                    {
                        Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_userHomeFragment)
                    }
                    else
                    {
                        Toast.makeText( requireContext(), "login Failed", Toast.LENGTH_SHORT ).show()
                    }
                }
            } )
        }

        button2.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        view.username_edittext.addTextChangedListener( object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mbEmailValid = ValidationUtility.emailValidation( s.toString() )
                shouldEnableLoginButton()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        view.password_edittext.addTextChangedListener( object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mbPasswordValid = ValidationUtility.passwordValidation( s.toString() )
                shouldEnableLoginButton()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
    }

    private fun showLoadingAnimations()
    {
        view?.imgLargeIcon?.visibility = View.INVISIBLE
        view?.animation_view?.visibility = View.VISIBLE
    }

    private fun shouldEnableLoginButton()
    {
        if( mbEmailValid && mbPasswordValid  )
        {
            button1.isEnabled = true
        }
    }

    private fun setActionBarTitle(){
        val activity = activity as AppCompatActivity
        activity.supportActionBar?.title = "Login"
    }
}
