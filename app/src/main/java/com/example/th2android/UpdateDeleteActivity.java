package com.example.th2android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import java.util.Locale;

public class UpdateDeleteActivity extends AppCompatActivity implements  View.OnClickListener{
    private EditText tvName,tvDate;
    private Spinner spTacgia;
    private Button btUpdate, btCancel,btRemove;

    private RadioButton rb1, rb2;
    private CheckBox cb1, cb2, cb3;
    private RatingBar rating;
    private Item item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();
        btCancel.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        tvDate.setOnClickListener(this);
        btRemove.setOnClickListener(this);
        Intent intent = getIntent();
        item = (Item) intent.getSerializableExtra("item");

        cb1.setChecked(false);
        cb2.setChecked(false);
        cb3.setChecked(false);

        tvName.setText(item.getName());
        tvDate.setText(item.getDate());
        int p = 0;
        for(int i = 0; i < spTacgia.getCount(); i++){
            if(spTacgia.getItemAtPosition(i).toString().equalsIgnoreCase(item.getAuthor())){
                p = i;
                break;
            }
        }
        spTacgia.setSelection(p);
        float star = Float.parseFloat(item.getRating());
        rating.setRating(star);
        String pv = item.getPhamvi();
        if(pv.equals(rb1.getText())){
            rb1.setChecked(true);
        }else rb2.setChecked(true);

        String dt = item.getDoituong();
        if(dt.toLowerCase().contains(cb1.getText().toString().toLowerCase())){
            cb1.setChecked(true);
        }
        if(dt.toLowerCase().contains(cb2.getText().toString().toLowerCase())){
            cb2.setChecked(true);
        }
        if(dt.toLowerCase().contains(cb3.getText().toString().toLowerCase())){
            cb3.setChecked(true);
        }

    }

    private void initView() {
        tvName = findViewById(R.id.tvName);
        tvDate = findViewById(R.id.tvDate);
        spTacgia = findViewById(R.id.spTacgia);
        btUpdate = findViewById(R.id.btUpdate);
        btCancel = findViewById(R.id.btCancel);
        btRemove= findViewById(R.id.btRemove);
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
            DatePickerDialog dialog = new DatePickerDialog(UpdateDeleteActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        if(view == btUpdate){
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
                int id = item.getId();

                Item i = new Item(id, name, author, date,pv, dt, star);
                SQLHelper db= new SQLHelper((this));
                db.update(i);
                finish();
            }

        }
        if(view == btRemove){
            int id = item.getId();
            System.out.println("Delete" + id);
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Thong bao xoa");
            builder.setMessage("Ban co chac muon xoa " + item.getName() + " khong?");
            builder.setIcon(R.drawable.baseline_remove);
            builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    SQLHelper db1 = new SQLHelper(getApplicationContext());
                    db1.delete(id);
                    System.out.println("Delete thanh cong");
                    finish();
                }
            });
            builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}