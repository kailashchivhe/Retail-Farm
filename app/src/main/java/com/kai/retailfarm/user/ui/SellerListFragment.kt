package com.kai.retailfarm.user.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.kai.retailfarm.R
import com.kai.retailfarm.utility.firebase.FirebaseUtility
import com.kai.retailfarm.user.adapter.SellerListAdapter
import com.kai.retailfarm.user.data.SellerItem
import com.kai.retailfarm.user.listeners.SellerListListener
import com.kai.retailfarm.user.listeners.SellerRecyclerListListener
import kotlinx.android.synthetic.main.fragment_seller_list.*

class SellerListFragment : Fragment(), SellerRecyclerListListener {
    private var mItem:String? = null
    private var mArea:String? = null

    private lateinit var mRecycleListView: RecyclerView
    private var mSellerList: MutableList<SellerItem> = mutableListOf()
    private lateinit var mSellerListAdapter: SellerListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mItem = arguments?.getString( "itemName", "" )
        mArea = arguments?.getString( "areaName", "" )
        setActionBarTitle()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_seller_list, container, false)

        mRecycleListView = view.findViewById(R.id.recycler_view_list)
        mRecycleListView.setHasFixedSize(true)
        mRecycleListView.layoutManager = LinearLayoutManager( context )

        mSellerListAdapter = SellerListAdapter( mSellerList ){
            onItemClick( it )
        }

        mRecycleListView.adapter = mSellerListAdapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoadingAnimations()

        if( !mItem.isNullOrBlank() && !mArea.isNullOrBlank() ) {
            FirebaseUtility.getSellersList(object : SellerListListener {
                override fun onSellersListReceivedListener(list: MutableList<SellerItem>) {
                    refreshDataset( list )
                }
            }, mItem!!, mArea!!)
        }
        else
        {
            Toast.makeText( requireContext(), "Load Failed", Toast.LENGTH_SHORT ).show()
        }
    }

    fun refreshDataset(list: MutableList<SellerItem> )
    {
        Handler().postDelayed({
            if( view != null )
            {
                if( mSellerListAdapter.getCurrentDataset().isEmpty() && list.isEmpty() )
                {
                    stopLoadingAnimations()
                    noDataAvailable()
                }
                else {
                    mSellerListAdapter.refreshDataset(list)
                    stopLoadingAnimations()
                    mSellerListAdapter.notifyDataSetChanged()
                }
            }
        },2000 )
    }

    private fun showLoadingAnimations()
    {
        recycler_view_list?.visibility = View.INVISIBLE
        animation_view?.visibility = View.VISIBLE
    }

    private fun stopLoadingAnimations()
    {
        recycler_view_list?.visibility = View.VISIBLE
        animation_view?.visibility = View.GONE
    }

    private fun noDataAvailable()
    {
        recycler_view_list?.visibility = View.INVISIBLE
        animation_view?.visibility = View.INVISIBLE
        status_text?.visibility = View.VISIBLE
    }

    override fun onItemClick(sellerItem: SellerItem) {
        val intent = Intent()
        intent.action = Intent.ACTION_DIAL
        intent.data = Uri.parse("tel: ${sellerItem.sellerContact}")
        startActivity(intent)
    }

    private fun setActionBarTitle(){
        val activity = activity as AppCompatActivity
        if( !mItem.isNullOrBlank() && !mArea.isNullOrBlank() ) {
            activity.supportActionBar?.title = "${mItem} Sellers in ${mArea}"
        }
        else
        {
            activity.supportActionBar?.title = "Sellers"
        }
    }
}
