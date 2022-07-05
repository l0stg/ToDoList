package com.example.todolist
import com.example.todolist.databinding.ActivityMainBinding

class ViewModel {
    //Функция удаления и добавления элементов
    private val myAdapter = ToDoAdapter() { position -> deleteItem(position)}

    fun addElementForEditText(items: Items){
       myAdapter.addElement(items)
    }
    fun deleteItem(position: Int){
        myAdapter.deleteItem(position)
    }

}