package com.sonderben.kagom.ui.distribution_center;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sonderben.kagom.KMRetrofit;
import com.sonderben.kagom.data.DistributionCenter;
import com.sonderben.kagom.retrofitRepository.DistributionRetrofitRepository;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DistributionCenterViewModel extends ViewModel {

    private final MutableLiveData<List<DistributionCenter>> mText;

    public DistributionCenterViewModel() {
        mText = new MutableLiveData<>();
        Retrofit retrofit = KMRetrofit.getInstanceRetrofit();
        DistributionRetrofitRepository a = retrofit.create(DistributionRetrofitRepository.class);
        Call<List<DistributionCenter>> callDistributionCenter = a.distributions();
        callDistributionCenter.enqueue(new Callback<List<DistributionCenter>>() {
            @Override
            public void onResponse(Call<List<DistributionCenter>> call, Response<List<DistributionCenter>> response) {
                if (response.isSuccessful()){
                    mText.setValue( response.body() );
                }else {
                    try {
                        JSONObject jsonError=new JSONObject(response.errorBody().string());

                        int status=jsonError.getInt("status");

                        if(status==404){
                            System.out.println("distribution: "+status);
                        }

                    }catch (Exception e){}
                }
            }

            @Override
            public void onFailure(Call<List<DistributionCenter>> call, Throwable t) {
                System.out.println("distribution: "+t);
            }
        });
    }

    public void deleteAll(String bibleInfoId){

        /*Completable.fromAction( ()-> dao.deleteAll( bibleInfoId ) )
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();*/
    }

    public LiveData<List<DistributionCenter>> getText() {
        return mText;
    }
}