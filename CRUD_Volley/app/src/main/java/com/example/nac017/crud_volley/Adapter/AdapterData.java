package com.example.nac017.crud_volley.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nac017.crud_volley.InsertData;
import com.example.nac017.crud_volley.MainActivity;
import com.example.nac017.crud_volley.Model.ModelData;
import com.example.nac017.crud_volley.R;

import java.util.List;

/**
 * Created by nac017 on 18/10/17.
 */

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {
    private List<ModelData> mItems;
    private Context context;

    public AdapterData(Context context, List<ModelData> mItems) {
        this.context = context;
        this.mItems = mItems;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row, parent, false);
        HolderData holderData = new HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        ModelData md = mItems.get(position);
        holder.tvnama.setText(md.getNama());
        holder.tvnpm.setText(md.getNpm());
        //tambahan pas update
        holder.md = md;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class HolderData extends RecyclerView.ViewHolder {
        TextView tvnama, tvnpm;
        //tambahan pas update
        ModelData md;

        public HolderData(View view) {
            super(view);

            tvnama = (TextView) view.findViewById(R.id.nama);
            tvnpm = (TextView) view.findViewById(R.id.npm);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent update = new Intent(context, InsertData.class);
                    update.putExtra("update", 1);
                    update.putExtra("npm", md.getNpm());
                    update.putExtra("nama", md.getNama());
                    update.putExtra("prodi", md.getProdi());
                    update.putExtra("fakultas", md.getFakultas());

                    context.startActivity(update);
                }
            });
        }
    }
}
