package com.example.todolist
import android.content.Context
import com.example.todolist.databinding.ActivityMainBinding

class ViewModel {
    //Функция удаления и добавления элементов
    private val myAdapter = ToDoAdapter() { position -> deleteItem(position)}

    fun addElementForEditText(binding: ActivityMainBinding){
       myAdapter.addElement(binding)
    }
    fun deleteItem(position: Int){
        myAdapter.deleteItem(position)
    }

}