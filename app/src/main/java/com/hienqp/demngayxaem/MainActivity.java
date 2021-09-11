package com.hienqp.demngayxaem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText editTextStartDate, editTextCurrentDate;
    Button buttonCalculate;
    TextView textViewResult;
    Calendar calendarStart, calendarCurrent;
    SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        captureViewOfLayout();
        simpleDateFormat = new SimpleDateFormat("dd/MMM/yyyy");

        editTextStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickStartDate();
            }
        });

        editTextCurrentDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickCurrentDate();
            }
        });

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int duration = (int) ((calendarCurrent.getTimeInMillis() - calendarStart.getTimeInMillis()) / (1000*60*60*24));
                if (duration < 0) {
                    Toast.makeText(MainActivity.this, "Vui lòng chọn ngày hiện tại sau ngày bắt đầu", Toast.LENGTH_SHORT).show();
                } else {
                    textViewResult.setText("Số ngày xa nhau: \n" + duration);
                }
            }
        });

    }

    private void pickStartDate() {
        calendarStart = Calendar.getInstance();
        int yearStart = calendarStart.get(Calendar.YEAR);
        int monthStart = calendarStart.get(Calendar.MONTH);
        int dayStart = calendarStart.get(Calendar.DATE);
        DatePickerDialog pickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendarStart.set(year, month, dayOfMonth);
                editTextStartDate.setText(simpleDateFormat.format(calendarStart.getTime()));
            }
        }, yearStart, monthStart, dayStart);
        pickerDialog.show();
    }

    private void pickCurrentDate() {
        calendarCurrent = Calendar.getInstance();
        int yearStart = calendarCurrent.get(Calendar.YEAR);
        int monthStart = calendarCurrent.get(Calendar.MONTH);
        int dayStart = calendarCurrent.get(Calendar.DATE);
        DatePickerDialog pickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendarCurrent.set(year, month, dayOfMonth);
                editTextCurrentDate.setText(simpleDateFormat.format(calendarCurrent.getTime()));
            }
        }, yearStart, monthStart, dayStart);
        pickerDialog.show();
    }

    private void captureViewOfLayout() {
        editTextStartDate = (EditText) findViewById(R.id.editText_start_date);
        editTextCurrentDate = (EditText) findViewById(R.id.editText_current_date);
        buttonCalculate = (Button) findViewById(R.id.button_calculate);
        textViewResult = (TextView) findViewById(R.id.textView_result);
    }
}