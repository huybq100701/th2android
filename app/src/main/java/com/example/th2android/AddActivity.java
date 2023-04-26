package com.example.th2android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.example.th2.R;
import com.example.th2android.dal.SQLHelper;
import com.example.th2android.model.Item;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText tvName,tvDate;
    private Spinner spTacgia;
    private Button btAdd, btCancel;

    private RadioButton rb1, rb2;
    private CheckBox cb1, cb2, cb3;
    private RatingBar rating;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        btCancel.setOnClickListener(this);
        btAdd.setOnClickListener(this);
        tvDate.setOnClickListener(this);


    }

    private void initView() {
        tvName = findViewById(R.id.tvName);
        tvDate = findViewById(R.id.tvDate);
        spTacgia = findViewById(R.id.spTacgia);
        btAdd = findViewById(R.id.btAdd);
        btCancel = findViewById(R.id.btCancel);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        rating = findViewById(R.id.rating);
        spTacgia.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner, getResources().getStringArray(R.array.tacgia)));


    }


    @Override
    public void onClick(View view) {
        if( view == tvDate){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day  = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    String date = "";
                    if(m > 8){
                        date = d + "/" + (m+1) + "/" + y;

                    }
                    else{
                        date = d + "/0" + (m+1) + "/" + y;

                    }
                    tvDate.setText(date);
                }
            },year, month, day);
            dialog.show();

        }
        if(view == btCancel){
            finish();
        }
        if(view == btAdd){
            String name = tvName.getText().toString();
            String author = spTacgia.getSelectedItem().toString();
            String date = tvDate.getText().toString();
            String pv = "";
            if (rb1.isChecked()) {
                pv += rb1.getText();
            }
            else {
                pv += rb2.getText();
            }
            String dt= "";
            if (cb1.isChecked()) {
                dt += cb1.getText() + "\n";
            }
            if(cb2.isChecked()) {
                dt += cb2.getText() + "\n";
            }if(cb3.isChecked()){
                dt += cb3.getText() + "\n";
            }
            String star ="" ;
            star += rating.getRating();
            if(!name.isEmpty() ){
                Item i = new Item(name, author, date,pv, dt, star);
                SQLHelper db= new SQLHelper((this));
                db.addItem(i);
                finish();
            }

        }
    }
}