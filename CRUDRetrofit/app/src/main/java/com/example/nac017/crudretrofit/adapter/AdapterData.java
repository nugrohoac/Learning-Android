package com.example.nac017.crudretrofit.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.nac017.crudretrofit.MainActivity;
import com.example.nac017.crudretrofit.R;
import com.example.nac017.crudretrofit.model.DataModel;

import java.util.List;

/**
 * Created by nac017 on 30/11/17.
 */

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {
    private List<DataModel> mList;
    private Context context;

    public AdapterData(Context context, List<DataModel> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.listdata, parent, false);
        HolderData holderData = new HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        DataModel dm = mList.get(position);
        holder.npm.setText(dm.getNpm());
        holder.nama.setText(dm.getNama());
        holder.prodi.setText(dm.getProdi());
        holder.fakultas.setText(dm.getFakultas());
        holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder {
        TextView npm, nama, prodi, fakultas;
        DataModel dm;

        public HolderData(View view) {
            super(view);
            npm = (TextView) view.findViewById(R.id.idNpm);
            nama = (TextView) view.findViewById(R.id.idNama);
            prodi = (TextView) view.findViewById(R.id.idProdi);
            fakultas = (TextView) view.findViewById(R.id.idFakultas);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent parsing = new Intent(context, MainActivity.class);
                    parsing.putExtra("status",1);
                    parsing.putExtra("npm", dm.getNpm());
                    parsing.putExtra("nama", dm.getNama());
                    parsing.putExtra("prodi", dm.getProdi());
                    parsing.putExtra("fakultas", dm.getFakultas());

                    context.startActivity(parsing);
                }
            });
        }
    }
}
