package com.example.todolist

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
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

class ToDoAdapter() : RecyclerView.Adapter<ToDoAdapter.MyViewHolder>() {

    private var myList =  mutableListOf<Items>()



    fun addTest(){
        (1..10).forEach {
            val name = "its $it name"
            val desc = "its $it desc"
            val items = Items(name, desc)
            myList.add(items)
        }
    }

    //функция DiffUtil для обновления данных
    private fun changesRV(fillListCopy: MutableList<Items>) {
        val diffCallback = DataDiffCallback(myList, fillListCopy)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        myList = fillListCopy.toMutableList()
        //itemsList =
         //   myList // чтобы при пересоздании активити сохранялись данные, надо бы уйти от этого
        diffResult.dispatchUpdatesTo(this)
    }

    class MyViewHolder(binding: MyTodoViewBinding) : RecyclerView.ViewHolder(binding.root) {
        private val name: TextView = binding.tvName
        private val description: TextView = binding.tvDescription
        fun bind(data: Items) {
            name.text = data.name
            description.text = data.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int, ): MyViewHolder {
        val itemView = MyTodoViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(myList[position])
        holder.itemView.setOnClickListener {
            myList.removeAt(position)
            changesRV()
        }
    }

    override fun getItemCount(): Int = myList.size

    fun addElement(context: Context, binding: ActivityMainBinding){
      if (binding.editTextName.text.isEmpty() && binding.editTextDescription.text.isEmpty())
            showMessage(context, "Введите название и(или) описание задачи")
        else if (binding.editTextName.text.isEmpty())
            showMessage(context,"Введите имя задачи")
        else if (binding.editTextDescription.text.isEmpty())
            showMessage(context,"Введите описание задачи")
        else {
            val name = binding.editTextName.text.toString()
            val description = binding.editTextDescription.text.toString()
            val items = Items(name = name, description = description)
            val myListCopy = myList.toMutableList()
            myListCopy.add(items)
            println(myListCopy)
           // changesRV(myListCopy)
            binding.editTextName.text.clear()
            binding.editTextDescription.text.clear()
        }
    }

    fun deleteItem(position: Int){
        val myListCopy = myList.toMutableList()
        myListCopy.removeAt(position)
        //changesRV(myListCopy)
    }


}



