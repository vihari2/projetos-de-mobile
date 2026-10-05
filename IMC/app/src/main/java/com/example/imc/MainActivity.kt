package com.example.imc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editName = findViewById<EditText>(R.id.edit_name)
        val editWeight = findViewById<EditText>(R.id.edit_weight)
        val editHeight = findViewById<EditText>(R.id.edit_height)
        val btnCalculate = findViewById<Button>(R.id.btn_calculate)

        btnCalculate.setOnClickListener {
            val name = editName.text.toString()
            val weightStr = editWeight.text.toString()
            val heightStr = editHeight.text.toString()

            if (name.isNotEmpty() && weightStr.isNotEmpty() && heightStr.isNotEmpty()) {
                val weight = weightStr.replace(",", ".").toDoubleOrNull() ?: 0.0
                var height = heightStr.replace(",", ".").toDoubleOrNull() ?: 0.0
                
                if (height <= 0 || weight <= 0) {
                    Toast.makeText(this, "Valores inválidos", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Se o usuário digitou em cm (ex: 160), converte para metros (1.60)
                if (height > 3.0) {
                    height /= 100
                }
                
                val imc = weight / (height * height)
                val message = when {
                    imc < 18.5 -> "Abaixo do peso"
                    imc < 25.0 -> "Peso normal"
                    imc < 30.0 -> "Sobrepeso"
                    imc < 35.0 -> "Obesidade grau 1"
                    imc < 40.0 -> "Obesidade grau 2"
                    else -> "Obesidade grau 3"
                }

                val person = Person(name, weight, height, imc, message)
                
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("PERSON_DATA", person)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}