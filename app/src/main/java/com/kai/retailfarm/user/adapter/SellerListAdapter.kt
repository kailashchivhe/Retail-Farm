package com.kai.retailfarm.user.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.kai.retailfarm.R
import com.kai.retailfarm.user.data.SellerItem
import kotlinx.android.synthetic.main.card_layout_seller_item.view.*
import kotlin.random.Random

class SellerListAdapter( private var mSellerList: MutableList<SellerItem>, private val listener: (SellerItem) -> Unit) : RecyclerView.Adapter< SellerListAdapter.SellerViewHolder >()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SellerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout_seller_item, parent, false)
        return SellerViewHolder( view )
    }

    override fun getItemCount(): Int {
        return mSellerList.size
    }

    override fun onBindViewHolder(holder: SellerViewHolder, position: Int) {
        holder.bind( mSellerList[position], listener )
    }

    fun refreshDataset( list: MutableList<SellerItem> )
    {
        mSellerList = list
    }

    class SellerViewHolder( itemView: View) : RecyclerView.ViewHolder( itemView )
    {
        @SuppressLint("SetTextI18n")
        fun bind(sellerItem: SellerItem, listener: (SellerItem) -> Unit) = with( itemView )
        {
            header_text.text = " ${sellerItem.itemName}  ₹${ sellerItem.itemPrice }/kg"
            sub_text.text = " Quantity : ${sellerItem.itemUnits} kgs "
            sub_text2.text = " Seller : ${sellerItem.sellerName} "
            val drawable = TextDrawable.builder().buildRound( sellerItem.itemName[0].toString() , getColor( context ) )
            imageview.setImageDrawable(drawable)
            cardView.setOnClickListener {
                listener(sellerItem)
            }
        }
    }

    companion object{
        fun getColor( context: Context): Int
        {
            val random = Random.nextInt(0, 5 )
            when( random ){
                0 ->{
                    return context.resources.getColor( R.color.red )
                }
                1 ->{
                    return context.resources.getColor( R.color.blue )
                }
                2 ->{
                    return context.resources.getColor( R.color.yellow )
                }
                3 ->{
                    return context.resources.getColor( R.color.green )
                }
                4 ->{
                    return context.resources.getColor( R.color.purple )
                }
            }
            return context.resources.getColor( R.color.brown )
        }
    }
}