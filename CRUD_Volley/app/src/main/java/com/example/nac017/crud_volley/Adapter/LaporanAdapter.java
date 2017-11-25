package com.example.nac017.crud_volley.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nac017.crud_volley.Model.LaporanModel;
import com.example.nac017.crud_volley.R;

import java.util.List;

/**
 * Created by nac017 on 01/11/17.
 */

public class LaporanAdapter extends RecyclerView.Adapter<LaporanAdapter.HolderData> {
    private List<LaporanModel> mItems;
    private Context context;

    public LaporanAdapter(Context context, List<LaporanModel> mItems) {
        this.context = context;
        this.mItems = mItems;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_laporan,
                parent, false);
        HolderData holderData = new HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        LaporanModel lm = mItems.get(position);
        holder.harga.setText(String.valueOf(lm.getHarga()));
        holder.alamat.setText(lm.getAlamat());
        holder.latitude.setText(String.valueOf(lm.getLatitude()));
        holder.longitude.setText(String.valueOf(lm.getLongitude()));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class HolderData extends RecyclerView.ViewHolder {
        TextView harga, alamat, latitude, longitude;
        //tambahan pas update

        public HolderData(View view) {
            super(view);
            harga = (TextView) view.findViewById(R.id.idharga);
            alamat = (TextView) view.findViewById(R.id.idalamat);
            latitude = (TextView) view.findViewById(R.id.idLatitde);
            longitude = (TextView) view.findViewById(R.id.idLongitude);
        }
    }
}
