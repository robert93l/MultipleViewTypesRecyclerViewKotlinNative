package com.example.multipleviewtypesrecyclerviewkotlinnative.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.multipleviewtypesrecyclerviewkotlinnative.R
import com.example.multipleviewtypesrecyclerviewkotlinnative.adapter.item.TypeAViewHolder
import com.example.multipleviewtypesrecyclerviewkotlinnative.adapter.item.TypeBViewHolder
import com.example.multipleviewtypesrecyclerviewkotlinnative.adapter.item.TypeCViewHolder

class Adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ViewHolderType {
        TYPE_A, TYPE_B, TYPE_C
    }

    private var list: MutableList<String> = mutableListOf()

    var onLoadMore: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {

            ViewHolderType.TYPE_A.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder_type_a, parent, false)
                return TypeAViewHolder(view)
            }

            ViewHolderType.TYPE_B.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder_type_b, parent, false)
                return TypeBViewHolder(view)
            }

            ViewHolderType.TYPE_C.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder_type_c, parent, false)
                return TypeCViewHolder(view)

            }

            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder_type_a, parent, false)
                return TypeAViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {

            is TypeAViewHolder -> {
                holder.contentA = list[position]
                holder.onItemClick = { content ->
                    Toast.makeText(holder.itemView.context, content, Toast.LENGTH_SHORT).show()
                }
                holder.updateView()
            }

            is TypeBViewHolder -> {
                holder.contentB = list[position]
                holder.onItemClick = { content ->
                    Toast.makeText(holder.itemView.context, content, Toast.LENGTH_SHORT).show()
                }
                holder.updateView()
            }

            is TypeCViewHolder -> {
                holder.contentC = list[position]
                holder.onItemClick = { content ->
                    Toast.makeText(holder.itemView.context, content, Toast.LENGTH_SHORT).show()
                }
                holder.updateView()
            }
        }

        if (position == list.size - 1) {
            onLoadMore?.let {
                it()
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            "Type A" -> ViewHolderType.TYPE_A.ordinal
            "Type B" -> ViewHolderType.TYPE_B.ordinal
            "Type C" -> ViewHolderType.TYPE_C.ordinal
            else -> ViewHolderType.TYPE_A.ordinal
        }
    }

    fun reload(list: MutableList<String>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun loadMore(list: MutableList<String>) {
        this.list.addAll(list)
        notifyItemRangeChanged(this.list.size - list.size + 1, list.size)
    }

}