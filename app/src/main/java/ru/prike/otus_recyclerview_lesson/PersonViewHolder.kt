package ru.prike.otus_recyclerview_lesson

import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PersonViewHolder(
    private val view: View,
    private val listener: Listener
) : RecyclerView.ViewHolder(view) {

    private val name: TextView by lazy { view.findViewById(R.id.name) }
    private val message: TextView by lazy { view.findViewById(R.id.message) }
    private val date: TextView by lazy { view.findViewById(R.id.date) }
    private val image: ImageView by lazy { view.findViewById(R.id.image) }
    private val root: ViewGroup by lazy { view.findViewById(R.id.root) }
    private val deleteView: ImageButton by lazy { view.findViewById(R.id.delete) }

    fun bind(item: PersonItem) {
        name.text = item.name
        message.text = item.message
        date.text = item.date
        image.setImageResource(item.image)
        root.setBackgroundResource(item.background)

        root.setOnClickListener {
            listener.onItemClicked(item.id)
        }

        deleteView.setOnClickListener {
            listener.onItemActionClicked(item.id)
        }
    }
}