package com.example.promanagement.helper

import android.app.DatePickerDialog
import android.content.Context
import android.widget.EditText
import java.text.SimpleDateFormat
import java.util.*

object DateHelper {

    // Fungsi untuk menampilkan DatePickerDialog
    fun showDatePicker(context: Context, editText: EditText) {
        // Mendapatkan tanggal hari ini
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        // Membuat DatePickerDialog
        val datePickerDialog = DatePickerDialog(
            context,
            { _, selectedYear, selectedMonth, selectedDay ->
                // Format tanggal yang diinginkan (contoh: dd/MM/yyyy)
                val formattedDate = formatDate(selectedYear, selectedMonth + 1, selectedDay)
                // Menampilkan tanggal yang dipilih ke dalam EditText
                editText.setText(formattedDate)
            },
            year, month, dayOfMonth
        )
        datePickerDialog.show()
    }

    // Fungsi untuk memformat tanggal ke format yang diinginkan
    private fun formatDate(year: Int, month: Int, day: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month - 1, day)
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }
}
