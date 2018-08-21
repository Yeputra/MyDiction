package com.freaky.id.mydiction.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freaky.id.mydiction.R;
import com.freaky.id.mydiction.data.model.Kamus;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class KamusAdapter extends RecyclerView.Adapter<KamusAdapter.KamusViewHolder> {
    private ArrayList<Kamus> kamusList;

    public KamusAdapter(ArrayList<Kamus> kamusList) {
        this.kamusList = kamusList;
    }

    public KamusAdapter() {

    }

    @Override
    public KamusAdapter.KamusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kamus_item, parent, false);
        KamusAdapter.KamusViewHolder viewHolder = new KamusAdapter.KamusViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(KamusAdapter.KamusViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: " + position);
        holder.tvKata.setText(kamusList.get(position).getKata());
        holder.tvTerjemahan.setText(kamusList.get(position).getTerjemahan());
    }

    @Override
    public int getItemCount() {
        return kamusList.size();
    }

    public void setData(ArrayList<Kamus> kamusList) {
        this.kamusList = kamusList;
        notifyDataSetChanged();
    }

    public class KamusViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvKata;
        TextView tvTerjemahan;

        public KamusViewHolder(View itemView) {
            super(itemView);

            tvKata = itemView.findViewById(R.id.tv_kata);
            tvTerjemahan = itemView.findViewById(R.id.tv_terjemahan);

            itemView.setOnClickListener(this);

            tvKata.setSelected(true);
        }

        @Override
        public void onClick(View v) {
        }
    }
}
