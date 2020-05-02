package com.kai.retailfarm.user.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter

import com.kai.retailfarm.R
import com.kai.retailfarm.login.utility.ValidationUtility
import kotlinx.android.synthetic.main.fragment_buyer.*
import kotlinx.android.synthetic.main.fragment_buyer.area
import kotlinx.android.synthetic.main.fragment_buyer.item
import kotlinx.android.synthetic.main.fragment_seller.*


class SellerFragment : Fragment() {

    companion object{
        private val items = arrayOf("Potato", "Onion", "Tomato")
        private val areas = arrayOf("Pune", "Mumbai", "Nashik")
    }

    var mbPriceValid = false
    var mbUnitValid = false

    private val  itemSpinnerListner = object : AdapterView.OnItemSelectedListener{
        override fun onNothingSelected(parent: AdapterView<*>?) {
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when(position){
                0->{

                }
                1->{

                }
                2->{

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

                }
                1->{

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
                shouldEnableSellerButton()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        units_edittext.addTextChangedListener( object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mbPriceValid = ValidationUtility.unitValidation( s.toString() )
                shouldEnableSellerButton()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        seller_button.setOnClickListener {

        }
    }

    private fun shouldEnableSellerButton()
    {
        if( mbPriceValid && mbUnitValid )
        {
            seller_button.isEnabled = true
        }
    }
}
