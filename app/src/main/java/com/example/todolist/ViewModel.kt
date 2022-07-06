package com.example.todolist
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.todolist.databinding.ActivityMainBinding

class ViewModel {
    //Функция удаления и добавления элементов
    var newItemsLiveData: MutableLiveData<Items> = MutableLiveData()
    var positionLiveData: MutableLiveData<Int> = MutableLiveData()

    fun addElementsViewModel(name: String, description: String, context: Context) {
        if (name.isEmpty() && description.isEmpty()) {
            showMessage(context, "Введите название и(или) описание задачи")
        } else if (name.isEmpty()) {
            showMessage(context, "Введите имя задачи")
        } else if (description.isEmpty()) {
            showMessage(context, "Введите описание задачи")
        } else {
            val item = Items(name, description)
            newItemsLiveData.value = item
        }
    }

    fun deleteItem(position: Int){
        positionLiveData.value = position
    }

}