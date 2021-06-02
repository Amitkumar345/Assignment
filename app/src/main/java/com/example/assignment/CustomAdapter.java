package com.example.assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.w3c.dom.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private List<Crew> crewList =new ArrayList<>();
    Context mn;
    public CustomAdapter(Context context){
        this.mn = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        Crew currCrew = crewList.get(position);
        holder.name.setText(currCrew.getName());
        holder.agency.setText(currCrew.getAgency());
        holder.wikiLink.setText(currCrew.getWikiLink());
        holder.status.setText(currCrew.getStatus());
        String imgUrl = currCrew.getImgLink();
        if(isValidUrl(imgUrl)){
            Glide.with(mn)
                    .load(imgUrl)
                    .error(R.mipmap.ic_launcher)
                    .apply(RequestOptions.circleCropTransform())
                    .into(holder.img);
        }
    }
    private static boolean isValidUrl(String url){
        /* Try creating a valid URL */
        try {
            new URL(url).toURI();
            return true;
        }
        // If there was an Exception while creating URL object
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return crewList.size();
    }
    public void setCrewList(List<Crew> crewList){
        this.crewList = crewList;
        notifyDataSetChanged();
     }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name,agency,wikiLink,status;
        private ImageView img;
        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            agency = view.findViewById(R.id.agency);
            wikiLink = view.findViewById(R.id.wikipedia);
            status = view.findViewById(R.id.status);
            img = view.findViewById(R.id.image);
        }
    }
}
