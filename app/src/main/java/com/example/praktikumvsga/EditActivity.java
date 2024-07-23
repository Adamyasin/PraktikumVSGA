package com.example.praktikumvsga;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class EditActivity extends AppCompatActivity {
    EditText editTextNomor, editTextNama, editTextTanggalLahir, editTextJenisKelamin, editTextAlamat;
    Button btnUpdate;
    DatabaseHelper databaseHelper;
    String namaLama, nomor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editTextNomor = findViewById(R.id.editTextNomor);
        editTextNama = findViewById(R.id.editTextNama);
        editTextTanggalLahir = findViewById(R.id.editTextTanggalLahir);
        editTextJenisKelamin = findViewById(R.id.editTextJenisKelamin);
        editTextAlamat = findViewById(R.id.editTextAlamat);
        btnUpdate = findViewById(R.id.btnSimpan);
        databaseHelper = new DatabaseHelper(this);

        // Ambil data dari intent
        Intent intent = getIntent();
        namaLama = intent.getStringExtra("nama");
        nomor = intent.getStringExtra("nomor");
        String tanggalLahir = intent.getStringExtra("tanggal_lahir");
        String jenisKelamin = intent.getStringExtra("jenis_kelamin");
        String alamat = intent.getStringExtra("alamat");

        // Set nilai awal pada EditText
        editTextNomor.setText(nomor);
        editTextNama.setText(namaLama);
        editTextTanggalLahir.setText(tanggalLahir);
        editTextJenisKelamin.setText(jenisKelamin);
        editTextAlamat.setText(alamat);

        // Set onClickListener for editTextTanggalLahir to show DatePickerDialog
        editTextTanggalLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomorBaru = editTextNomor.getText().toString().trim();
                String namaBaru = editTextNama.getText().toString().trim();
                String tanggalLahirBaru = editTextTanggalLahir.getText().toString().trim();
                String jenisKelaminBaru = editTextJenisKelamin.getText().toString().trim();
                String alamatBaru = editTextAlamat.getText().toString().trim();

                updateData(nomorBaru, namaBaru, tanggalLahirBaru, jenisKelaminBaru, alamatBaru);
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

    private void updateData(String nomor, String nama, String tanggalLahir, String jenisKelamin, String alamat) {

        boolean isSuccess = databaseHelper.updateData(nomor, nama, tanggalLahir, jenisKelamin, alamat);

        if (isSuccess) {
            Toast.makeText(this, "Data berhasil diperbarui", Toast.LENGTH_SHORT).show();
            finish(); // Selesai dan kembali ke activity sebelumnya atau refresh halaman jika diperlukan
        } else {
            Toast.makeText(this, "Gagal memperbarui data", Toast.LENGTH_SHORT).show();
        }
    }
}
