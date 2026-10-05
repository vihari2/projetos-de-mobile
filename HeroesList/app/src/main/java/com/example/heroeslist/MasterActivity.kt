
package com.example.heroeslist
import androidx.recyclerview.widget.DividerItemDecoration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MasterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_master2)

        val heroesRV = findViewById<RecyclerView>(R.id.heroesRV)

        val heroes = listOf(
            Hero(
                R.drawable.batman,
                "Batman",
                "DC Comics",
                "Inteligência, artes marciais e habilidades de detetive"
            ),
            Hero(
                R.drawable.hulk,
                "Hulk",
                "Marvel",
                "Superforça, resistência e regeneração"
            ),
            Hero(
                R.drawable.flash,
                "Flash",
                "DC Comics",
                "Supervelocidade e reflexos acelerados"
            ),
            Hero(
                R.drawable.drstrange,
                "Doutor Estranho",
                "Marvel",
                "Magia, manipulação do tempo e teletransporte"
            ),
            Hero(
                R.drawable.superman,
                "Superman",
                "DC Comics",
                "Superforça, voo, visão de calor e supervelocidade"
            ),
            Hero(
                R.drawable.ironman,
                "Homem de Ferro",
                "Marvel",
                "Armadura tecnológica, voo e armas avançadas"
            )
        )

        heroesRV.layoutManager = LinearLayoutManager(this)

        heroesRV.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )


        heroesRV.adapter = HeroesAdapter(
            heroes,
            this
        ) { hero ->

            val intent = android.content.Intent(
                this,
                DetailActivity::class.java
            )

            intent.putExtra("heroImage", hero.heroImage)
            intent.putExtra("heroName", hero.heroName)
            intent.putExtra("heroPowers", hero.heroPowers)

            startActivity(intent)
        }
    }
}