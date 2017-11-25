package com.example.nac017.crud_volley;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.nac017.crud_volley.Adapter.InfiniteAdapter;
import com.example.nac017.crud_volley.Model.InfiniteModel;
import com.example.nac017.crud_volley.Util.AppController;
import com.example.nac017.crud_volley.Util.ServerAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class InfiniteScroll extends AppCompatActivity {
    RecyclerView rvInfi;
    RecyclerView.Adapter rvAdapter;
    LinearLayoutManager rvLManager;
    List<InfiniteModel> mItems;
    ProgressDialog pg;
    int PAGE = 2;
    boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infinite_scroll);

        //initialize
        pg = new ProgressDialog(InfiniteScroll.this);
        mItems = new ArrayList<>();
        rvInfi = (RecyclerView) findViewById(R.id.rvInfinite);
        rvLManager = new LinearLayoutManager(InfiniteScroll.this);
        rvInfi.setLayoutManager(rvLManager);
        getData(PAGE);
        rvAdapter = new InfiniteAdapter(InfiniteScroll.this, mItems);
        rvInfi.setAdapter(rvAdapter);
        rvInfi.addOnScrollListener(recyclerViewOnScrollListener);
    }

    private void getData(int PAGE) {
        String keywords = "fixied";
        final String page = String.valueOf(PAGE);
        pg.setMessage("load data");
        pg.setCancelable(false);
        pg.show();
        JsonObjectRequest reqData = new JsonObjectRequest(Request.Method.GET, ServerAPI.TEST + page,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                pg.cancel();
                try {
                    JSONArray categories = response.getJSONArray("categories");
                    for (int i = 0; i < categories.length(); i++) {
                        InfiniteModel im = new InfiniteModel();
                        JSONObject infi = categories.getJSONObject(i);
                        im.setCount(Integer.parseInt(infi.getString("count")));
                        im.setId(Integer.parseInt(infi.getString("id")));
                        Log.d("cek", "id BL " + Integer.parseInt(infi.getString("id")));
                        Log.d("cek", "count BL " + Integer.parseInt(infi.getString("count")));
                        Log.d("cek", "name BL " + infi.getString("name"));
                        Log.d("cek", "permalink BL " + infi.getString("permalink"));
                        im.setName(infi.getString("name"));
                        im.setPermalink(infi.getString("permalink"));
                        mItems.add(im);
                        rvAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("volley", " error" + error.getMessage());
            }
        });
        AppController.getInstance().addToRequestQueue(reqData);
    }

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = rvLManager.getChildCount();
            int totalItemCount = rvLManager.getItemCount();
            int firstVisibleItemPosition = rvLManager.findFirstVisibleItemPosition();
            if (!isLoading) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            PAGE++;
                            getData(PAGE);
                            Log.d("Page","Ini page "+ PAGE);
                        }
                    }, 1);
                }
            }
        }
    };

}
