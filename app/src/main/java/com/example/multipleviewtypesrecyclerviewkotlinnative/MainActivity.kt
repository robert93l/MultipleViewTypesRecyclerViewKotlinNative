package com.example.multipleviewtypesrecyclerviewkotlinnative

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.multipleviewtypesrecyclerviewkotlinnative.adapter.Adapter

class MainActivity : AppCompatActivity() {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findView()
        initList()
        reload()
    }

    private fun findView() {
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        recyclerView = findViewById(R.id.recyclerView)
    }

    private fun initList() {

        layoutManager = LinearLayoutManager(this)
        adapter = Adapter()

        adapter.onLoadMore = {
            loadMore()
        }

        swipeRefreshLayout.setOnRefreshListener {
            reload()
            swipeRefreshLayout.isRefreshing = false
        }

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    private fun reload() {
        recyclerView.post {
            adapter.reload(createDummyData(0, 15))
        }
    }

    private fun loadMore() {
        recyclerView.post {
            adapter.loadMore(createDummyData(adapter.itemCount, 10))
        }
    }

    private fun createDummyData(offset: Int, limit: Int): MutableList<String> {

        val list = mutableListOf<String>()

        for (i in offset..(offset + limit)) {

            when ((0..2).random()) {
                0 -> {
                    list.add("Type A")
                }

                1 -> {
                    list.add("Type B")
                }

                2 -> {
                    list.add("Type C")
                }
            }
        }

        return list
    }
}