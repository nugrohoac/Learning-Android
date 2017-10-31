package com.example.nac017.sqliteapp;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName, editSurname, editMarks;
    Button btnAddData, btnViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        editName = (EditText) findViewById(R.id.name);
        editSurname = (EditText) findViewById(R.id.surname);
        editMarks = (EditText) findViewById(R.id.marks);
        btnAddData = (Button) findViewById(R.id.btnAdd);
        btnViewData = (Button) findViewById(R.id.btnView);

        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddData();
            }
        });

        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewData();
            }
        });
    }

    private void ViewData() {
        Cursor res = myDb.getAllData();
        if (res.getCount() == 0) {
            //show message
            showMessage("Error", "Nothing found");
            return;
        } else {
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                buffer.append("Id : " + res.getString(0) + "\n");
                buffer.append("Name : " + res.getString(1) + "\n");
                buffer.append("Surname : " + res.getString(2) + "\n");
                buffer.append("Marks : " + res.getString(3) + "\n\n");
            }
            //show alldata
            showMessage("Error", buffer.toString());
        }
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void AddData() {
        boolean Inserted = myDb.insertData(editName.getText().toString(),
                editSurname.getText().toString(),
                Integer.parseInt(editMarks.getText().toString()));
        if (Inserted == true)
            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivity.this, "Failed Inserted Data", Toast.LENGTH_SHORT).show();
    }


}
