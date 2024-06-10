package ru.prike.otus_recyclerview_lesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(
    private val listener: Listener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var list = listOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewTypes.PERSON.id -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.person_item, parent, false)
                PersonViewHolder(view, listener)
            }
            ViewTypes.DAY.id -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.day_item, parent, false)
                DayViewHolder(view)
            }
            else -> throw IllegalArgumentException("Not found view type for chat adapter")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list.getOrNull(position) ?: return
        when (item) {
            is PersonItem -> {
                (holder as? PersonViewHolder)?.bind(item)
            }
            is DayItem -> {
                (holder as? DayViewHolder)?.bind(item)
            }
        }
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is PersonItem -> ViewTypes.PERSON.id
            is DayItem -> ViewTypes.DAY.id
            else -> -1
        }
    }

    fun setItems(items: List<Item>) {
        list = items
        notifyDataSetChanged()
    }

    fun removeItem(id: Int) {
        val position = list.mapNotNull { it as? PersonItem }.indexOfFirst { it.id == id }
        if (position < 0) return
        list = list.toMutableList().also {
            it.removeAt(position)
        }.toList()
        notifyItemRemoved(position)
    }

    fun moved() {
        notifyItemMoved(1, 5)
    }

    enum class ViewTypes(val id: Int) {
        DAY(R.layout.day_item),
        PERSON(R.layout.person_item)
    }
}

//abstract class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
//    protected val context = view.context
//    abstract fun bind(item: Item)
//}
//
interface Item