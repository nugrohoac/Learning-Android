package com.example.nac017.crud_volley.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nac017.crud_volley.Model.InfiniteModel;
import com.example.nac017.crud_volley.R;

import java.util.List;

/**
 * Created by nac017 on 06/11/17.
 */

public class InfiniteAdapter extends RecyclerView.Adapter<InfiniteAdapter.HolderData> {
    private List<InfiniteModel> mItems;
    private Context context;

    public InfiniteAdapter(Context context, List<InfiniteModel> mItems) {
        this.mItems = mItems;
        this.context = context;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_infi,
                parent, false);
        HolderData holderData = new HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        InfiniteModel im = mItems.get(position);
        holder.name.setText(im.getName());
        holder.id.setText(String.valueOf(im.getId()));
        holder.count.setText(String.valueOf(im.getCount()));
        holder.permalink.setText(im.getPermalink());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class HolderData extends RecyclerView.ViewHolder {
        TextView name, id, count, permalink;

        public HolderData(View view) {
            super(view);
            id = (TextView) view.findViewById(R.id.iddd);
            name = (TextView) view.findViewById(R.id.nameeee);
            count = (TextView) view.findViewById(R.id.countttt);
            permalink = (TextView) view.findViewById(R.id.permalinkkk);
        }
    }
}
