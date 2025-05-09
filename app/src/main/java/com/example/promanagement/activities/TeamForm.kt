package com.example.promanagement.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.promanagement.R
import com.example.promanagement.database.AppDatabase
import com.example.promanagement.database.TeamMember
import com.example.promanagement.helper.DateHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TeamForm : AppCompatActivity() {

    private lateinit var nama: EditText
    private lateinit var tanggalLahir: EditText
    private lateinit var alamat: EditText
    private lateinit var kelamin: EditText
    private lateinit var keahlian: EditText
    private lateinit var peran: EditText
    private lateinit var btnDone: Button
    private lateinit var uploadFoto: Button

    private var fotoUri: String? = null // Persiapan simpan URI foto jika implementasi lanjut

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.team_form)

        // Inisialisasi elemen UI dari XML
        nama = findViewById(R.id.nama1)
        tanggalLahir = findViewById(R.id.tanggal_lahir1)
        alamat = findViewById(R.id.alamat1)
        kelamin = findViewById(R.id.kelamin1)
        keahlian = findViewById(R.id.ahli1)
        peran = findViewById(R.id.peran1)
        uploadFoto = findViewById(R.id.uploadFoto)
        btnDone = findViewById(R.id.btnDone)

        // Tampilkan DatePicker saat klik tanggal lahir
        tanggalLahir.setOnClickListener {
            DateHelper.showDatePicker(this, tanggalLahir)
        }

        val db = AppDatabase.getDatabase(this)
        val dao = db.teamMemberDao()

        btnDone.setOnClickListener {
            if (isFormValid()) {
                val member = TeamMember(
                    nama = nama.text.toString(),
                    tanggalLahir = tanggalLahir.text.toString(),
                    alamat = alamat.text.toString(),
                    jenisKelamin = kelamin.text.toString(),
                    keahlian = keahlian.text.toString(),
                    peran = peran.text.toString(),
                    fotoUri = fotoUri
                )

                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        dao.insert(member)
                    }
                    Toast.makeText(this@TeamForm, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
                    finish()
                }
            } else {
                Toast.makeText(this, "Harap isi semua field", Toast.LENGTH_SHORT).show()
            }
        }

        // Optional: Handler untuk upload foto
        uploadFoto.setOnClickListener {
            Toast.makeText(this, "Upload foto belum diimplementasikan", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isFormValid(): Boolean {
        return nama.text.isNotBlank() &&
                tanggalLahir.text.isNotBlank() &&
                alamat.text.isNotBlank() &&
                kelamin.text.isNotBlank() &&
                keahlian.text.isNotBlank() &&
                peran.text.isNotBlank()
    }
}
