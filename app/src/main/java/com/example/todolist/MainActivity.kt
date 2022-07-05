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
        val recyclerView: RecyclerView? = binding?.recyclerViewToDo
        recyclerView?.layoutManager = LinearLayoutManager(applicationContext)
        myAdapter = ToDoAdapter() { position ->
            viewModel.deleteItem(position)
        }
        recyclerView?.adapter = myAdapter
        myAdapter!!.addTest()
        //проверка на пустые поля
        binding?.addButton?.setOnClickListener{
            viewModel.addElementForEditText(binding!!)
            hideKeyboard()
        }



        //Удаление элемента
        /*myAdapter!!.setOnItemClickListener(object : ToDoAdapter.OnItemClickListener {
            override fun onItemClickDeleteButton(position: Int) {
                deleteItem(position)
            }
        })*/
    }
}

fun showMessage(text: String){
    //Toast.makeText(MainActivity().applicationContext, text, Toast.LENGTH_LONG).show()
}

