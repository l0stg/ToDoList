package com.example.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.databinding.MyTodoViewBinding

/*class DataDiffCallback(
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
}*/

class ToDoAdapter(var onItemClicked: ((position: Int) -> Unit)) : RecyclerView.Adapter<ToDoAdapter.MyViewHolder>() {

    private val myList = ArrayList<Items>()
    
    fun addTest() {
        (1..10).forEach {
            val name = "its $it name"
            val desc = "its $it desc"
            val items = Items(name, desc)
            myList.add(items)
        }
    }

    fun deleteItem(position: Int) {
        //val myListCopy = this.myList.toMutableList()
        this.myList.removeAt(position)
        println(myList)
        this.notifyDataSetChanged()
        //showMessage("Удалили $position")
        //println("Удалили $position")
        //changesRV(myListCopy)
    }

    //функция DiffUtil для обновления данных
   /* private fun changesRV(fillListCopy: MutableList<Items>) {
        val diffCallback = DataDiffCallback(this.myList, fillListCopy)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        //this.myList = fillListCopy.toMutableList() as ArrayList<Items>
        //println(myList)
        //itemsList =
        //   myList // чтобы при пересоздании активити сохранялись данные, надо бы уйти от этого
        diffResult.dispatchUpdatesTo(this)
    }*/

    class MyViewHolder(item: View) : RecyclerView.ViewHolder(item) {
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
        this.notifyDataSetChanged()
    }
}




