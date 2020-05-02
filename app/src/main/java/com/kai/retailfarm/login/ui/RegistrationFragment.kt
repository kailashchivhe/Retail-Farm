package com.kai.retailfarm.login.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.Navigation

import com.kai.retailfarm.R
import com.kai.retailfarm.firebase.FirebaseUtility
import com.kai.retailfarm.location.LocationUtility
import com.kai.retailfarm.user.data.User
import com.kai.retailfarm.login.listeners.LoginResultListener
import com.kai.retailfarm.login.utility.ValidationUtility
import com.kai.retailfarm.user.listeners.UserDetailsRecordInsertedListener
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.android.synthetic.main.fragment_registration.*

class RegistrationFragment : Fragment() {

    var mbEmailValid = false
    var mbPasswordValid = false
    var mbMobileValid = false
    var mbCityValid = false
    var mbPostalValid = false
    var mbStateValid = false
    var mbNameValid = false
    lateinit var mUser: User

    companion object{
        const val LOCATION_PERMISSION_REQUEST_CODE = 555
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_registration, container, false)
        setActionBarTitle()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if ( ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED )
        {
            this.requestPermissions( arrayOf( Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE )
        }
        else {
            getUserDetails()
        }

        email.addTextChangedListener( object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mbEmailValid = ValidationUtility.emailValidation( s.toString() )
                shouldEnableRegisterButton()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        name.addTextChangedListener( object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mbNameValid = ValidationUtility.nameValidation( s.toString() )
                shouldEnableRegisterButton()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        password.addTextChangedListener( object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mbPasswordValid = ValidationUtility.passwordValidation( s.toString() )
                shouldEnableRegisterButton()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        mobile.addTextChangedListener( object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mbMobileValid = ValidationUtility.mobileValidation( s.toString() )
                shouldEnableRegisterButton()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        city.addTextChangedListener( object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mbCityValid = ValidationUtility.cityValidation( s.toString() )
                shouldEnableRegisterButton()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        pincode.addTextChangedListener( object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mbPostalValid = ValidationUtility.postalValidation( s.toString() )
                shouldEnableRegisterButton()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        state.addTextChangedListener( object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mbStateValid = ValidationUtility.stateValidation( s.toString() )
                shouldEnableRegisterButton()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        button1.setOnClickListener {
            showLoadingAnimations()
            FirebaseUtility.register( mUser, object: LoginResultListener{
                override fun loginResultReceived(bSuccess: Boolean) {
                    if( bSuccess ) {
                        FirebaseUtility.createUserRecord(mUser,  object : LoginResultListener{
                            override fun loginResultReceived(bSuccess: Boolean) {
                                if( bSuccess ) {
                                    Navigation.findNavController(it).navigate(R.id.action_registrationFragment_to_userHomeFragment)
                                }
                                else{
                                    Toast.makeText( requireContext(), "Registration Failed", Toast.LENGTH_SHORT ).show()
                                }
                            }
                        })
                    }
                }
            })
        }
    }

    private fun setActionBarTitle(){
        val activity = activity as AppCompatActivity
        activity.supportActionBar?.title = "Registration"
    }

    private fun showLoadingAnimations()
    {
        view?.imgLargeIcon?.visibility = View.INVISIBLE
        view?.animation_view?.visibility = View.VISIBLE
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if( requestCode == LOCATION_PERMISSION_REQUEST_CODE && permissions.contains( Manifest.permission.ACCESS_COARSE_LOCATION )
            && permissions.contains( Manifest.permission.ACCESS_FINE_LOCATION ) )
        {
            getUserDetails()
        }
    }

    private fun shouldEnableRegisterButton()
    {
        if( mbEmailValid && mbPasswordValid && mbMobileValid && mbCityValid && mbPostalValid && mbStateValid && mbNameValid )
        {
            mUser = User(
                email.text.toString(),
                password.text.toString(),
                mobile.text.toString(),
                city.text.toString(),
                pincode.text.toString(),
                state.text.toString(),
                name.text.toString()
            )
            button1.isEnabled = true
        }
    }

    private fun getUserDetails() {
        val address = LocationUtility.getLocationDetails( requireContext() )
        city.setText(address?.subAdminArea)
        pincode.setText(address?.postalCode)
        state.setText(address?.adminArea)
        if( address != null ) {
            mbCityValid = true
            mbPostalValid = true
            mbStateValid = true
        }
    }
}
