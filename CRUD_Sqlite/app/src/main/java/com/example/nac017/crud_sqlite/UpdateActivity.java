package com.example.nac017.crud_sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nac017.crud_sqlite.Helper.StudentHelper;
import com.example.nac017.crud_sqlite.Model.StudentModel;

public class UpdateActivity extends AppCompatActivity {
    EditText updateId, updateName, updateSurname, updateMarks;
    Button btnUpdate, btnDelete;
    StudentHelper stdUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        //initial
        updateId = (EditText) findViewById(R.id.updateId);
        updateName = (EditText) findViewById(R.id.updateName);
        updateSurname = (EditText) findViewById(R.id.updateSurname);
        updateMarks = (EditText) findViewById(R.id.updateMarks);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        stdUpdate = new StudentHelper(UpdateActivity.this);

        //get data from recycleview
        Intent data = getIntent();
        Integer intent_update = data.getIntExtra("statusUpdate", 0);
        Integer inten_id = data.getIntExtra("id", 0);
        String inten_name = data.getStringExtra("name");
        String inten_surname = data.getStringExtra("surname");
        Integer inten_marks = data.getIntExtra("marks", 0);
        Log.d("cek lagi", "ini datanya :" + inten_surname);

        //set to edit text
        updateId.setText(String.valueOf(inten_id));
        updateId.setVisibility(View.GONE);
        updateName.setText(inten_name);
        updateSurname.setText(inten_surname);
        updateMarks.setText(String.valueOf(inten_marks));

        //button actions
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call funtion to
                if (updateData() == true) {
                    startActivity(new Intent(UpdateActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(UpdateActivity.this, "Failed to update data", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer rows = stdUpdate.deleteData(Integer.parseInt(updateId.getText().toString()));
                if (rows == 1) {
                    Toast.makeText(UpdateActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UpdateActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(UpdateActivity.this, "Failed Delete Data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean updateData() {
        boolean isTrue = stdUpdate.updateData(Integer.parseInt(updateId.getText().toString()),
                updateName.getText().toString(), updateSurname.getText().toString(),
                Integer.parseInt(updateMarks.getText().toString()));
        return isTrue;
    }


}
