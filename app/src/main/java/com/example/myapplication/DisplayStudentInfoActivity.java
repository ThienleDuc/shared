package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayStudentInfoActivity extends AppCompatActivity {
    private TextView tvName, tvStudentId, tvBirthDate, tvGender, tvClass, tvMajor, tvEmail, tvPhone, tvAddress;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_student_info);

        // Khởi tạo các view
        tvName = findViewById(R.id.tvName);
        tvStudentId = findViewById(R.id.tvStudentId);
        tvBirthDate = findViewById(R.id.tvBirthDate);
        tvGender = findViewById(R.id.tvGender);
        tvClass = findViewById(R.id.tvClass);
        tvMajor = findViewById(R.id.tvMajor);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);
        tvAddress = findViewById(R.id.tvAddress);

        // Khởi tạo SharedPreferences
        sharedPreferences = getSharedPreferences("StudentInfo", MODE_PRIVATE);

        // Hiển thị thông tin sinh viên từ SharedPreferences
        displayStudentInfo();
    }

    private void displayStudentInfo() {
        String name = sharedPreferences.getString("name", "");
        String studentId = sharedPreferences.getString("studentId", "");
        String birthDate = sharedPreferences.getString("birthDate", "");
        String gender = sharedPreferences.getString("gender", "");
        String classInfo = sharedPreferences.getString("class", "");
        String major = sharedPreferences.getString("major", "");
        String email = sharedPreferences.getString("email", "");
        String phone = sharedPreferences.getString("phone", "");
        String address = sharedPreferences.getString("address", "");

        tvName.setText("" + name);
        tvStudentId.setText("" + studentId);
        tvBirthDate.setText("" + birthDate);
        tvGender.setText("" + gender);
        tvClass.setText("" + classInfo);
        tvMajor.setText("" + major);
        tvEmail.setText("" + email);
        tvPhone.setText("" + phone);
        tvAddress.setText("" + address);
    }
}
