package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sharedPreferences = getSharedPreferences("StudentInfo", MODE_PRIVATE);
        // Kiểm tra xem thông tin sinh viên đã được nhập hay chưa
        boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
        if (isFirstRun || areStudentInfoFieldsEmpty()) {
            // Nếu là lần đầu cài đặt hoặc có trường nào đó rỗng, chuyển đến StudentInfoActivity
            Intent intent = new Intent(MainActivity.this, StudentInfoActivity.class);
            // Đánh dấu là đã chạy lần đầu
            sharedPreferences.edit().putBoolean("isFirstRun", false).apply();
            startActivity(intent);
            finish(); // Đóng MainActivity
        } else {
            // Nếu đã nhập thông tin, chuyển đến DisplayStudentInfoActivity
            Intent intent = new Intent(MainActivity.this, DisplayStudentInfoActivity.class);
            startActivity(intent);
            finish(); // Đóng MainActivity
        }
    }

    private boolean areStudentInfoFieldsEmpty() {
        // Kiểm tra các thuộc tính có rỗng không
        String name = sharedPreferences.getString("name", "");
        String studentId = sharedPreferences.getString("studentId", "");
        String birthDate = sharedPreferences.getString("birthDate", "");
        String gender = sharedPreferences.getString("gender", "");
        String classInfo = sharedPreferences.getString("class", "");
        String major = sharedPreferences.getString("major", "");
        String email = sharedPreferences.getString("email", "");
        String phone = sharedPreferences.getString("phone", "");
        String address = sharedPreferences.getString("address", "");

        // Kiểm tra xem có bất kỳ thuộc tính nào rỗng không
        return name.isEmpty() || studentId.isEmpty() || birthDate.isEmpty() ||
                gender.isEmpty() || classInfo.isEmpty() || major.isEmpty() ||
                email.isEmpty() || phone.isEmpty() || address.isEmpty();
    }
}