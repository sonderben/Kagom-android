package com.sonderben.kagom.ui.init.signup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.sonderben.kagom.AutoCompleteAdapter;
import com.sonderben.kagom.KMRetrofit;
import com.sonderben.kagom.R;
import com.sonderben.kagom.data.Address;
import com.sonderben.kagom.data.Customer;
import com.sonderben.kagom.data.DistributionCenter;
import com.sonderben.kagom.database.KagomDb;
import com.sonderben.kagom.entity.AddressEntity;
import com.sonderben.kagom.entity.CustomerEntity;
import com.sonderben.kagom.retrofitRepository.CustomerRepository;
import com.sonderben.kagom.ui.distribution_center.DistributionCenterViewModel;
import com.sonderben.kagom.utils.Converter;
import com.sonderben.kagom.utils.KMPreferences;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class SignupFragment extends Fragment {


    public SignupFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_signup, container, false);

        init(root);
        onCreateMenu();
        mKp = new KMPreferences( getContext() );

        root.findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean a = setError();
                if (a==true) {
                    signUp(createCustomer());

                    NavController nav = Navigation.findNavController(v);
                    nav.popBackStack();
                }
            }
        });

        if (getArguments() != null){
             mCustomerId = Long.valueOf( getArguments().getString("CUSTOMER_ID") );
            root.findViewById(R.id.layout_save).setVisibility(View.GONE);

            mKagomDb.customerDao().getCurrentCustomerById(mCustomerId).observe(getViewLifecycleOwner(), new Observer<CustomerEntity>() {
                @Override
                public void onChanged(CustomerEntity customerEntity) {
                    setCustomer(customerEntity);
                    mOldCustomer = Converter.convert( customerEntity );

                    mKagomDb.addressDao().getAddress( customerEntity.getAddress_id() ).observe(getViewLifecycleOwner(), new Observer<AddressEntity>() {
                        @Override
                        public void onChanged(AddressEntity addressEntity) {
                            setAddress(addressEntity);
                            mOldCustomer.setAddress( Converter.convert(addressEntity) );
                        }
                    });


                }
            });
        }

        mAutoCompleteTextView =  root.findViewById(R.id.autoComplete_view);

        mAutoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {
            AutoCompleteAdapter adapter = (AutoCompleteAdapter) parent.getAdapter();

             mDistributionCenter = adapter.getItem( position );
             Address a = mDistributionCenter.getAddress();

             mAutoCompleteTextView.setText(String.format("%s. %s, %s.",a.getDirection(),a.getCity(),a.getCountry()));

        });



        DistributionCenterViewModel distributionCenterViewModel =
                new ViewModelProvider(this).get(DistributionCenterViewModel.class);

        distributionCenterViewModel.getText().observe(getViewLifecycleOwner(), distributionCenters -> {


            mAutoCompleteAdapter = new AutoCompleteAdapter(getContext(),distributionCenters);

            mAutoCompleteTextView.setAdapter(mAutoCompleteAdapter);

            for (DistributionCenter de: distributionCenters) {
                if ( mKp.getIdDistribution()!=null && de.getId().equals( Long.valueOf( mKp.getIdDistribution() ) )){
                    this.mDistributionCenter = de;
                    break;
                }
            }





        });




        return root;
    }

    private void setCustomer(CustomerEntity customer) {
        fullName.setText( customer.getFullName() );
        email.setText( customer.getEmail() );
        telephone.setText( customer.getTelephone() );
        passport.setText( customer.getCountryIdentity() );
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date( customer.getBirthday() ));
        birthday.setText( String.format("%s / %s / %s",cal.get( cal.get(Calendar.DAY_OF_MONTH) ),cal.get( Calendar.MONTH )+1,cal.get(Calendar.YEAR)) );
    }

    private void setAddress(AddressEntity address){
        country.setText(address.getCountry());
        state.setText( address.getState() );
        city.setText( address.getCity() );
        direction.setText( address.getDirection() );
        postalCode.setText( address.getCodePostal() );
    }

    //AutoCompleteTextView autoCompleteTextView;
    AutoCompleteAdapter mAutoCompleteAdapter;
    TextInputEditText fullName,email,telephone,birthday,country,state,city,postalCode,direction,password,confirmPassword,passport;
    TextInputLayout fullNameLayout,emailLayout,telephoneLayout,birthdayLayout,countryLayout,
            stateLayout,cityLayout,postalCodeLayout,directionLayout,passwordLayout,
            confirmPasswordLayout,passportLayout,distributionLayout;
    DistributionCenter mDistributionCenter;
    MaterialAutoCompleteTextView mAutoCompleteTextView;

    private void  init(@NonNull View root){
        mKagomDb = KagomDb.getInstance(getContext());

        fullName = root.findViewById(R.id.full_name_);
        fullNameLayout = root.findViewById(R.id.full_name);


        email = root.findViewById(R.id.email_);
        emailLayout = root.findViewById(R.id.email);


        telephone = root.findViewById(R.id.telephone_);
        telephoneLayout = root.findViewById(R.id.telephone);

        birthday = root.findViewById(R.id.birthday_);
        birthdayLayout = root.findViewById(R.id.birthday);


        country = root.findViewById(R.id.country_);
        countryLayout = root.findViewById(R.id.country);


        state = root.findViewById(R.id.state_);
        stateLayout = root.findViewById(R.id.state);


        city = root.findViewById(R.id.city_);
        cityLayout = root.findViewById(R.id.city);

        postalCode = root.findViewById(R.id.postal_Code_);
        postalCodeLayout = root.findViewById(R.id.postal_Code);

        directionLayout = root.findViewById(R.id.direction);
        direction = root.findViewById(R.id.direction_);

        passportLayout = root.findViewById(R.id.passport);
        passport = root.findViewById(R.id.passport_);


        password = root.findViewById(R.id.password_);
        passwordLayout = root.findViewById(R.id.password);

        confirmPasswordLayout = root.findViewById(R.id.confirm_password);
        confirmPassword = root.findViewById(R.id.confirm_password_);

        distributionLayout = root.findViewById(R.id.distribution_center);


        //setError();

    }

    private void onCreateMenu() {
        requireActivity().addMenuProvider(new MenuProvider() {


            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                //menu.clear();
                menuInflater.inflate(R.menu.menu_sign_up, menu);
                menu.findItem(R.id.save).setIcon(R.drawable.check);

            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {

                if (menuItem.getItemId() == R.id.save) {
                    if (mCustomerId != null && setError()){
                        Customer temp = createCuctomer4Update();
                        if (temp != null){
                            update( createCuctomer4Update() );
                            getActivity().onBackPressed();
                        }


                    }
                    //return true;
                }


                return true;
            }
        }, getViewLifecycleOwner(), Lifecycle.State.RESUMED);
    }

    private boolean setError(){
         boolean a = true;
        emailLayout.setError( null );
        telephoneLayout.setError( null );
        birthdayLayout.setError( null );
        countryLayout.setError( null );
        stateLayout.setError( null );
        cityLayout.setError( null );
        postalCodeLayout.setError( null );
        directionLayout.setError( null );
        passwordLayout.setError( null );
        confirmPasswordLayout.setError( null );
        passportLayout.setError( null );
        birthdayLayout.setError( null );

        if (fullName.getText().toString().length() < 6 || fullName.getText().toString().split(" ").length != 2) {
            a = false;
            fullNameLayout.setError("Le nom complet doit avoir 6 caractères minimum et doit contenir le nom et le prénom.");
        }

        if (passport.getText().toString().length() < 4){
            a = false;
            passportLayout.setError( "Le numero d'identité doir avoir 6 caractères minimum" );
        }

        Pattern pattern = Pattern.compile(
                "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"
        );
        Matcher matcher = pattern.matcher(email.getText().toString());




        if (! matcher.matches() ){
            a = false;
            emailLayout.setError( "L'email n'est pas valide." );
        }else {
            emailLayout.setError(null);
        }


        Pattern patternDate = Pattern.compile( "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$" );
        Matcher matcherDAte = patternDate.matcher( birthday.getText().toString() );

        if ( !matcherDAte.matches() ){
            a = false;
            birthdayLayout.setError("Le format de la date doit ressemble a 23 / 04 / 1995");
        }

        if (mDistributionCenter == null){
            a = false;
            distributionLayout.setError("Vous devez choisir un centre de distribution");
        }

        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?!.*\\s).{8,}$";
        Pattern patternPwd = Pattern.compile( regex );
        Matcher matcherPwd = patternPwd.matcher( password.getText().toString() );
        if (!matcherPwd.matches()){
            a = false;
            password.setError("Le mot de passe doit contenir:\n"+
                    "\tAu moins une lettre majuscule.\n" +
                    "\tAu moins une lettre minuscule.\n" +
                    "\tAu moins un chiffre.\n" +
                    "\tAu moins un caractère spécial.\n" +
                    "\tAucun espace n’est autorisé.\n" +
                    "\tLongueur minimale de 8 caractères.");
        }

        if ( !password.getText().toString().equals(confirmPassword.getText().toString()) ){
            a = false;
            confirmPasswordLayout.setError("Le mot de passe ne correspond pas");
        }

        return a;

    }

    private Customer createCustomer(){
        Customer c = new Customer();

        String [] dates = birthday.getText().toString().split("/");
        if ( dates.length == 3 ){

            Calendar calendar = Calendar.getInstance();
            calendar.set( Integer.parseInt(dates[0].trim()),Integer.parseInt(dates[1].trim()),Integer.parseInt(dates[2].trim()));

            c.setBirthday(calendar.getTimeInMillis());

        }


        c.setFullName( fullName.getText().toString() );
        c.setTelephone( telephone.getText().toString() );
        c.setEmail( email.getText().toString() );
        c.setPassword( password.getText().toString() );
        c.setCountryIdentity( passport.getText().toString() );


        Address address =new Address();
        address.setCountry( country.getText().toString() );
        address.setCity( city.getText().toString() );
        address.setDirection( direction.getText().toString() );
        address.setState( state.getText().toString() );
        address.setCodePostal( postalCode.getText().toString() );





        c.setDistributionCenter(mDistributionCenter);
        c.setAddress( address );
        return c;
    }

    /**
     * @return
     * el servidor va actualizar solamente los campos no nulos (PATCH)
     */
    private Customer createCuctomer4Update(){
        Customer tempCustomer= createCustomer();
        if ( tempCustomer.equals(mOldCustomer) ){
            return null;
        }

        if (tempCustomer.getEmail().equals( mOldCustomer.getEmail() )){
                tempCustomer.setEmail(null);
        }
        if (tempCustomer.getFullName().equals( mOldCustomer.getFullName() )){
            tempCustomer.setFullName(null);
        }
        if (tempCustomer.getBirthday().equals( mOldCustomer.getBirthday() )){
            tempCustomer.setBirthday(null);
        }
        if (tempCustomer.getEmail().equals( mOldCustomer.getEmail() )){
            tempCustomer.setEmail(null);
        }
        if (tempCustomer.getTelephone().equals( mOldCustomer.getTelephone() )){
            tempCustomer.setTelephone(null);
        }
        if (tempCustomer.getAddress().equals( mOldCustomer.getAddress() )){
            tempCustomer.setAddress(null);
        }
        if (tempCustomer.getDistributionCenter().equals( mOldCustomer.getDistributionCenter() )){
            tempCustomer.setDistributionCenter(null);
        }

        return tempCustomer;


    }

    public void signUp(Customer customer){
        Retrofit retrofit = KMRetrofit.getInstanceRetrofit();
        CustomerRepository customerRepository = retrofit.create(CustomerRepository.class);
        Call<Customer> customerCall = customerRepository.signUp(customer);
        customerCall.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {

                if (response.isSuccessful()){
                    Toast.makeText(getContext(),"todo bien: "+ mDistributionCenter,Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {

            }
        });
    }

    private void update(Customer customer){

    }


    Long mCustomerId;

    private KagomDb mKagomDb;
    private KMPreferences mKp;

    private Customer mOldCustomer;





}















