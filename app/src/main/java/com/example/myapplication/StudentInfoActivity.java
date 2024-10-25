package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StudentInfoActivity extends AppCompatActivity {
    private EditText editTextName, editTextStudentId, editTextBirthDate, editTextClass, editTextMajor, editTextEmail, editTextPhone, editTextAddress;
    private RadioGroup radioGroupGender;
    private RadioButton radioButtonMale, radioButtonFemale;
    private Button buttonSave;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Khởi tạo các view
        editTextName = findViewById(R.id.etName);
        editTextStudentId = findViewById(R.id.etStudentId);
        editTextBirthDate = findViewById(R.id.etBirthDate);
        editTextClass = findViewById(R.id.etClass);
        editTextMajor = findViewById(R.id.etMajor);
        editTextEmail = findViewById(R.id.etEmail);
        editTextPhone = findViewById(R.id.etPhone);
        editTextAddress = findViewById(R.id.etAddress);
        radioGroupGender = findViewById(R.id.rgGender);
        radioButtonMale = findViewById(R.id.rbMale);
        radioButtonFemale = findViewById(R.id.rbFemale);
        buttonSave = findViewById(R.id.btnSave);

        // Khởi tạo SharedPreferences
        sharedPreferences = getSharedPreferences("StudentInfo", MODE_PRIVATE);

        // Đọc dữ liệu từ SharedPreferences (nếu có)
        loadStudentInfo();

        // Lưu thông tin khi nhấn nút
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveStudentInfo();
                // Chuyển sang activity hiển thị thông tin
                Intent intent = new Intent(StudentInfoActivity.this, DisplayStudentInfoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveStudentInfo() {
        // Kiểm tra giới tính đã được chọn chưa
        int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
        if (selectedGenderId == -1) {
            Toast.makeText(this, "Vui lòng chọn giới tính", Toast.LENGTH_SHORT).show();
            return;
        }

        String gender = selectedGenderId == R.id.rbMale ? "Nam" : "Nữ";

        // Lưu thông tin vào SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", editTextName.getText().toString());
        editor.putString("studentId", editTextStudentId.getText().toString());
        editor.putString("birthDate", editTextBirthDate.getText().toString());
        editor.putString("class", editTextClass.getText().toString());
        editor.putString("major", editTextMajor.getText().toString());
        editor.putString("email", editTextEmail.getText().toString());
        editor.putString("phone", editTextPhone.getText().toString());
        editor.putString("address", editTextAddress.getText().toString());
        editor.putString("gender", gender);
        editor.apply();

        Toast.makeText(this, "Thông tin sinh viên đã được lưu", Toast.LENGTH_SHORT).show();
    }

    private void loadStudentInfo() {
        // Đọc thông tin từ SharedPreferences
        String name = sharedPreferences.getString("name", "");
        String studentId = sharedPreferences.getString("studentId", "");
        String birthDate = sharedPreferences.getString("birthDate", "");
        String classInfo = sharedPreferences.getString("class", "");
        String major = sharedPreferences.getString("major", "");
        String email = sharedPreferences.getString("email", "");
        String phone = sharedPreferences.getString("phone", "");
        String address = sharedPreferences.getString("address", "");
        String gender = sharedPreferences.getString("gender", "");

        editTextName.setText(name);
        editTextStudentId.setText(studentId);
        editTextBirthDate.setText(birthDate);
        editTextClass.setText(classInfo);
        editTextMajor.setText(major);
        editTextEmail.setText(email);
        editTextPhone.setText(phone);
        editTextAddress.setText(address);

        // Chọn radio button theo giới tính
        if (gender.equals("Nam")) {
            radioButtonMale.setChecked(true);
        } else if (gender.equals("Nữ")) {
            radioButtonFemale.setChecked(true);
        }
    }
}
