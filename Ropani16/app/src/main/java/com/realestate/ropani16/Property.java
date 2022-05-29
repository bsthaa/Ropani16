package com.realestate.ropani16;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Property extends RecyclerView.Adapter<Property.PropertyViewHolder> implements Filterable {
    private ArrayList<PropertyDesc> mPropertyList;
    private ArrayList<PropertyDesc> mPropertyListFull;

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class PropertyViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mTextView;

        public PropertyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.rImg);
            mTextView = itemView.findViewById(R.id.rName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION)
                            listener.onItemClick(position);
                    }
                }
            });
        }
    }

    public Property(ArrayList<PropertyDesc> propertyList) {
        mPropertyList = propertyList;
        mPropertyListFull = new ArrayList<>(propertyList);
    }

    @NonNull
    @Override
    public PropertyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards,parent,false);
        PropertyViewHolder rvh = new PropertyViewHolder(v, mListener);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyViewHolder holder, int position) {
        PropertyDesc currentItem = mPropertyList.get(position);

        holder.mImageView.setImageResource(currentItem.getbImageResource());
        holder.mTextView.setText(currentItem.getbPropertyName());
    }

    @Override
    public int getItemCount() {
        return mPropertyList.size();
    }

    @Override
    public Filter getFilter() {
        return propertyFilter;
    }

    private Filter propertyFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<PropertyDesc> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0)
                filteredList.addAll(mPropertyListFull);
            else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(PropertyDesc item : mPropertyListFull){
                    if(item.getbPropertyName().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            mPropertyList.clear();
            mPropertyList.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };
}
