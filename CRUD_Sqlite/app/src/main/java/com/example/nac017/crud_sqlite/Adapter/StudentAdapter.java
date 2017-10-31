package com.example.nac017.crud_sqlite.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nac017.crud_sqlite.InsertActivity;
import com.example.nac017.crud_sqlite.Model.StudentModel;
import com.example.nac017.crud_sqlite.R;
import com.example.nac017.crud_sqlite.UpdateActivity;

import java.util.List;

/**
 * Created by nac017 on 28/10/17.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.HolderData> {
    private List<StudentModel> mStudent;
    private Context context;

    //constructor
    public StudentAdapter(Context context, List<StudentModel> mStudent) {
        this.mStudent = mStudent;
        this.context = context;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.submain, parent, false);
        HolderData holderData = new HolderData(view);
        return holderData;
    }

    //set value
    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        StudentModel sModel = mStudent.get(position);
        holder.id.setText(sModel.getId().toString());
        holder.name.setText(sModel.getName());
        holder.surname.setText(sModel.getSurname());
        holder.marks.setText(sModel.getMarks().toString());

        holder.studentModel = sModel;
    }

    @Override
    public int getItemCount() {
        return mStudent.size();
    }

    class HolderData extends RecyclerView.ViewHolder {
        TextView id, name, surname, marks;

        StudentModel studentModel; //sutdent model use to parsing data other activities

        public HolderData(View view) {
            super(view);

            id = (TextView) view.findViewById(R.id.idStd);
            name = (TextView) view.findViewById(R.id.nameStd);
            surname = (TextView) view.findViewById(R.id.surnameStd);
            marks = (TextView) view.findViewById(R.id.marksStd);

            //view on click detail to update activiteis
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent update = new Intent(context, UpdateActivity.class);
                    update.putExtra("statusUpdate", 1);
                    update.putExtra("id", studentModel.getId());
                    update.putExtra("name", studentModel.getName());
                    update.putExtra("surname", studentModel.getSurname());
                    update.putExtra("marks", studentModel.getMarks());
                    context.startActivity(update);
                }
            });
        }
    }
}
