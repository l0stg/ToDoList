package com.example.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.UiThread
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.databinding.MyTodoViewBinding

class DataDiffCallback(
    private var oldList: MutableList<Items>,
    private var newList: MutableList<Items>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldElement = oldList[oldItemPosition]
        val newElement = newList[newItemPosition]
        return oldElement.name == newElement.name
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldElement = oldList[oldItemPosition]
        val newElement = newList[newItemPosition]
        return oldElement == newElement
    }
}

class ToDoAdapter(val onItemClicked: ((position: Int) -> Unit)): RecyclerView.Adapter<ToDoAdapter.MyViewHolder>() {

    private var myList = ArrayList<Items>()

    //функция DiffUtil для обновления данных
    private fun changesRV(position: Int, myListCopy: MutableList<Items>) {
        notifyItemRangeChanged(position, itemCount)
        val diffCallback = DataDiffCallback(myList, myListCopy)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        myList = ArrayList(myListCopy)
        diffResult.dispatchUpdatesTo(this)
    }

    class MyViewHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = MyTodoViewBinding.bind(item)
        val deleteButton = binding.imDeleteButton
        fun bind(data: Items) = with(binding) {
            tvName.text = data.name
            tvDescription.text = data.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.my_todo_view, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(myList[position])
        holder.deleteButton.setOnClickListener {
            onItemClicked(position)
        }
    }

    override fun getItemCount(): Int = myList.size

    fun addElement(items: Items) {
        val myListCopy = ArrayList(myList)
        myListCopy.add(items)
        changesRV(0, myListCopy)
    }

    fun deleteItem(position: Int) {
        val myListCopy = ArrayList(myList)
        myListCopy.removeAt(position)
        changesRV(position, myListCopy)
    }
}




