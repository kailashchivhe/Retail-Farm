package com.kai.retailfarm.firebase

import com.google.firebase.auth.FirebaseAuth
import com.kai.retailfarm.login.listeners.LoginResultListener

class FirebaseUtility{
    companion object{
        fun login(userName: String, password:String, loginResultListener: LoginResultListener){
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(userName,password).addOnCompleteListener {
                loginResultListener.loginResultReceived( it.isSuccessful )
            }
        }
    }
}