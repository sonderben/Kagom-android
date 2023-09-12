package com.sonderben.kagom.ui.init.login;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sonderben.kagom.KMRetrofit;
import com.sonderben.kagom.data.Login;
import com.sonderben.kagom.data.LoginResponse;
import com.sonderben.kagom.retrofitRepository.CustomerRepository;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginViewModel extends ViewModel {

    private final MutableLiveData<LoginResponse> mText = new MutableLiveData<>();

    public LoginViewModel() {

    }
    public void login(String email, String pwd) {
        Retrofit retrofit = KMRetrofit.getInstanceRetrofit();

        CustomerRepository userRepository = retrofit.create(CustomerRepository.class);


        Call<LoginResponse> call = userRepository.login(new Login(email, pwd));
        Log.i("sdbtag", "onResponse: deyo net");
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {

                    mText.setValue(response.body());
                    Log.i("sdbtag", "onResponse: " + response.body());

                } else {
                    try {
                        JSONObject jsonError = new JSONObject(response.errorBody().string());

                        int status = jsonError.getInt("status");
                        Log.i("sdbtag", "onResponse: " + status);

                        if (status == 404) {
                            //Log.i("sdbtag", "onResponse: "+status);
                        }

                    } catch (Exception e) {
                        Log.i("sdbtag", e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.i("sdbtag", "onResponse: "+t);
            }

        });
    }

    public LiveData<LoginResponse> getLoginResponse() {
        return mText;
    }
}