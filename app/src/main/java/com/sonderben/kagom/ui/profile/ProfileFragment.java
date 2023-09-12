package com.sonderben.kagom.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputEditText;
import com.sonderben.kagom.InitActivity;
import com.sonderben.kagom.KMRetrofit;
import com.sonderben.kagom.R;
import com.sonderben.kagom.data.Customer;
import com.sonderben.kagom.database.KagomDb;
import com.sonderben.kagom.databinding.FragmentProfileBinding;
import com.sonderben.kagom.entity.AddressEntity;
import com.sonderben.kagom.entity.CustomerEntity;
import com.sonderben.kagom.entity.DistributionEntity;
import com.sonderben.kagom.retrofitRepository.CustomerRepository;
import com.sonderben.kagom.utils.KMPreferences;
import com.sonderben.kagom.utils.Util;

import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfileFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);

        root = binding.getRoot();
        mainLayout = binding.mainLayout;

        root.findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController nav = Navigation.findNavController(v);
                nav.getGraph().findNode(R.id.signupFragment3).setLabel("Mettre à jour");
                Bundle bundle = new Bundle();
                bundle.putString("CUSTOMER_ID",String.valueOf( kp.getIdCurrentUser() ));
                nav.navigate(R.id.action_navigation_profile_to_signupFragment3,bundle);

                //navController.popBackStack(R.id.profileFragment, false);


            }
        });


        init(root);

        onCreateMenu();

        liveDataCustomer.observe(getViewLifecycleOwner(), new Observer<>() {
            @Override
            public void onChanged(CustomerEntity customerEntity) {
                if (customerEntity != null) {

                    setCustomer(customerEntity);
                    kagomDb.addressDao().getAddress(customerEntity.getAddress_id()).observe(getViewLifecycleOwner(), new Observer<AddressEntity>() {
                        @Override
                        public void onChanged(AddressEntity addressEntity) {
                            setAddress(addressEntity);
                        }
                    });
                }
            }
        });

        kagomDb.distributionDao().findById( Long.valueOf( kp.getIdDistribution() ) ).observe(
                getViewLifecycleOwner(), distributionEntity -> {
                    setDistribution(distributionEntity);

                    kagomDb.addressDao().getAddress(distributionEntity.getAddress_id()).observe(getViewLifecycleOwner(), new Observer<AddressEntity>() {
                        @Override
                        public void onChanged(AddressEntity addressEntity) {
                            setAddressDistribution(addressEntity);
                        }
                    });

                }
        );










        return root;
    }

    public void init(View root) {
        kp = new KMPreferences( getContext() );
        retrofit = KMRetrofit.getInstanceRetrofit();
        kagomDb = KagomDb.getInstance( getContext() );
        liveDataCustomer = kagomDb.customerDao().getCurrentCustomerByEmail( kp.getEmailCurrentUser() );


        internationalAddressLab = root.findViewById(R.id.international_address_profile);
        KMIdentityLab = root.findViewById(R.id.km_identity_profile);
        pointLab = root.findViewById(R.id.point_profile);

        fullName = root.findViewById(R.id.full_name_profile);
        email = root.findViewById(R.id.email_profile);
        telephone = root.findViewById(R.id.telephone_profile);
        birthday = root.findViewById(R.id.birthday_profile);

        myCountry = root.findViewById(R.id.my_country_profile);
        myState = root.findViewById(R.id.my_state_profile);
        myCity = root.findViewById(R.id.my_city_profile);
        myCodePostal = root.findViewById(R.id.my_postal_code_profile);
        myDirection = root.findViewById(R.id.my_direction_profile);

        disEmailNameLab = root.findViewById(R.id.dis_email_label_profile);
        disDirectionNameLab = root.findViewById(R.id.dis_direction_label_profile);
        disTelephone = root.findViewById(R.id.dis_tel2_profile);
        disEmail = root.findViewById(R.id.dis_email2_profile);
        disSchedule = root.findViewById(R.id.dis_shedule_profile);

    }


    private void setCustomer(CustomerEntity customer){

        internationalAddressLab.setText(customer.getKmIdentity() + " Usa Florida");


        KMIdentityLab.setText(customer.getKmIdentity());
        pointLab.setText(customer.getPoints() + " Points");
        fullName.setText(customer.getFullName());
        email.setText(customer.getEmail());
        telephone.setText(customer.getTelephone());


        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(customer.getBirthday()));

        birthday.setText( Util.formatDate( cal.getTime() ) );

        fullName.setText( customer.getFullName() );
        email.setText(customer.getEmail());
        telephone.setText(customer.getTelephone());

        birthday.setText(
                String.format("%s / %s / %s",
                        cal.get(Calendar.DAY_OF_MONTH),
                        cal.get(Calendar.MONTH)+1,
                        cal.get(Calendar.YEAR)
                )
        );

    }

    private void setAddress(AddressEntity address){

        myCountry.setText(address.getCountry());
        myCity.setText(address.getCity());
        myState.setText(address.getState());
        myCodePostal.setText(address.getCodePostal());
        myDirection.setText(address.getDirection());



    }

    public void updateCustomer(Customer customer){
        Retrofit retrofit = KMRetrofit.getInstanceRetrofit();
        CustomerRepository customerRepository = retrofit.create(CustomerRepository.class);
        Call<Customer> customerCall = customerRepository.update(kp.getIdCurrentUser(), customer, "Bearer "+kp.getJwt());
        customerCall.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {

                if (response.isSuccessful()){
                    Toast.makeText(getContext(),"update bien: "+response.body(),Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getContext(),"error on response: ",Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                Toast.makeText(getContext(),"error Throwable ",Toast.LENGTH_LONG).show();

            }
        });

    }




    private void setDistribution(DistributionEntity distributionCenter){
        disTelephone.setText(distributionCenter.getTel());
        disEmail.setText(distributionCenter.getEmail());
        disSchedule.setText(distributionCenter.getSchedule());
        disEmailNameLab.setText( distributionCenter.name );
    }

    private void setAddressDistribution(AddressEntity a){
        disDirectionNameLab.setText(String.format("%s, %s %s.", a.getDirection(),  a.getCity(),a.getCountry() ));

    }



    private void onCreateMenu() {
        requireActivity().addMenuProvider(new MenuProvider() {


            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                //menu.clear();
                menuInflater.inflate(R.menu.menu_profile, menu);

            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {

                 if (menuItem.getItemId() == R.id.log_out) {

                    kp.logOut();
                    Intent intent = new Intent(getActivity(), InitActivity.class);

                    getActivity().startActivity(intent);
                    getActivity().finishAffinity();
                }

                return false;
            }
        }, getViewLifecycleOwner(), Lifecycle.State.RESUMED);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    

    private FragmentProfileBinding binding;
    private LinearLayout mainLayout;

    private TextView internationalAddressLab
            , KMIdentityLab
            , pointLab
            , disEmailNameLab
            , disDirectionNameLab
            ;


    KMPreferences kp;
    private TextInputEditText fullName, email, telephone,
            birthday, myCountry, myState, myCity, myCodePostal, myDirection,
            disTelephone, disEmail, disSchedule;

    ///////////




    private Retrofit retrofit;
    private KagomDb kagomDb;
    private LiveData<CustomerEntity> liveDataCustomer;
    private LiveData<AddressEntity> liveDataAddress;
    private View root;




}