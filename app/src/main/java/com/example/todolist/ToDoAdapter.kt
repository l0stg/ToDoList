package com.example.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
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

class ToDoAdapter(var myList: MutableList<Items>) : RecyclerView.Adapter<ToDoAdapter.MyViewHolder>() {

    //функция DiffUtil для обновления данных
    fun changesRV(fillListCopy: MutableList<Items>){
        val diffCallback = DataDiffCallback(myList, fillListCopy)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        myList = fillListCopy.toMutableList()
        itemsList = myList // чтобы при пересоздании активити сохранялись данные, надо бы уйти от этого
        diffResult.dispatchUpdatesTo(this)
    }

    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClickDeleteButton(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        mListener = listener
    }

    class MyViewHolder(binding: MyTodoViewBinding, listener: OnItemClickListener) : RecyclerView.ViewHolder(binding.root) {
        private val name: TextView = binding.tvName
        private val description: TextView = binding.tvDescription
        fun bind(data: Items){
           name.text = data.name
           description.text = data.description
        }
        init {
            binding.imDeleteButton.setOnClickListener{
                listener.onItemClickDeleteButton(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int, ): MyViewHolder {
        val itemView = MyTodoViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(myList[position])

    }

    override fun getItemCount(): Int = myList.size
}