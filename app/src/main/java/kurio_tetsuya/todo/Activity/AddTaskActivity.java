package kurio_tetsuya.todo.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import kurio_tetsuya.todo.Database.DatabaseClient;
import kurio_tetsuya.todo.Presenter.AddPresenter;
import kurio_tetsuya.todo.R;
import kurio_tetsuya.todo.Model.TaskModel;
import kurio_tetsuya.todo.View.IAddView;

public class AddTaskActivity extends AppCompatActivity implements IAddView{

    private EditText editTextTask, editTextDesc;
    private TextView tv_end_date;
    DatePickerDialog datePickerDialog;
    String sTask,sDesc,sStatus,sEndDate;
    private AddPresenter addPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        editTextTask = findViewById(R.id.editTextTask);
        editTextDesc = findViewById(R.id.editTextDesc);
        tv_end_date = findViewById(R.id.tv_end_date);

        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidate()){
                    addPresenter.saveTask(editTextTask.getText().toString().trim(),
                            editTextDesc.getText().toString().trim(),
                            tv_end_date.getText().toString().trim(),"To Do");
                }
            }
        });
    }
    public boolean isValidate(){
        boolean isvalidate=false;
        if(TextUtils.isEmpty(editTextTask.getText())){
            Toast.makeText(this, "Enter Task Name", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(editTextDesc.getText())){
            Toast.makeText(this, "Enter Task Description", Toast.LENGTH_SHORT).show();
        }
        return isvalidate;
    }

    public void pickDate(View view) {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
        // date picker dialog
        datePickerDialog = new DatePickerDialog(AddTaskActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // set day of month , month and year value in the edit text
                        tv_end_date.setText(dayOfMonth + "/"
                                + (monthOfYear + 1) + "/" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    @Override
    public void onSuccess() {
        Toast.makeText(this, "Successfully Added", Toast.LENGTH_SHORT).show();
        finish();
        startActivity(new Intent(this,MainActivity.class));
    }

    @Override
    public void onError() {

    }
}