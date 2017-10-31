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

public class InsertActivity extends AppCompatActivity {
    StudentHelper mS;
    Button btnSave;
    EditText editId, editName, editSurname, editMarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        //initial
        editName = (EditText) findViewById(R.id.editName);
        editSurname = (EditText) findViewById(R.id.editSurname);
        editMarks = (EditText) findViewById(R.id.editMarks);
        mS = new StudentHelper(this);
        btnSave = (Button) findViewById(R.id.btnSave);

        //set button
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AddData() == true) {
                    startActivity(new Intent(InsertActivity.this, MainActivity.class));
                    finish();
                }
            }
        });
    }

    //create function

    //function add data
    public boolean AddData() {
        boolean isInserted = mS.insertData(editName.getText().toString(),
                editSurname.getText().toString(),
                Integer.parseInt(editMarks.getText().toString()));
        if (isInserted == true) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }
        return isInserted;
    }
}
