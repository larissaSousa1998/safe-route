package com.example.saferoute.activity.GroupLocomotion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.saferoute.R
import com.example.saferoute.adapter.GruposLocomocaoAdapter
import com.example.saferoute.adapter.GruposLocomocaoInAdapter
import com.example.saferoute.domain.GroupoLocomocaoUsuaria
import com.example.saferoute.domain.Viagem
import com.example.saferoute.usecase.ClearUserSession


class FindGroups : AppCompatActivity(), GruposLocomocaoInAdapter.OnItemClickListener {


    val grupos = listOf(
        GroupoLocomocaoUsuaria(
            "Fernanda Laurita",
            "Faculdade",
            Viagem("Origem", "-4545456", "-454554",
            "Destino","-4545456", "-454554")
        ),
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_groups)

        val v: RecyclerView = findViewById(R.id.recycler_view_find_groups_locomotion)
        v.layoutManager = LinearLayoutManager(this)
        v.adapter = GruposLocomocaoInAdapter(grupos = grupos, this)
    }

    override fun onItemClick(position: Int) {
        print(grupos[position])
    }

    override fun onStop() {
        super.onStop()
        ClearUserSession.execute(applicationContext)
    }
}