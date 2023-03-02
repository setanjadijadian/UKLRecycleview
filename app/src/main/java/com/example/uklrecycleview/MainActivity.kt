package com.example.uklrecycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvHeroes: RecyclerView
    private var list: ArrayList<Chara> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title=("Mode List")

        rvHeroes = findViewById(R.id.rv_hero)
        rvHeroes.setHasFixedSize(true)

        list.addAll(CharaData.listData)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvHeroes.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListChara(list)
        rvHeroes.adapter = listHeroAdapter
        listHeroAdapter.setOnItemClickCallback(object : ListChara.OnItemClickCallback {
            override fun onItemClicked(data: Chara) {
                showSelectedHero(data)
            }
        })
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_chara, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_list -> {
                title = "Mode List"
                showRecyclerList()
                supportActionBar?.title = (title)
            }

            R.id.action_grid -> {
                title = "Mode Grid"
                showRecyclerGrid()
                supportActionBar?.title = (title)
            }
        }
    }
    private fun showRecyclerGrid() {
        rvHeroes.layoutManager = GridLayoutManager(this, 2)
        val gridHeroAdapter = GridChara(list)
        rvHeroes.adapter = gridHeroAdapter
        gridHeroAdapter.setOnItemClickCallback(object : GridChara.OnItemClickCallback {
            override fun onItemClicked(data: Chara) {
                showSelectedHero(data)
            }
        })
    }

    private fun showSelectedHero(hero: Chara) {
        Toast.makeText(this, "Kamu memilih " + hero.name, Toast.LENGTH_SHORT).show()
    }


}