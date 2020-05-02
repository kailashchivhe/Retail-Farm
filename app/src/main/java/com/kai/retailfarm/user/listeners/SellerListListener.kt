package com.kai.retailfarm.user.listeners

import com.kai.retailfarm.user.data.SellerItem

interface SellerListListener {
    fun onSellersListReceivedListener( list: MutableList<SellerItem> )
}