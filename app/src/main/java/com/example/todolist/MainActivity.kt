package com.example.todolist
import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ActivityMainBinding


lateinit var binding: ActivityMainBinding
lateinit var myAdapter: ToDoAdapter
lateinit var context: Context
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val layoutManager = LinearLayoutManager(applicationContext)
        val recyclerView: RecyclerView = binding.recyclerViewToDo
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        myAdapter = ToDoAdapter(itemsList)
        recyclerView.adapter = myAdapter
        context = this

        //проверка на пустые поля
        binding.addButton.setOnClickListener{
                addElementForEditText()
                hideKeyboard()
            }

        //Красивая полосочка между элементами
        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            layoutManager.orientation
        )
        recyclerView.addItemDecoration(dividerItemDecoration)

        //Удаление элемента
        myAdapter.setOnItemClickListener(object : ToDoAdapter.OnItemClickListener {
            override fun onItemClickDeleteButton(position: Int) {
                deleteItem(position)
            }
        })
    }
}

fun showMessage(text: String){
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

