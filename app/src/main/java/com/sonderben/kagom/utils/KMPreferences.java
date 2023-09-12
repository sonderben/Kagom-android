package com.sonderben.kagom.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class KMPreferences {
    private static SharedPreferences settingPreferences = null;
    SharedPreferences preference;
    Context context;
    private final String KM_PREFERENCES= "KM_PREFERENCES";


    public KMPreferences(Context context){
        this.context = context;
        preference = context.getSharedPreferences(KM_PREFERENCES,Context.MODE_PRIVATE);
        settingPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String getJwt(){
        return preference.getString("JWT",null);
    }

    public boolean setJwt(String jwt){
        SharedPreferences.Editor editor = preference.edit();
        editor.putString("JWT", jwt);
        return editor.commit(); //commit the changes asynchronously
    }

    public String getIdDistribution(){
        return preference.getString("ID_DISTRIBUTION",null);
    }
    public boolean setIdDistribution(String idDistribution){
        SharedPreferences.Editor editor = preference.edit();
        editor.putString("ID_DISTRIBUTION",idDistribution);
        return editor.commit();
    }

    public long getIdCurrentUser (){
        return preference.getLong("ID_USER",-1);
    }
    public boolean setIdCurrentUser(long idCurrentUser){
        SharedPreferences.Editor editor = preference.edit();
        editor.putLong("ID_USER",idCurrentUser);
        return editor.commit();
    }
    //
    public String getEmailCurrentUser (){
        return preference.getString("EMAIL_USER","");
    }
    public boolean setEmailCurrentUser(String email){
        SharedPreferences.Editor editor = preference.edit();
        editor.putString("EMAIL_USER",email);
        return editor.commit();
    }


    public void logOut(){
        setJwt(null);
        setEmailCurrentUser(null);
        setIdCurrentUser(-1);
        setIdCurrentUser(-1);
    }

}
