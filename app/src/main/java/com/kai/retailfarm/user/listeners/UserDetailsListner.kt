package com.kai.retailfarm.user.listeners

import com.kai.retailfarm.user.data.User

interface UserDetailsListner {
    fun onUserDetailsReceived( user: User )
}