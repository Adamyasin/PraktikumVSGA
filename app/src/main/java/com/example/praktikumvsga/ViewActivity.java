package com.example.praktikumvsga;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {
    ListView listView;
    Button btnInputData;
    ArrayList<String> dataList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        listView = findViewById(R.id.listView);
        btnInputData = findViewById(R.id.btnInputData);

        dataList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);

        btnInputData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewActivity.this, InputData.class);
                startActivityForResult(intent, 1); // Request code 1
            }
        });

        // Handle item click on ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showOptionsDialog(position);
            }
        });

        // Refresh ListView saat activity di-resume
        refreshListView();
    }

    private void showOptionsDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pilih Tindakan")
                .setItems(new CharSequence[]{"Lihat Data", "Update Data", "Hapus Data"},
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        lihatData(position);
                                        break;
                                    case 1:
                                        updateData(position);
                                        break;
                                    case 2:
                                        hapusData(position);
                                        break;
                                }
                            }
                        })
                .setNegativeButton("Batal", null)
                .create().show();
    }

    private void lihatData(int position) {
        String selectedItem = dataList.get(position);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        Cursor cursor = databaseHelper.getDataByName(selectedItem);

        // Jika data ditemukan, kirim data ke DetailData activity
        if (cursor != null && cursor.moveToFirst()) {
            Intent intent = new Intent(ViewActivity.this, DetailData.class);
            intent.putExtra("nomor", cursor.getString(cursor.getColumnIndex("idMahasiswa")));
            intent.putExtra("nama", cursor.getString(cursor.getColumnIndex("nama")));
            intent.putExtra("tanggal_lahir", cursor.getString(cursor.getColumnIndex("tanggal_lahir")));
            intent.putExtra("jenis_kelamin", cursor.getString(cursor.getColumnIndex("jenis_kelamin")));
            intent.putExtra("alamat", cursor.getString(cursor.getColumnIndex("alamat")));
            startActivity(intent);
        } else {
            Toast.makeText(this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
        }

        if (cursor != null) {
            cursor.close();
        }
    }

    private void updateData(int position) {
        String selectedItem = dataList.get(position);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        Cursor cursor = databaseHelper.getDataByName(selectedItem);

        // Jika data ditemukan, kirim data ke EditActivity untuk update
        if (cursor != null && cursor.moveToFirst()) {
            Intent intent = new Intent(ViewActivity.this, EditActivity.class);
            intent.putExtra("nomor", cursor.getString(cursor.getColumnIndex("idMahasiswa")));
            intent.putExtra("nama", cursor.getString(cursor.getColumnIndex("nama")));
            intent.putExtra("tanggal_lahir", cursor.getString(cursor.getColumnIndex("tanggal_lahir")));
            intent.putExtra("jenis_kelamin", cursor.getString(cursor.getColumnIndex("jenis_kelamin")));
            intent.putExtra("alamat", cursor.getString(cursor.getColumnIndex("alamat")));
            startActivity(intent);
        } else {
            Toast.makeText(this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
        }

        if (cursor != null) {
            cursor.close();
        }
    }

    private void hapusData(int position) {
        String selectedItem = dataList.get(position); // Dapatkan item yang dipilih dari ArrayList
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        // Dapatkan ID berdasarkan nama yang dipilih (jika nama unik, bisa gunakan ini)
        Cursor cursor = databaseHelper.getDataByName(selectedItem);
        int idToDelete = -1; // ID default untuk menandai bahwa tidak ada ID yang cocok ditemukan

        if (cursor != null && cursor.moveToFirst()) {
            idToDelete = cursor.getInt(cursor.getColumnIndex("idMahasiswa"));
            cursor.close();
        }

        // Hapus data dari database
        int rowsDeleted = databaseHelper.deleteData(idToDelete);

        if (rowsDeleted > 0) {
            Toast.makeText(this, "Data dihapus: " + selectedItem, Toast.LENGTH_SHORT).show();
            dataList.remove(position); // Hapus item dari ArrayList
            adapter.notifyDataSetChanged(); // Beritahu adapter bahwa data berubah
        } else {
            Toast.makeText(this, "Gagal menghapus data", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        refreshListView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String nama = data.getStringExtra("nama");
            tambahData(nama);
        }
    }

    public void tambahData(String nama) {
        dataList.add(nama);
        adapter.notifyDataSetChanged();
    }

    private void refreshListView() {
        dataList.clear();
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        Cursor cursor = databaseHelper.getAllData();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String nama = cursor.getString(cursor.getColumnIndex("nama"));
                dataList.add(nama);
            } while (cursor.moveToNext());

            // Tutup cursor setelah selesai menggunakan
            cursor.close();
        }

        // Beritahu adapter bahwa data telah berubah
        adapter.notifyDataSetChanged();
    }
}
