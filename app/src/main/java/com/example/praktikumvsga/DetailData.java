package com.example.praktikumvsga;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailData extends AppCompatActivity {
    TextView textViewNomor, textViewNama, textViewTanggalLahir, textViewJenisKelamin, textViewAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        // Inisialisasi TextView
        textViewNomor = findViewById(R.id.tvnomor);
        textViewNama = findViewById(R.id.tvnama);
        textViewTanggalLahir = findViewById(R.id.tvTTL);
        textViewJenisKelamin = findViewById(R.id.tvJK);
        textViewAlamat = findViewById(R.id.tvalamat);

        // Ambil data dari Intent
        Intent intent = getIntent();
        if (intent != null) {
            String nomor = intent.getStringExtra("nomor");
            String nama = intent.getStringExtra("nama");
            String tanggalLahir = intent.getStringExtra("tanggal_lahir");
            String jenisKelamin = intent.getStringExtra("jenis_kelamin");
            String alamat = intent.getStringExtra("alamat");

            // Set data ke TextView
            textViewNomor.setText("Nomor \n" + nomor);
            textViewNama.setText("Nama \n" + nama);
            textViewTanggalLahir.setText("Tanggal Lahir \n" + tanggalLahir);
            textViewJenisKelamin.setText("Jenis Kelamin \n" + jenisKelamin);
            textViewAlamat.setText("Alamat \n" + alamat);
        }
    }
}
