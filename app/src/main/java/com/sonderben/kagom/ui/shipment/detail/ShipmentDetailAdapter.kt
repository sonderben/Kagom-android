package com.sonderben.kagom.ui.shipment.detail

import android.content.Context
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sonderben.kagom.R
import com.sonderben.kagom.data.Package
import com.sonderben.kagom.utils.Util
import java.text.NumberFormat


class ShipmentDetailAdapter(var packages:List<Package>?,var context:Context): RecyclerView.Adapter<ShipmentDetailAdapter.MyViewHolder>() {



    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name:TextView = itemView.findViewById(R.id.name)
        val description:TextView = itemView.findViewById(R.id.description)
        val identity:TextView = itemView.findViewById(R.id.identity)
        val qty:TextView = itemView.findViewById(R.id.qty)
        val volume:TextView = itemView.findViewById(R.id.volume)
        val weight:TextView = itemView.findViewById(R.id.weight)
        val price:TextView = itemView.findViewById(R.id.price)
        val itbis:TextView = itemView.findViewById(R.id.itbis)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_package,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount()= packages?.size ?:0

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val pos = holder.adapterPosition
        holder.name.text = packages?.get(pos)?.name ?: ""
        holder.description.text = packages?.get(pos)?.description ?: ""
        holder.price.text = foregroundColorSpan("prix",packages?.get(pos)?.price?.format() ?: "")
        holder.itbis.text = foregroundColorSpan("Itbis",packages?.get(pos)?.itbis?.format() ?: "")//
        holder.qty.text = foregroundColorSpan("Qty",String.format( "%s",packages?.get(pos)?.qty ))//
        holder.weight.text = foregroundColorSpan("Poids",String.format( "%.2f",packages?.get(pos)?.weight )) //
        holder.volume.text = foregroundColorSpan("Dimention", packages?.get(pos)?.dimension ?: "") //

        holder.identity.text = foregroundColorSpan("Id", packages?.get(pos)?.kmidentity ?: "unknown")


    }


    private fun Double.format():String{
        val numberFormat:NumberFormat = NumberFormat.getCurrencyInstance()
        val e = numberFormat.format(this).split(".")

        return if(e.size == 2) e[0]+"."+Util.positionToSupIndex( Integer.valueOf(e[1]) ) else e[0]
    }

    fun foregroundColorSpan(hint:String,string:String): SpannableString {
        val hint2 = "$hint: "
        val spannableString = SpannableString(hint2+string)
        spannableString.setSpan( ForegroundColorSpan( context.getColor(R.color.hint)  ),0, hint.length+1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE )

        return spannableString
    }

    fun truncateToTwoDigits(number: Double): Double {
        return String.format("%.2f", number).toDouble()
    }




}