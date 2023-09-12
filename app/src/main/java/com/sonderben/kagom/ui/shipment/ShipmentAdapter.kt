package com.sonderben.kagom.ui.shipment

import android.R.attr.text
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sonderben.kagom.R
import com.sonderben.kagom.data.Shipment
import com.sonderben.kagom.data.ShipmentsStatus
import java.text.DateFormat
import java.util.Date


class ShipmentAdapter(private val shipments: List<Shipment>) :RecyclerView.Adapter<ShipmentAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var status:TextView = itemView.findViewById(R.id.shipment_status)
        var from:TextView = itemView.findViewById(R.id.from)
        var to:TextView = itemView.findViewById(R.id.to)
        var originNameAddress:TextView = itemView.findViewById(R.id.from_name)
        var destinationNameAddress:TextView = itemView.findViewById(R.id.to_name)

        var fromDirection:TextView = itemView.findViewById(R.id.from_direction)
        var toDirection:TextView = itemView.findViewById(R.id.to_direction)
        var fromDate:TextView = itemView.findViewById(R.id.from_date)
        var toDate:TextView = itemView.findViewById(R.id.to_date)

        var info:TextView = itemView.findViewById(R.id.detail)
        val trackingNumber:TextView = itemView.findViewById(R.id.tracking_number);


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.item_shipment,parent,false);
        return MyViewHolder(v)
    }

    override fun getItemCount() = shipments.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val shipment = shipments.get(holder.adapterPosition)
        val distributionOrigin = shipment.distributionOrigin
        val distributionDestination = shipment.distributionDestination

        holder.apply {
            status.text = statusShipment( shipment.status )
            from.text = shipment.sender.fullName
            to.text = shipment.receiver.fullName
            originNameAddress.text = String.format("%s, %s %s",distributionOrigin.name,distributionOrigin.address.state,distributionOrigin.address.country)
            destinationNameAddress.text = String.format("%s, %s %s",distributionDestination.name,distributionDestination.address.state,distributionDestination.address.country)
            fromDirection.text = distributionOrigin.address.direction
            toDirection.text = distributionDestination.address.direction

            fromDate.text = Date(shipment.sendDate).formatMedium()
            toDate.text = Date(shipment.receiveDate).formatMedium()

            info.text = shipment.info


            val spannableString = SpannableString( shipment.trackingId )

            val clickableSpan: ClickableSpan = object : ClickableSpan() {
                override fun onClick( widget: View) {
                    var nav = widget.findNavController()
                    var bundle = Bundle()
                    bundle.putString("TRACKING_ID",shipment.trackingId)
                    nav.navigate(R.id.action_navigation_shipment_to_statusShipmentFragment,bundle)
                }
            }



            spannableString.setSpan(clickableSpan, 0, shipment.trackingId.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            trackingNumber.text = (spannableString)
            trackingNumber.movementMethod = LinkMovementMethod.getInstance()



        }
        holder.itemView.setOnClickListener {
            val navController = it.findNavController()
            val bundle = Bundle()

            bundle.putString("SHIPMENT_ID",shipment.id.toString()) // id will be string
            navController.navigate(
                R.id.action_navigation_shipment_to_shipmentDetailFragment,
                bundle
            )
        }


    }
    private fun Date.formatMedium(): String {
        var dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM)
        return dateFormat.format(this)
    }
    private fun statusShipment(status: ShipmentsStatus) =
        when (status) {
            ShipmentsStatus.PROCESSED -> "Traité"
            ShipmentsStatus.SENT -> "Envoyé"
            ShipmentsStatus.CUSTOMS -> "Duane"
            ShipmentsStatus.CENTER_DISTRIBUTION -> "Centre de distribution"
            ShipmentsStatus.RETIRED -> "Retiré"
        }

}