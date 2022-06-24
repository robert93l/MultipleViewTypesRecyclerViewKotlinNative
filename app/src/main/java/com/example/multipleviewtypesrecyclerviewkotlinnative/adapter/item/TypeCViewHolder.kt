package com.example.multipleviewtypesrecyclerviewkotlinnative.adapter.item

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.multipleviewtypesrecyclerviewkotlinnative.R
import java.lang.ref.WeakReference

class TypeCViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var view: WeakReference<View> = WeakReference(itemView)

    private var textView: TextView? = null

    var contentC = ""

    var onItemClick: ((String) -> Unit)? = null

    init {
        findView()
        setListener()
    }

    private fun findView() {
        textView = view.get()?.findViewById(R.id.textView)

    }

    private fun setListener() {
        view.get()?.setOnClickListener {
            onItemClick?.let {
                it(contentC)
            }
        }
    }

    fun updateView() {
        textView?.text = contentC
    }


}