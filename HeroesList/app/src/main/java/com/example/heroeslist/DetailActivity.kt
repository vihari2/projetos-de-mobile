package com.example.heroeslist
import android.widget.Button
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val btnVoltar = findViewById<Button>(R.id.btnVoltar)

        btnVoltar.setOnClickListener {
            finish()
        }

        val heroImage = findViewById<ImageView>(
            R.id.detailHeroImage
        )

        val heroName = findViewById<TextView>(
            R.id.detailHeroName
        )

        val heroPowers = findViewById<TextView>(
            R.id.detailHeroPowers
        )

        val image = intent.getIntExtra("heroImage", 0)
        val name = intent.getStringExtra("heroName")
        val powers = intent.getStringExtra("heroPowers")

        heroImage.setImageResource(image)
        heroName.text = name
        heroPowers.text = powers
    }
}