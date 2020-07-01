package com.example.databankabacus

import android.database.Cursor
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.databankabacus.utils.SqliteUsuario


class proyectosActivity : AppCompatActivity() {
   private var mListView: ListView? = null
    private lateinit var sqliteUsuario: SqliteUsuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proyectos)
        this.mListView=findViewById(R.id.listViewProyecto)

        val listData: ArrayList<String?> = ArrayList()

        listData.add("Proyecto 1")
        listData.add("Proyecto 2")

        val adapter: ListAdapter =  ArrayAdapter<String?>(this, android.R.layout.simple_list_item_1, listData)
        mListView?.setAdapter(adapter)
    }
}
