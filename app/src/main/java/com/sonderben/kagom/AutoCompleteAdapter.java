package com.sonderben.kagom;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sonderben.kagom.data.Address;
import com.sonderben.kagom.data.DistributionCenter;

import java.util.List;

public class AutoCompleteAdapter extends ArrayAdapter<DistributionCenter> implements Filterable {

    List<DistributionCenter>objects;
    CharSequence mConstraint;
    public AutoCompleteAdapter(@NonNull Context context,  @NonNull List<DistributionCenter>objects) {
        super(context, 0);
        this.objects = objects;

    }

    @Nullable
    @Override
    public DistributionCenter getItem(int position) {
        return objects.get(position);
    }

    @Override
    public int getCount() {
        return objects.size();
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Object object = getItem(position);



            //if(convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item,parent,false);
                TextView textView = convertView.findViewById(R.id.text_view);
            //}


            DistributionCenter dc = (DistributionCenter) object;
            Address a = dc.getAddress();
            textView.setTextColor(getContext().getColor(R.color.primary));
            textView.setText( String.format("%s. %s, %s.",a.getDirection(),a.getCity(),a.getCountry()) );




        return convertView;
    }


    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            public CharSequence convertResultToString(Object resultValue) {
                return mConstraint;
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                mConstraint = constraint;
                FilterResults filterResults = new FilterResults();
                filterResults.count=objects.size();
                filterResults.values=objects;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                notifyDataSetChanged();
            }
        };
        return filter;
    }

}
