package com.example.administrator.projectlogin.view.adapter;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.projectlogin.R;
import com.example.administrator.projectlogin.Util;
import com.example.administrator.projectlogin.model.Hero;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeroListAdapter extends RecyclerView.Adapter<HeroListAdapter.HeroCardViewHolder> {
    private Context context;
    private List<Hero> heroes;

    public HeroListAdapter(Context context, List<Hero> heroes) {
        this.context = context;
        this.heroes = heroes;
    }

    @Override
    public HeroListAdapter.HeroCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_card_view, parent, false);

        return new HeroCardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HeroListAdapter.HeroCardViewHolder holder, int position) {
        Util.setDownloadImageView(
                context
                , heroes.get(position).getImage()
                , holder.logoImageView
        );

        holder.heroName.setText(heroes.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return heroes.size();
    }

    class HeroCardViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_view)
        CardView cardView;

        @BindView(R.id.logo_image)
        ImageView logoImageView;

        @BindView(R.id.hero_name)
        TextView heroName;

        HeroCardViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
