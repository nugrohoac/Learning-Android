package com.example.nac017.crudretrofit;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.nac017.crudretrofit.adapter.AdapterData;
import com.example.nac017.crudretrofit.api.ApiRequestBiodata;
import com.example.nac017.crudretrofit.api.RetroServer;
import com.example.nac017.crudretrofit.model.DataModel;
import com.example.nac017.crudretrofit.model.ResponsModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TampilData extends AppCompatActivity {
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mManager;
    ProgressDialog pd;
    List<DataModel> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycleData);
        mManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mManager);
        pd = new ProgressDialog(this);
        pd.setMessage("Loading");
        pd.setCancelable(false);
        pd.show();
        ApiRequestBiodata api = RetroServer.getClient().create(ApiRequestBiodata.class);
        Call<ResponsModel> gettingData = api.getData();
        gettingData.enqueue(new Callback<ResponsModel>() {
            @Override
            public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                pd.hide();
                Log.d("RETRO", "RESPONSE : " + response.body().getCode());
                Log.d("message", "RESPONSE : " + response.body().getMessage());
                mList = response.body().getResult();

                mAdapter = new AdapterData(TampilData.this, mList);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponsModel> call, Throwable t) {
                pd.hide();
                Log.d("RETRO", "RESPONSE : " + "Respon gagal");
            }
        });
    }
}
