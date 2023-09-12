package com.sonderben.kagom.ui.distribution_center


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.sonderben.kagom.R
import com.sonderben.kagom.data.Address
import com.sonderben.kagom.data.DistributionCenter
import com.sonderben.kagom.data.Schedule
import com.sonderben.kagom.utils.KMPreferences


public open class DistributionAdapter(private val distributions:List<DistributionCenter>): RecyclerView.Adapter<DistributionAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById<TextView>(R.id.name)
        var address = itemView.findViewById<TextView>(R.id.direction)

        var telephone = itemView.findViewById<TextView>(R.id.tel)
        var email = itemView.findViewById<TextView>(R.id.email)
        var schedule = itemView.findViewById<TextView>(R.id.schedule)
        var homeDistribution:ImageView = itemView.findViewById(R.id.home_distribution)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.distribution_item,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return distributions.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val distribution = distributions[holder.adapterPosition]

        val pref = KMPreferences(holder.itemView.context)

        holder.name.text = distribution.name
        holder.address.text = addressToString(distribution.address)
        holder.telephone.text = distribution.tel
        holder.email.text = distribution.email
        holder.schedule.text =  distribution.schedule
        if ( pref.idDistribution.equals(  distribution.id.toString()   )){
            holder.homeDistribution.visibility = View.VISIBLE
        }
    }

    private fun addressToString(address:Address) = "${address.country},${address.city}, ${address.direction}"




}