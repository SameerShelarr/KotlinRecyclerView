package com.example.kotlinrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity(), ItemAdapter.OnItemClickListener {

    private val dummyList = generateDummyList(500)
    private val adapter = ItemAdapter(dummyList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
    }

    override fun onItemClick(position: Int, item: Item) {
        Toast.makeText(this, "Item $position Clicked", Toast.LENGTH_SHORT).show()
        item.text2 = "This item was clicked"
        adapter.notifyItemChanged(position)
    }

    private fun generateDummyList(size: Int): ArrayList<Item> {
        val list = ArrayList<Item>()
        for (i in 0..size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_android
                1 -> R.drawable.ic_outlet
                else -> R.drawable.ic_security
            }
            val item = Item(drawable, "Item $i", "Line 2")
            list += item
        }
        return list
    }

    fun insertItem(view: View) {
        val index = Random.nextInt(8)
        val newIem = Item(R.drawable.ic_security, "New Item $index", "Line 2")
        dummyList.add(index, newIem)
        adapter.notifyItemInserted(index)
    }

    fun deleteItem(view: View) {
        val index = Random.nextInt(8)
        dummyList.remove(dummyList[index])
        adapter.notifyItemRemoved(index)
    }
}