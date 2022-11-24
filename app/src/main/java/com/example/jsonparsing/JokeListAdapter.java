package com.example.jsonparsing;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.ChipGroup;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class JokeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "JokeListAdapter";
    public List<Joke> jokesList = new ArrayList<>();
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        RecyclerView.ViewHolder viewHolder;
        if(viewType==0){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_joke_item, parent, false);
            viewHolder = new SingleViewHolder(view);
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.twopart_joke_item, parent, false);
            viewHolder = new TwoPartViewHolder(view);
        }

        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        if(jokesList.get(position).getType().equals("single")){
            return 0;
        }else{
            return 1;
        }
    }

    public void setJokesList(List<Joke> jokesList) {
        Log.d(TAG, "setJokesList: SetJokesList Called");
        this.jokesList = jokesList;
        notifyDataSetChanged();
    }
    public static interface loader{
        void loadData();
    }
    loader mLoader;

    public void setmLoader(loader mLoader) {
        this.mLoader = mLoader;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(position == jokesList.size()-5){
            mLoader.loadData();
        }
        if(getItemViewType(position)==0){
            SingleViewHolder viewHolder = (SingleViewHolder) holder;
            viewHolder.joke.setText(jokesList.get(position).getJoke());
            viewHolder.category.setText(jokesList.get(position).getCategory());

        }else{
            TwoPartViewHolder viewHolder = (TwoPartViewHolder) holder;
            viewHolder.setup.setText(jokesList.get(position).getSetup());
            viewHolder.delivery.setText(jokesList.get(position).getDelivery());
            viewHolder.category.setText(jokesList.get(position).getCategory());
        }
    }

    @Override
    public int getItemCount() {
        return jokesList.size();
    }

    public class SingleViewHolder extends RecyclerView.ViewHolder{
        public TextView joke;
        public TextView category;
        public ChipGroup flags;

        public SingleViewHolder(@NonNull View itemView) {
            super(itemView);
            joke = itemView.findViewById(R.id.joke);
            category = itemView.findViewById(R.id.category);
//            flags = itemView.findViewById(R.id.flags);
        }
    }

    public class TwoPartViewHolder extends RecyclerView.ViewHolder{
        public TextView setup;
        public TextView delivery;
        public TextView category;
        public ChipGroup flags;

        public TwoPartViewHolder(@NonNull View itemView) {
            super(itemView);
            setup = itemView.findViewById(R.id.setup);
            delivery = itemView.findViewById(R.id.delivery);
            category = itemView.findViewById(R.id.category);
//            flags = itemView.findViewById(R.id.flags);
        }
    }
}
