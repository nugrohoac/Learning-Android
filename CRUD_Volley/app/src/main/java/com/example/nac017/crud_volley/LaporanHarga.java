package com.example.nac017.crud_volley;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.nac017.crud_volley.Adapter.AdapterData;
import com.example.nac017.crud_volley.Adapter.LaporanAdapter;
import com.example.nac017.crud_volley.Model.LaporanModel;
import com.example.nac017.crud_volley.Model.ModelData;
import com.example.nac017.crud_volley.Util.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LaporanHarga extends AppCompatActivity {
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    LinearLayoutManager mManager;
    List<LaporanModel> mItems;
    Button btnLaporan;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_harga);
        pd = new ProgressDialog(LaporanHarga.this);
        mItems = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleLaporan);

        getDataLaporanHarga();

        mManager = new LinearLayoutManager(LaporanHarga.this);
        mRecyclerView.setLayoutManager(mManager);
        mAdapter = new LaporanAdapter(LaporanHarga.this, mItems);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void getDataLaporanHarga() {
        pd.setMessage("load data");
        pd.setCancelable(false);
        pd.show();
        JsonObjectRequest requestData = new JsonObjectRequest(Request.Method.GET, "https://ph.yippytech.com:5000/laporanHarga/get",
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                pd.cancel();
                try {
                    JSONArray data = response.getJSONArray("data");
                    String token = response.getString("token");
                    //Log.d("cek token", "Ini tokenya : " + token);
                    for (int i = 0; i < data.length(); i++) {
                        LaporanModel lp = new LaporanModel();
                        JSONObject laporan = data.getJSONObject(i);
                        lp.setLaporanHarga_id(Integer.parseInt(laporan.getString("laporanHarga_id")));
                        lp.setUser_id(Integer.parseInt(laporan.getString("user_id")));
                        lp.setKomoditas_id(Integer.parseInt(laporan.getString("komoditas_id")));
                        lp.setHarga(Integer.parseInt(laporan.getString("harga")));
                        lp.setLatitude(Double.parseDouble(laporan.getString("latitude")));
                        lp.setLongitude(Double.parseDouble(laporan.getString("longitude")));
                        lp.setAlamat(laporan.getString("alamat"));
                        Log.d("longitude", "longitude : " + Double.parseDouble(laporan.getString("longitude")));
                        mItems.add(lp);
                        mAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.cancel();
                Log.d("volley", " error" + error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                String auth = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjo5NSwidXNlcm5hbWUiOiJudWdyb2hvYWMxNzc3NyIsInRpbWUiOiJNb24sIDI1IFNlcCAyMDE3IDA3OjI2OjM2IEdNVCIsInJvbGUiOjUsImxvZ2luX3R5cGUiOiIxIiwiaWF0IjoxNTA2MzI0Mzk2fQ.n1amCwxXQY7zao-gAZWBD7n7XGEt2XuhxO9LzzklMjM";
                headers.put("Authorization", auth);
                Log.d("token", "ini token :" + auth);
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(requestData);
    }

}
