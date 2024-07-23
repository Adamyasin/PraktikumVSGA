package com.example.praktikumvsga;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        // Mendapatkan referensi TextView
        tv = findViewById(R.id.tv1);

        // Atur teks pada TextView
        tv.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis vulputate consequat turpis, sit amet sodales dui ornare sed. Quisque ligula est, accumsan quis lacus quis, volutpat elementum lorem. Sed hendrerit facilisis sollicitudin. Suspendisse non feugiat eros. Vivamus sed venenatis lectus. Aenean congue turpis vel leo dictum fermentum. Sed pretium laoreet tellus, vitae tempus dui congue ut. Quisque accumsan mattis turpis, nec efficitur mauris tincidunt eu. Aenean et diam elementum, consectetur neque et, vulputate sem. Phasellus eu massa sit amet libero viverra aliquet vitae at tellus. Fusce sapien dolor, blandit nec turpis at, ullamcorper condimentum massa. Donec nec sodales neque. Duis commodo, metus eu lacinia pulvinar, erat urna laoreet ligula, sed bibendum eros libero vel orci. Fusce magna lectus, hendrerit vitae convallis a, sodales quis ligula. Donec nec vehicula lorem. Curabitur vel diam a ex efficitur mattis. Maecenas at lectus quis tellus finibus dignissim. In hac habitasse platea dictumst. Curabitur interdum.");
    }
}
