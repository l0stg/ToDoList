package com.example.todolist

import android.content.Context

var itemsList: MutableList<Items> = mutableListOf()

data class Items(val name: String, val description: String)

//Функция удаления и добавления элементов
fun deleteItem(position: Int){
    val myListCopy = myAdapter.myList.toMutableList()
    myListCopy.removeAt(position)
    myAdapter.changesRV(myListCopy)
}

fun addElementForEditText(){
    if (binding.editTextName.text.isEmpty() && binding.editTextDescription.text.isEmpty())
        showMessage("Введите название и(или) описание задачи")
    else if (binding.editTextName.text.isEmpty())
        showMessage("Введите имя задачи")
    else if (binding.editTextDescription.text.isEmpty())
        showMessage("Введите описание задачи")
    else {
        val name = binding.editTextName.text.toString()
        val description = binding.editTextDescription.text.toString()
        val items = Items(name = name, description = description)
        val myListCopy = myAdapter.myList.toMutableList()
        myListCopy.add(items)
        myAdapter.changesRV(myListCopy)
        binding.editTextName.text.clear()
        binding.editTextDescription.text.clear()
    }
}