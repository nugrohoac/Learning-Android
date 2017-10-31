package com.example.nac017.crud_sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.nac017.crud_sqlite.Adapter.StudentAdapter;
import com.example.nac017.crud_sqlite.Helper.StudentHelper;
import com.example.nac017.crud_sqlite.Model.StudentModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //initial
    StudentHelper myStudent;
    Button btnAdd;
    RecyclerView recyclerView;
    RecyclerView.Adapter rvAdapter;
    RecyclerView.LayoutManager rvlManager;
    List<StudentModel> stdModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialition
        myStudent = new StudentHelper(this);
        stdModel = new ArrayList<>();

        //call function
        ParsingData(); // this function use to move data from API to array model
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        rvlManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(rvlManager);
        rvAdapter = new StudentAdapter(MainActivity.this, stdModel);

        recyclerView.setAdapter(rvAdapter);

        //button
        btnAdd = (Button) findViewById(R.id.btnInsert);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, InsertActivity.class));
                //finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    //parsing data from API to array list student model
    private void ParsingData() {
        Cursor res = myStudent.getAllData();
        if (res.getCount() == 0) {
            Toast.makeText(MainActivity.this, "Data empty", Toast.LENGTH_SHORT).show();
        } else {
            while (res.moveToNext()) {
                StudentModel stdParsing = new StudentModel();
                stdParsing.setId(Integer.parseInt(res.getString(0)));
                stdParsing.setName(res.getString(1));
                stdParsing.setSurname(res.getString(2));
                stdParsing.setMarks(Integer.parseInt(res.getString(3)));
                stdModel.add(stdParsing);
            }
        }
    }
}
