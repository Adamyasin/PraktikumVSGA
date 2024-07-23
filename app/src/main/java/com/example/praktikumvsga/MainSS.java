package com.example.praktikumvsga;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class MainSS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ss_main);

        // Menunggu beberapa detik sebelum berpindah ke MainActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainSS.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000); // Durasi splash screen dalam milidetik (misalnya 3000ms = 3 detik)
    }
}
