package com.example.imc

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textName = findViewById<TextView>(R.id.text_name)
        val textWeightHeight = findViewById<TextView>(R.id.text_weight_height)
        val textImcValue = findViewById<TextView>(R.id.text_imc_value)
        val textImcMessage = findViewById<TextView>(R.id.text_imc_message)

        val person = intent.getSerializableExtra("PERSON_DATA") as? Person

        person?.let {
            textName.text = "Nome: ${it.name}"
            textWeightHeight.text = String.format(Locale.getDefault(), "Peso: %.1fkg | Altura: %.2fm", it.weight, it.height)
            textImcValue.text = String.format(Locale.getDefault(), "%.1f", it.imc)
            textImcMessage.text = it.message
        }
    }
}
