package com.example.myapp.presentation.favorites;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;
import com.example.myapp.model.BoredAction;
import com.like.LikeButton;

import java.util.ArrayList;
import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    TextView textViewCategory, textViewExplore, textViewPrice, textViewFree;
    ProgressBar progressBar;
    ImageView iconReplace, iconOnePerson, iconTwoPerson, iconThreePerson, iconThreePlusPerson;
    LikeButton likeButton;

    List<BoredAction> addFavList = new ArrayList<>();
    private Object onItemViewClick;
    public FavoritesAdapter(List<BoredAction> addFavList) {
        this.addFavList = addFavList;
    }

    @NonNull
    @Override
    public FavoritesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorites_card, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull FavoritesAdapter.ViewHolder holder, int position) {
        holder.bind(addFavList.get(position));
    }

    @Override
    public int getItemCount() {
        return addFavList.size();
    }

    public interface onItemViewClick {
        void onClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View view) {
            super(view);
            elementView(view);
        }

        public void elementView(View view) {
            textViewCategory = view.findViewById(R.id.category);
            textViewExplore = view.findViewById(R.id.explore_text);
            textViewFree = view.findViewById(R.id.price_text);
            textViewPrice = view.findViewById(R.id.money);
            progressBar = view.findViewById(R.id.progressBar);
            iconReplace = view.findViewById(R.id.icon_replace);
            iconOnePerson = view.findViewById(R.id.icon_one_user);
            iconTwoPerson = view.findViewById(R.id.icon_two_user);
            iconThreePerson = view.findViewById(R.id.icon_three_user);
            iconThreePlusPerson = view.findViewById(R.id.icon_three_plus_user);
            likeButton = view.findViewById(R.id.btn_like);
        }
        @RequiresApi(api = Build.VERSION_CODES.N)

        public void bind(BoredAction boredAction) {
            textViewCategory.setText(boredAction.getType());
            textViewExplore.setText(boredAction.getActivity());
            textViewFree.setText(boredAction.getPrice().toString());
            setImageVisibility();

            switch (boredAction.getParticipants()) {
                case 1: iconReplace.setImageResource(R.drawable.ic_user_1);
                break;
                case 2: iconReplace.setImageResource(R.drawable.ic_user_2);
                break;
                case 3: iconThreePerson.setImageResource(R.drawable.ic_user_3);
                break;
                case 4: iconThreePlusPerson.setImageResource(R.drawable.ic_user_3_plus);
                break;
            }

            progressBar.setProgress((int)(boredAction.getAccessibility()*100), true);
        }
        public void setImageVisibility() {
            iconReplace.setVisibility(View.VISIBLE);
            iconOnePerson.setVisibility(View.GONE);
            iconTwoPerson.setVisibility(View.GONE);
            iconThreePerson.setVisibility(View.GONE);
            iconThreePlusPerson.setVisibility(View.GONE);
        }
    }
}
