package com.sonderben.kagom.ui.init.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.sonderben.kagom.MainActivity;
import com.sonderben.kagom.R;
import com.sonderben.kagom.dao.AddressDao;
import com.sonderben.kagom.dao.CustomerDao;
import com.sonderben.kagom.dao.DistributionCenterDao;
import com.sonderben.kagom.data.Customer;
import com.sonderben.kagom.data.LoginResponse;
import com.sonderben.kagom.database.KagomDb;
import com.sonderben.kagom.entity.CustomerEntity;
import com.sonderben.kagom.utils.Converter;
import com.sonderben.kagom.utils.KMPreferences;


public class LoginFragment extends Fragment {




    public LoginFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_login, container, false);
        loginViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(LoginViewModel.class);
        kmPreferences = new KMPreferences(getActivity().getApplicationContext());
        signup = root.findViewById(R.id.sign_up);
        login = root.findViewById(R.id.login);

        email = root.findViewById(R.id.email);
        password = root.findViewById(R.id.password);

        KagomDb db = KagomDb.getInstance(getContext());
        CustomerDao customerDao = db.customerDao();
        AddressDao addressDao = db.addressDao();
        DistributionCenterDao distributionDao = db.distributionDao();

        /*db.customerDao().getCurrentCustomer(kmPreferences.getEmailCurrentUser()).observe(getViewLifecycleOwner(), new Observer<CustomerEntity>() {
            @Override
            public void onChanged(CustomerEntity customerEntity) {
                System.out.println("customerDao: "+customerEntity);
            }
        });*/

        if (kmPreferences.getJwt() != null && kmPreferences.getJwt().length()>10){
            Intent intent = new Intent(LoginFragment.this.getActivity(), MainActivity.class);
            getActivity().startActivity( intent );

            getActivity().finish();
        }



        login.setOnClickListener(x->{
            loginViewModel.login(email.getText().toString(),password.getText().toString());
        });

        loginViewModel.getLoginResponse().observe(getActivity(), loginResponse -> {

            kmPreferences.setJwt( loginResponse.getJwt() );
            kmPreferences.setIdCurrentUser( loginResponse.getCustomer().getId() );
            kmPreferences.setEmailCurrentUser( loginResponse.getCustomer().getEmail() );

            //Toast.makeText(getContext(), "Bienvenue " + loginResponse.getCustomer().getFullName(), Toast.LENGTH_LONG).show();


            String idDistribution = String.valueOf( loginResponse.getCustomer().getDistributionCenter().getId() );
            kmPreferences.setIdDistribution( idDistribution );
            Customer customer = loginResponse.getCustomer();


            long addressCustomerId = addressDao.insert( Converter.convert(customer.getAddress()) );
            long addressDistributionId = addressDao.insert( Converter.convert( customer.getDistributionCenter().getAddress() ) );
            long distributionID = distributionDao.insert( Converter.convert( customer.getDistributionCenter(), addressDistributionId ) );
            long customerID = customerDao.insert( Converter.convert( customer, addressCustomerId, distributionID ) );



            System.out.println("last check: "+String.format("addressCustomerId = %s, addressDistributionId = %s, distributionID = %s, customerID = %s",
                    addressCustomerId,addressDistributionId,distributionID,customerID));

            Intent intent = new Intent(LoginFragment.this.getActivity(), MainActivity.class);
            getActivity().startActivity(intent);
        });


        goToSignUp();
        return root;
    }
    private TextView signup;
    Button login;
    private void goToSignUp(){
        String text = signup.getText().toString();
        Spannable spannable = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                NavController navController = Navigation.findNavController(widget);
                navController.getGraph().findNode(R.id.signup2Fragment).setLabel("Creer un compte");
                navController.navigate(R.id.action_loginFragment_to_signup2Fragment);
            }
        };




        int startIndex = text.indexOf("Creer un compte.");
        spannable.setSpan(clickableSpan, startIndex, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(R.color.primaryVariant));
        spannable.setSpan(colorSpan, startIndex, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        signup.setText(spannable);
        signup.setMovementMethod(LinkMovementMethod.getInstance());

    }
    private LoginViewModel loginViewModel;
    private KMPreferences kmPreferences;

    private TextInputEditText email,password;


}