package com.example.todolist
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ActivityMainBinding

//Без отложенной инцилизации, лямда выражение, test viewmodel


class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    var myAdapter: ToDoAdapter? = null
    private val viewModel by lazy {
        ViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        init()
    }
    private fun init() {
        val recyclerView: RecyclerView? = binding?.recyclerViewToDo
        recyclerView?.layoutManager = LinearLayoutManager(this@MainActivity)
        myAdapter = ToDoAdapter { position ->
            viewModel.deleteItem(position)
        }
        recyclerView?.adapter = myAdapter
        myAdapter!!.addTest()

        binding?.addButton?.setOnClickListener {
            binding?.apply {
                if (editTextName.text.isEmpty() && editTextDescription.text.isEmpty()) {
                    showMessage(applicationContext, "Введите название и(или) описание задачи")
                } else if (editTextName.text.isEmpty()) {
                    showMessage(applicationContext, "Введите имя задачи")
                } else if (editTextDescription.text.isEmpty()) {
                    showMessage(applicationContext, "Введите описание задачи")
                } else {
                    val name = editTextName.text.toString()
                    val description = editTextDescription.text.toString()
                    val items = Items(name = name, description = description)
                    viewModel.addElementForEditText(items)
                    editTextName.text.clear()
                    editTextDescription.text.clear()
                    hideKeyboard()
                }
            }
        }
    }
}

fun showMessage(context:Context, text: String){
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

