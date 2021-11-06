package com.example.epoxytask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.epoxy.EpoxyRecyclerView

class MainActivity : AppCompatActivity() {
    var itemsList: List<Triple<Int, String, String>> = listOf(
        Triple(R.drawable.ic_launcher_background, "Леха Coooler", "88005553535"),
        Triple(R.drawable.ic_launcher_background, "Федя PHP", "88005553535"),
        Triple(R.drawable.ic_launcher_background, "Влад Java-developer", "88005553535"),
        Triple(R.drawable.ic_launcher_background, "Слава - Где роуты?", "88005553535"),
        Triple(R.drawable.ic_launcher_background, "Софа", "88005553535"),
        Triple(R.drawable.ic_launcher_background, "Михоил JavaScript gay", "88005553535"),
        Triple(R.drawable.ic_launcher_background, "Матвей - пизду побрей", "88005553535"),
        Triple(R.drawable.ic_launcher_background, "Таня", "88005553535"),
        Triple(R.drawable.ic_launcher_background, "Никита - Java поневоле", "88005553535"),
        Triple(R.drawable.ic_launcher_background, "Маша - C++ hate", "88005553535"),
        Triple(R.drawable.ic_launcher_background, "Роман - Рабовладелец", "88005553535"),
        Triple(R.drawable.ic_launcher_background, "Закладки", "899923424"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var epoxyRecyclerView: EpoxyRecyclerView = findViewById(R.id.rvEpoxy)
        epoxyRecyclerView.addItemDecoration(EpoxyCustomDecorator(this))

        val resources = mutableListOf<GridItem>()

        itemsList.forEach {
            resources.add(GridItem(it.first, it.second, it.third))
        }

    }
}