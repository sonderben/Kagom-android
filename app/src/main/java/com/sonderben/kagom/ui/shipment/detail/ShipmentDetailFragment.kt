package com.sonderben.kagom.ui.shipment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sonderben.kagom.KMRetrofit
import com.sonderben.kagom.R
import com.sonderben.kagom.data.Package
import com.sonderben.kagom.retrofitRepository.PackageRepository
import com.sonderben.kagom.utils.KMPreferences
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class ShipmentDetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_shipment_detail, container, false)
        val recyclerView:RecyclerView = root.findViewById(R.id.recycler_view)
        val kmPreferences = KMPreferences(context)

        recyclerView.layoutManager = LinearLayoutManager(context)


        val retrofit:Retrofit = KMRetrofit.getInstanceRetrofit()

        val packageRepository = retrofit.create(PackageRepository::class.java)

        val SHIPMENT_ID = requireArguments().getString("SHIPMENT_ID")
            .let {
                java.lang.Long.valueOf("1")
                val callListPackages: Call<List<Package>>? = packageRepository.findPackageByShipmentId(java.lang.Long.valueOf(it),"Baerer "+kmPreferences.jwt)

                callListPackages?.enqueue( object: Callback <List<Package>>{
                    override fun onResponse(call: Call<List<Package>>, response: Response<List<Package>>) {
                        if (response.isSuccessful) {
                            recyclerView.adapter = ShipmentDetailAdapter(response.body(),
                                this@ShipmentDetailFragment.requireContext()
                            )
                        } else {
                            try {
                                val jsonError = JSONObject(response.errorBody()!!.string())
                                val status = jsonError.getInt("status")

                                if (status == 404) {

                                }
                            } catch (e: Exception) {

                            }
                        }
                    }

                    override fun onFailure(call: Call<List<Package>>, t: Throwable) {

                    }
                })
            }

        onCreateMenu()






        return root
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