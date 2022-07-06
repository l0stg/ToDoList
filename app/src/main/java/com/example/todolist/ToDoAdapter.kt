package com.example.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.MyTodoViewBinding

class ToDoAdapter(val onItemClicked: ((position: Int) -> Unit)): RecyclerView.Adapter<ToDoAdapter.MyViewHolder>() {

    private var myList = ArrayList<Items>()

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
        myList.add(items)
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        myList.removeAt(position)
        notifyDataSetChanged()
    }
}




