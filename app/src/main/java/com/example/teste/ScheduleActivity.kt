package com.example.teste

import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import android.widget.ArrayAdapter
import androidx.activity.ComponentActivity

class ScheduleActivity : ComponentActivity() {

    private lateinit var selectedProductNameTextView: TextView
    private lateinit var dateSpinner: Spinner
    private lateinit var timeSpinner: Spinner
    private lateinit var confirmScheduleButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        selectedProductNameTextView = findViewById(R.id.selected_product_name)
        dateSpinner = findViewById(R.id.date_spinner)
        timeSpinner = findViewById(R.id.time_spinner)
        confirmScheduleButton = findViewById(R.id.confirm_schedule_button)

        val productName = intent.getStringExtra("product_name")
        selectedProductNameTextView.text = "Procedimento selecionado: $productName"

        val dateOptions = listOf(
            "01/12/2024", "02/12/2024", "03/12/2024", "04/12/2024", "05/12/2024",
            "06/12/2024", "07/12/2024", "08/12/2024", "09/12/2024", "10/12/2024"
        )

        val dateAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dateOptions)
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dateSpinner.adapter = dateAdapter

        val timeOptions = listOf(
            "08:00", "09:00", "10:00", "13:00", "14:00", "15:00", "16:00", "17:00"
        )

        val timeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, timeOptions)
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        timeSpinner.adapter = timeAdapter

        confirmScheduleButton.setOnClickListener {
            val selectedDate = dateSpinner.selectedItem.toString()
            val selectedTime = timeSpinner.selectedItem.toString()

            Toast.makeText(
                this,
                "Agendamento confirmado para $selectedDate às $selectedTime",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
