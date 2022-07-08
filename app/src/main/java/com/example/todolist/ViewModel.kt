package com.example.todolist
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    //Функция удаления и добавления элементов
    var newItemsLiveData: MutableLiveData<Items> = MutableLiveData()
    var positionLiveData: MutableLiveData<Int> = MutableLiveData()
    var showMessageLiveData: MutableLiveData<String> = MutableLiveData()

    fun addElementsViewModel(name: String, description: String) {
        if (name.isEmpty() && description.isEmpty()) {
            showMessageLiveData.value = "Введите название и(или) описание задачи"
        } else if (name.isEmpty()) {
            showMessageLiveData.value = "Введите имя задачи"
        } else if (description.isEmpty()) {
            showMessageLiveData.value = "Введите описание задачи"
        } else {
            val item = Items(name, description)
            newItemsLiveData.value = item
        }
    }

    fun deleteItem(position: Int){
        positionLiveData.value = position
    }

}