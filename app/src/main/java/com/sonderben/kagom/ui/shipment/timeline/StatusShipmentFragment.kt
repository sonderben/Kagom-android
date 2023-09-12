package com.sonderben.kagom.ui.shipment.timeline


import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.MenuProvider

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.sonderben.kagom.KMRetrofit
import com.sonderben.kagom.R
import com.sonderben.kagom.data.ShipmentTracking
import com.sonderben.kagom.data.ShipmentsStatus
import com.sonderben.kagom.retrofitRepository.ShipmentTrackingRepository
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.util.Date


class StatusShipmentFragment : Fragment() {


    lateinit var orderTrackingView: ShippingTimelineView

     var trackingId: String?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_status_shipment, container, false)
        orderTrackingView= view.findViewById(R.id.order_tracking_view)

        trackingId = arguments?.getString("TRACKING_ID")


        onCreateMenu()
        val retrofit = KMRetrofit.getInstanceRetrofit()

       val shipmentTrackingRepository =  retrofit.create(ShipmentTrackingRepository::class.java)

       val callShipmentTracking: Call<ShipmentTracking?>? = trackingId?.let {
           shipmentTrackingRepository.shipmentTracking(it/*"KMTS-1234-A"*/)
       }


        callShipmentTracking?.enqueue(object : Callback<ShipmentTracking?> {
            override fun onResponse(call: Call<ShipmentTracking?>, response: Response<ShipmentTracking?>) {

                if (response.isSuccessful){

                    val shipmentTracking = response.body();

                    println("respomse: ${response.body()}")
                   val possibleStatusList:List<String> =
                       shipmentTracking?.possibleStatus!!.map { ps -> translatePossibleStatus(ps) }

                    val status = shipmentTracking.shipmentsStatus

                    val percent:Float = shipmentTracking.shipmentsStatusPercent;

                    val array = mutableListOf<View>()

                    array.apply {
                        var a:Long = 1200
                        var end = 0
                        for (ba in possibleStatusList){
                            a++
                            val linearLayout = LinearLayout(context)
                            linearLayout.orientation = LinearLayout.VERTICAL

                            val view1 = TextView(context)
                            view1.text = ba

                            linearLayout.addView( view1 )



                            if (end == 0){
                                val view2 = TextView(context)
                                view2.text = Date( (a*1112) ).formatMedium()
                                linearLayout.addView( view2 )
                            }

                            if ( ba.equals(translatePossibleStatus(status)) ){
                                end = 1
                            }




                            add(linearLayout)

                        }
                    }


                    orderTrackingView.setEntryView(array as List<View>?)



                    orderTrackingView.setPercentComplete(percent)

                }
                else{
                    try {
                        val jsonError = JSONObject(response.errorBody()!!.string())
                        val status = jsonError.getInt("status")
                        if (status == 404) {

                        }
                    } catch (_: Exception) {
                    }
                }

            }

            override fun onFailure(call: Call<ShipmentTracking?>, t: Throwable) {
            }

        })












        return view;
    }

    companion object {


        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StatusShipmentFragment().apply {

            }
    }

    private fun Date.formatMedium(): String {
        var dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM)
        return dateFormat.format(this)
    }
    private fun translatePossibleStatus(status: ShipmentsStatus) =
        when(status){
            ShipmentsStatus.PROCESSED -> "Traité"
            ShipmentsStatus.SENT -> "Envoyé"
            ShipmentsStatus.CUSTOMS -> "Duane"
            ShipmentsStatus.CENTER_DISTRIBUTION -> "Centre de distribution"
            ShipmentsStatus.RETIRED -> "Retiré"
        }
    private fun onCreateMenu() {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                //menu.clear();

            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                activity?.onBackPressed()
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }


}