package com.kai.retailfarm.user.listeners

import com.kai.retailfarm.user.data.SellerItem

interface SellerRecyclerListListener {
    fun onItemClick( sellerItem: SellerItem )
}