package com.sonderben.kagom.ui.distribution_center;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sonderben.kagom.data.DistributionCenter;
import com.sonderben.kagom.databinding.FragmentDistributionCenterBinding;
import com.sonderben.kagom.entity.DistributionEntity;
import com.sonderben.kagom.utils.KMPreferences;
import com.sonderben.kagom.utils.SpaceDecoration;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DistributionCenterFragment extends Fragment {

    private FragmentDistributionCenterBinding binding;
    private RecyclerView recyclerView;
    private KMPreferences kmPreferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DistributionCenterViewModel distributionCenterViewModel =
                new ViewModelProvider(this).get(DistributionCenterViewModel.class);
        binding = FragmentDistributionCenterBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = binding.recyclerView;
        kmPreferences = new KMPreferences( getContext() );

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration( new SpaceDecoration(30) );


        distributionCenterViewModel.getText().observe(getViewLifecycleOwner(), distributionCenters -> {
            System.out.println("distribution: "+distributionCenters);
            for (int i = 0; i < distributionCenters.size(); i++) {
                if (distributionCenters.get(i).getId().equals( Long.valueOf(kmPreferences.getIdDistribution() ) )){
                    Collections.swap(distributionCenters,i,0);
                    break;
                }
            }

            recyclerView.setAdapter( new DistributionAdapter( distributionCenters ) );
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}