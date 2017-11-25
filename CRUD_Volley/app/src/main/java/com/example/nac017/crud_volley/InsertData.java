package com.example.nac017.crud_volley;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.nac017.crud_volley.Util.AppController;
import com.example.nac017.crud_volley.Util.ServerAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InsertData extends AppCompatActivity {
    EditText npm, nama, prodi, fakultas;
    Button btnbtl, btnsimpan;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);
        /* get data dar intent */
        Intent data = getIntent();
        final int update = data.getIntExtra("update", 0);
        String intent_npm = data.getStringExtra("npm");
        String intent_nama = data.getStringExtra("nama");
        String intent_prodi = data.getStringExtra("prodi");
        String intent_fakultas = data.getStringExtra("fakultas");
        // End of get data dari intent

        //edit text
        npm = (EditText) findViewById(R.id.inp_npm);
        nama = (EditText) findViewById(R.id.inp_nama);
        prodi = (EditText) findViewById(R.id.inp_prodi);
        fakultas = (EditText) findViewById(R.id.inp_fakultas);
        //button
        btnbtl = (Button) findViewById(R.id.btn_cancel);
        btnsimpan = (Button) findViewById(R.id.btn_simpan);
        //pd dialog
        pd = new ProgressDialog(InsertData.this);

        // kondisi update / insert/
        if (update == 1) {
            btnsimpan.setText("Update Data");
            npm.setText(intent_npm);
            npm.setVisibility(View.GONE);
            nama.setText(intent_nama);
            prodi.setText(intent_prodi);
            fakultas.setText(intent_fakultas);
        }

        //ketika di klik baru simpan
        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (update == 1) {
                    Update_data();
                } else {
                    simpanData();
                }
            }
        });

        btnbtl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InsertData.this, MainActivity.class));
            }
        });
    }

    private void Update_data() {
        pd.setMessage("Update Data");
        pd.setCancelable(false);
        pd.show();

        //untuk mengirim ke file php yang dituju
        StringRequest updateReq = new StringRequest(Request.Method.POST, ServerAPI.URL_UPDATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertData.this, "pesan : " + res.getString("message")
                                    , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        startActivity(new Intent(InsertData.this, MainActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertData.this, "pesan : Gagal Insert Data",
                                Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //string key sesusi dengan API dan string kanan adalah isinya
                Map<String, String> map = new HashMap<>();
                map.put("npm", npm.getText().toString());
                map.put("nama", nama.getText().toString());
                map.put("prodi", prodi.getText().toString());
                map.put("fakultas", fakultas.getText().toString());
                Log.d("volley", "data: " + map.toString());
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                String auth = "token  harusnya";
                headers.put("Authorization", auth);
                Log.d("token","ini token :" + auth);
                return headers;
            }
        };

        AppController.getInstance().addToRequestQueue(updateReq);
    }

    private void simpanData() {
        pd.setMessage("Menyimpan Data");
        pd.setCancelable(false);
        pd.show();

        //untuk mengirim ke file php yang dituju
        StringRequest sendData = new StringRequest(Request.Method.POST, ServerAPI.URL_INSERT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertData.this, "pesan : " + res.getString("message")
                                    , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        startActivity(new Intent(InsertData.this, MainActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertData.this, "pesan : Gagal Insert Data",
                                Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //string key sesusi dengan API dan string kanan adalah isinya
                Map<String, String> map = new HashMap<>();
                map.put("npm", npm.getText().toString());
                map.put("nama", nama.getText().toString());
                map.put("prodi", prodi.getText().toString());
                map.put("fakultas", fakultas.getText().toString());
                Log.d("volley", "data: " + map.toString());
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                String auth = "Bearer hanta text";
                headers.put("Authorization", auth);
                Log.d("token","ini token :" + auth);
                return headers;
            }
        };

        AppController.getInstance().addToRequestQueue(sendData);
    }
}
