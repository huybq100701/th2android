package com.example.th2android.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.th2.R;
import com.example.th2android.model.Item;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private List<Item> mSearch;

    public SearchAdapter() {
        mSearch = new ArrayList<>();
    }

    public void setListDog(List<Item> mSearch) {
        this.mSearch = mSearch;
        notifyDataSetChanged();
    }

    public List<Item> getlist() {
        return mSearch;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent,false);

        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {

        Item item = mSearch.get(position);
        holder.name.setText(item.getName());
        holder.author.setText(item.getAuthor());
        holder.date.setText(item.getDate());
        holder.phamvi.setText(item.getPhamvi());
        holder.doituong.setText(item.getDoituong());
        holder.rating.setText(item.getRating());
    }

    @Override
    public int getItemCount() {
        return mSearch.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder{

        private TextView name,author, doituong, phamvi, date,rating;
        public SearchViewHolder(@NonNull View view) {
            super(view);
            name = itemView.findViewById(R.id.tvName);
            author = itemView.findViewById(R.id.tvAuthor);
            date = itemView.findViewById(R.id.tvDate);
            doituong = itemView.findViewById(R.id.tvDoituong);
            phamvi = itemView.findViewById(R.id.tvPhamvi);
            rating = itemView.findViewById(R.id.tvRating);

        }
    }

}
