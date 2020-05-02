package com.kai.retailfarm.user.ui

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth

import com.kai.retailfarm.R
import com.kai.retailfarm.firebase.FirebaseUtility
import com.kai.retailfarm.login.listeners.LoginResultListener
import com.kai.retailfarm.login.utility.ValidationUtility
import com.kai.retailfarm.user.data.SellerItem
import com.kai.retailfarm.user.data.User
import com.kai.retailfarm.user.listeners.UserDetailsListner
import kotlinx.android.synthetic.main.fragment_buyer.area
import kotlinx.android.synthetic.main.fragment_buyer.item
import kotlinx.android.synthetic.main.fragment_seller.*


class SellerFragment : Fragment() {

    companion object{
        private val items = arrayOf("Potato", "Onion", "Tomato")
        private val areas = arrayOf("Pune", "Mumbai")
    }

    var mbPriceValid = false
    var mbUnitValid = false
    var sellerItem = SellerItem( "Potato", "0", "0", "", "", "", "" )

    private val  itemSpinnerListner = object : AdapterView.OnItemSelectedListener{
        override fun onNothingSelected(parent: AdapterView<*>?) {
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when(position){
                0->{
                    sellerItem.itemName = "Potato"
                }
                1->{
                    sellerItem.itemName = "Onion"
                }
                2->{
                    sellerItem.itemName = "Tomato"
                }
            }
        }

    }

    private val  areaSpinnerListner = object : AdapterView.OnItemSelectedListener{
        override fun onNothingSelected(parent: AdapterView<*>?) {
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when(position){
                0->{
                    sellerItem.sellerArea = "Pune"
                }
                1->{
                    sellerItem.sellerArea = "Mumbai"
                }
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_seller, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemsAdapter: ArrayAdapter<String>? = activity?.let {
            ArrayAdapter<String>(
                it,
                android.R.layout.simple_spinner_item, items
            )
        }

        val areaAdapter: ArrayAdapter<String>? = activity?.let {
            ArrayAdapter<String>(
                it,
                android.R.layout.simple_spinner_item, areas
            )
        }

        itemsAdapter?.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item )
        item.adapter = itemsAdapter
        item.onItemSelectedListener = itemSpinnerListner

        areaAdapter?.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item )
        area.adapter = areaAdapter
        area.onItemSelectedListener = areaSpinnerListner

        price_edittext.addTextChangedListener( object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mbPriceValid = ValidationUtility.priceValidation( s.toString() )
                if( mbPriceValid )
                {
                    sellerItem.itemPrice = s.toString()
                }
                shouldEnableSellerButton()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        units_edittext.addTextChangedListener( object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mbUnitValid = ValidationUtility.unitValidation( s.toString() )
                if( mbUnitValid )
                {
                    sellerItem.itemUnits = s.toString()
                }
                shouldEnableSellerButton()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        seller_button.setOnClickListener {
            showLoadingAnimations()
            FirebaseUtility.getUserInfo( object : UserDetailsListner{
                override fun onUserDetailsReceived(user: User) {
                    sellerItem.sellerName = user.name
                    sellerItem.sellerContact = user.mobile
                    sellerItem.sellerID = FirebaseAuth.getInstance().currentUser!!.uid
                    FirebaseUtility.addSellerRequest( sellerItem, object : LoginResultListener{
                        override fun loginResultReceived(bSuccess: Boolean) {
                            if( bSuccess )
                            {
                                showSuccessAnimations()
                                Handler().postDelayed({
                                    Navigation.findNavController( requireView() ).popBackStack()
                                }, 2000 )
                            }
                            else
                            {
                                Toast.makeText( requireContext(), "Failed", Toast.LENGTH_SHORT ).show()
                            }
                        }
                    })
                }
            })
        }
    }

    private fun shouldEnableSellerButton()
    {
        if( mbPriceValid && mbUnitValid )
        {
            seller_button.isEnabled = true
        }
    }

    private fun showLoadingAnimations()
    {
        seller_imgLargeIcon?.visibility = View.INVISIBLE
        loading_animation_view?.visibility = View.VISIBLE
    }

    private fun showSuccessAnimations()
    {
        loading_animation_view?.visibility = View.INVISIBLE
        success_animation_view?.visibility = View.VISIBLE
    }
}
