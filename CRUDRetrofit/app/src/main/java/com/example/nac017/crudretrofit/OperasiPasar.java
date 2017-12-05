package com.example.nac017.crudretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nac017.crudretrofit.api.ApiRequestBiodata;
import com.example.nac017.crudretrofit.api.RetroServer;
import com.example.nac017.crudretrofit.model.ResponsModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OperasiPasar extends AppCompatActivity {
    TextView message, token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operasi_pasar);

        message = (TextView) findViewById(R.id.idMessage);
        token = (TextView) findViewById(R.id.idToken);

        ApiRequestBiodata api = RetroServer.getClient().create(ApiRequestBiodata.class);
        Call<ResponsModel> operasi = api.getLaporan("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjo5NSwidXNlcm5hbWUiOiJudWdyb2hvYWMxNzc3NyIsInRpbWUiOiJNb24sIDI1IFNlcCAyMDE3IDA3OjI2OjM2IEdNVCIsInJvbGUiOjUsImxvZ2luX3R5cGUiOiIxIiwiaWF0IjoxNTA2MzI0Mzk2fQ.n1amCwxXQY7zao-gAZWBD7n7XGEt2XuhxO9LzzklMjM");
        operasi.enqueue(new Callback<ResponsModel>() {
            @Override
            public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                Log.d("OPERASI","Ini pesan : "+ response.body().getMessage());
                message.setText(response.body().getMessage());
                token.setText(String.valueOf(response.body().getStatus()));
            }

            @Override
            public void onFailure(Call<ResponsModel> call, Throwable t) {
                Toast.makeText(OperasiPasar.this, "Failed header", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
