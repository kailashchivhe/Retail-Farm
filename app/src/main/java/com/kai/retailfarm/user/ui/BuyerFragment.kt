package com.kai.retailfarm.user.ui

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.kai.retailfarm.R
import kotlinx.android.synthetic.main.fragment_buyer.*


class BuyerFragment : Fragment(){

    companion object{
        private val items = arrayOf("Potato", "Onion", "Tomato")
        private val areas = arrayOf("Pune", "Mumbai")
    }

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
        return inflater.inflate(R.layout.fragment_buyer, container, false)
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

        buyer_button.setOnClickListener {

        }
    }

}
