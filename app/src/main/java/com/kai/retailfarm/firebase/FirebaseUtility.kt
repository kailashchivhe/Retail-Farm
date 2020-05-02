package com.kai.retailfarm.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.kai.retailfarm.user.data.User
import com.kai.retailfarm.login.listeners.LoginResultListener
import com.kai.retailfarm.user.data.SellerItem
import com.kai.retailfarm.user.listeners.SellerListListener
import com.kai.retailfarm.user.listeners.UserDetailsListner

class FirebaseUtility{
    companion object{
        fun login(userName: String, password:String, loginResultListener: LoginResultListener){
            FirebaseAuth.getInstance().signInWithEmailAndPassword(userName,password).addOnCompleteListener {
                loginResultListener.loginResultReceived( it.isSuccessful )
            }
        }

        fun logout() {
            FirebaseAuth.getInstance().signOut()
        }

        fun register(user: User, loginResultListener: LoginResultListener ){
            FirebaseAuth.getInstance().createUserWithEmailAndPassword( user.email, user.password ).addOnCompleteListener {
                loginResultListener.loginResultReceived( it.isSuccessful )
            }
        }

        fun createUserRecord( user: User, loginResultListener: LoginResultListener ){
            FirebaseDatabase.getInstance().getReference("users" ).child( FirebaseAuth.getInstance().currentUser!!.uid )
                .setValue( user ).addOnCompleteListener {
                    loginResultListener.loginResultReceived( it.isSuccessful )
                }
        }

        fun isFirebaseUserLogged():Boolean{
            return FirebaseAuth.getInstance().currentUser != null
        }

        fun getUserInfo( userDetailsListner: UserDetailsListner ){
            FirebaseDatabase.getInstance().getReference( "users" ).child( FirebaseAuth.getInstance().currentUser!!.uid ).addValueEventListener( object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val user = User( dataSnapshot.child("email").value as String,
                        dataSnapshot.child("password").value as String,
                        dataSnapshot.child("mobile").value as String,
                        dataSnapshot.child("city").value as String,
                        dataSnapshot.child("postal").value as String,
                        dataSnapshot.child("state").value as String,
                        dataSnapshot.child( "name" ).value as String )
                    userDetailsListner.onUserDetailsReceived( user )
                }
            })
        }

        fun getSellersList(sellerListListener: SellerListListener, area: String){
            FirebaseDatabase.getInstance().getReference( "sellers" ).addValueEventListener( object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {}

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val mutableList = mutableListOf<SellerItem>()
                    for( item in dataSnapshot.children ){
                        val sellerItem = SellerItem( dataSnapshot.child("itemName").value as String,
                            dataSnapshot.child("itemPrice").value as String,
                            dataSnapshot.child("itemUnits").value as String,
                            dataSnapshot.child("sellerID").value as String,
                            dataSnapshot.child("sellerName").value as String,
                            dataSnapshot.child("sellerContact").value as String,
                            dataSnapshot.child( "sellerArea" ).value as String )
                        if( sellerItem.sellerArea.equals( area,true) )
                        {
                            mutableList.add( sellerItem )
                        }
                    }
                    sellerListListener.onSellersListReceivedListener( mutableList )
                }
            })
        }

        fun addSellerRequest( sellerItem: SellerItem, resultListener: LoginResultListener){
            FirebaseDatabase.getInstance().getReference("sellers" ).push().setValue( sellerItem ).addOnCompleteListener {
                resultListener.loginResultReceived( it.isSuccessful )
            }
        }
    }
}