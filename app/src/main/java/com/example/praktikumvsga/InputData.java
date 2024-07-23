package com.example.praktikumvsga;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class InputData extends AppCompatActivity {
    EditText editTextNomor, editTextNama, editTextTanggalLahir, editTextJenisKelamin, editTextAlamat;
    Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        editTextNomor = findViewById(R.id.editTextNomor);
        editTextNama = findViewById(R.id.editTextNama);
        editTextTanggalLahir = findViewById(R.id.editTextTanggalLahir);
        editTextJenisKelamin = findViewById(R.id.editTextJenisKelamin);
        editTextAlamat = findViewById(R.id.editTextAlamat);

        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanData();
            }
        });

        // Set onClickListener for editTextTanggalLahir to show DatePickerDialog
        editTextTanggalLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    private void showDatePickerDialog() {
        // Mendapatkan tanggal saat ini
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Membuat DatePickerDialog dan menampilkan
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Format tanggal yang diinginkan
                String tanggal = dayOfMonth + "-" + (month + 1) + "-" + year;
                editTextTanggalLahir.setText(tanggal);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void simpanData() {
        String nomor = editTextNomor.getText().toString().trim();
        String nama = editTextNama.getText().toString().trim();
        String tanggalLahir = editTextTanggalLahir.getText().toString().trim();
        String jenisKelamin = editTextJenisKelamin.getText().toString().trim();
        String alamat = editTextAlamat.getText().toString().trim();

        // Validasi input
        if (nama.isEmpty()) {
            editTextNama.setError("Nama harus diisi");
            editTextNama.requestFocus();
            return;
        }

        // Simpan data ke SQLite menggunakan DatabaseHelper
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        long id = databaseHelper.insertData(nama, tanggalLahir, jenisKelamin, alamat);

        if (id != -1) {
            Toast.makeText(this, "Data tersimpan", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Gagal menyimpan data", Toast.LENGTH_SHORT).show();
        }
    }
}
