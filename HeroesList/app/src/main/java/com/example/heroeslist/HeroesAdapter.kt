package com.example.heroeslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HeroesAdapter(
    private val heroes: List<Hero>,
    private val context: Context,
    private val click: (Hero) -> Unit
) : RecyclerView.Adapter<HeroesAdapter.HeroesViewHolder>() {

    inner class HeroesViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val heroName: TextView =
            itemView.findViewById(R.id.heroName)

        private val heroCompany: TextView =
            itemView.findViewById(R.id.heroCompany)

        private val heroImage: ImageView =
            itemView.findViewById(R.id.heroImage)

        fun bind(hero: Hero) {
            heroName.text = hero.heroName
            heroCompany.text = hero.heroCompany
            heroImage.setImageResource(hero.heroImage)

            itemView.setOnClickListener {
                click(hero)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HeroesViewHolder {

        val view = LayoutInflater.from(context).inflate(
            R.layout.recycler_view_item,
            parent,
            false
        )

        return HeroesViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: HeroesViewHolder,
        position: Int
    ) {
        val hero = heroes[position]
        holder.bind(hero)
    }

    override fun getItemCount(): Int {
        return heroes.size
    }
}