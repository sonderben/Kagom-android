package com.sonderben.kagom.ui.shipment;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;
import com.sonderben.kagom.KMRetrofit;
import com.sonderben.kagom.R;
import com.sonderben.kagom.data.Shipment;
import com.sonderben.kagom.databinding.FragmentShipmentBinding;
import com.sonderben.kagom.retrofitRepository.ShipmentRepository;
import com.sonderben.kagom.utils.KMPreferences;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ShipmentFragment extends Fragment {

    private FragmentShipmentBinding binding;
    private RecyclerView recyclerView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ShipmentViewModel shipmentViewModel =
                new ViewModelProvider(this).get(ShipmentViewModel.class);

        binding = FragmentShipmentBinding.inflate(inflater, container, false);
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        View root = binding.getRoot();
        kmPreferences = new KMPreferences(getActivity().getApplicationContext());
        TextInputLayout textInputLayout = binding.inputLayout;
        Drawable drawable = textInputLayout.getEndIconDrawable();

        int color = ContextCompat.getColor(getContext(), R.color.secondary);
        PorterDuffColorFilter colorFilter = new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN);


        drawable.setColorFilter(colorFilter);
        textInputLayout.setEndIconOnClickListener(v -> {

            NavController navController = Navigation.findNavController(v);


            var bundle = new Bundle();
            bundle.putString("TRACKING_ID",textInputLayout.getEditText().getText().toString() );
            navController.navigate(R.id.action_navigation_shipment_to_statusShipmentFragment,bundle);
        });

        //GET http://localhost:8080/api/v1/shipments/search?paid=true&receiver=1
        Retrofit retrofit = KMRetrofit.getInstanceRetrofit();
        ShipmentRepository shipmentRepository = retrofit.create(ShipmentRepository.class);
        Map<String,Object> option = new HashMap<>();
        option.put("paid",true);
        option.put("receiver",kmPreferences.getIdCurrentUser());
        Call<List<Shipment>> shipmentCall = shipmentRepository.queryShipments(option, "Bearer "+kmPreferences.getJwt());
        Request a = shipmentCall.request();


        shipmentCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Shipment>> call, Response<List<Shipment>> response) {

                if (response.isSuccessful()) {
                    recyclerView.setAdapter(new ShipmentAdapter(response.body()));
                } else {
                    try {
                        JSONObject jsonError = new JSONObject(response.errorBody().string());

                        int status = jsonError.getInt("status");


                        if (status == 404) {
                            //Log.i("sdbtag", "onResponse: "+status);
                        }

                    } catch (Exception e) {
                        Log.i("sdbtag", e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Shipment>> call, Throwable t) {
                Toast.makeText(getContext(), "coo2: " + t.getMessage(), Toast.LENGTH_LONG).show();
                System.out.println("coo2 throwable: " + t);
            }
        });





        return root;
    }
    KMPreferences kmPreferences;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}