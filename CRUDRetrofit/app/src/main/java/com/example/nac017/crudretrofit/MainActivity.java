package com.example.nac017.crudretrofit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nac017.crudretrofit.api.ApiRequestBiodata;
import com.example.nac017.crudretrofit.api.RetroServer;
import com.example.nac017.crudretrofit.model.ResponsModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText Enpm, Enama, Eprodi, Efakultas;
    Button bSave, bTampil, bUpdate, bHapus;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Enpm = (EditText) findViewById(R.id.npm);
        Enama = (EditText) findViewById(R.id.nama);
        Eprodi = (EditText) findViewById(R.id.prodi);
        Efakultas = (EditText) findViewById(R.id.fakultas);
        bSave = (Button) findViewById(R.id.btnSave);
        bTampil = (Button) findViewById(R.id.bTampil);
        bUpdate = (Button) findViewById(R.id.bUpdate);
        bHapus = (Button) findViewById(R.id.bDelete);
        pd = new ProgressDialog(this);

        final Intent data = getIntent();
        Integer update = data.getIntExtra("status", 0);
        if (update != 0) {
            bSave.setVisibility(View.GONE);
            bTampil.setVisibility(View.GONE);
            bUpdate.setVisibility(View.VISIBLE);
            bHapus.setVisibility(View.VISIBLE);
            Enpm.setText(data.getStringExtra("npm"));
            Enama.setText(data.getStringExtra("nama"));
            Eprodi.setText(data.getStringExtra("prodi"));
            Efakultas.setText(data.getStringExtra("fakultas"));

        }

        bHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.setMessage("Loading hapus");
                pd.setCancelable(false);
                pd.show();

                ApiRequestBiodata api = RetroServer.getClient().create(ApiRequestBiodata.class);
                Call<ResponsModel> hapus = api.deleteData(data.getStringExtra("npm"));
                hapus.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        pd.cancel();
                        Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, TampilData.class));
                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Failed hapus", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        bUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.setMessage("Update data on going");
                pd.setCancelable(false);
                pd.show();
                ApiRequestBiodata api = RetroServer.getClient().create(ApiRequestBiodata.class);
                Call<ResponsModel> update = api.updating(
                        data.getStringExtra("npm"), Enama.getText().toString(),
                        Eprodi.getText().toString(),
                        Efakultas.getText().toString()
                );

                update.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        Log.d("RETRO", "response sukses");
                        Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        pd.hide();
                        startActivity(new Intent(MainActivity.this, TampilData.class));
                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {
                        pd.hide();
                        Log.d("Retro", "On Failure");
                    }
                });
            }
        });


        bTampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TampilData.class));
            }
        });

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.setMessage("send data ...");
                pd.setCancelable(false);
                pd.show();
                String npm = Enpm.getText().toString();
                String nama = Enama.getText().toString();
                String prodi = Eprodi.getText().toString();
                String fakultas = Efakultas.getText().toString();
                //call retrovit
                final ApiRequestBiodata api = RetroServer.getClient().create(ApiRequestBiodata.class);

                Call<ResponsModel> sending = api.sendData(npm, nama, prodi, fakultas);
                sending.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        pd.cancel();
                        Log.d("RETRO", "response : " + response.body().toString());
                        Integer code = response.body().getCode();

                        if (code == 1) {
                            Toast.makeText(MainActivity.this, response.body().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, response.body().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {
                        pd.cancel();
                        Log.d("RETRO", "Failure : " + "Gagal Mengirim Request");
                    }
                });
            }
        });
    }
}
