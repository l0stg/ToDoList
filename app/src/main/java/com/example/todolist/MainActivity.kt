package com.example.todolist
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ActivityMainBinding

//Без отложенной инцилизации, лямда выражение, test viewmodel


class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    var myAdapter : ToDoAdapter? = null

    private val viewModel by lazy {
        ViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        myAdapter = ToDoAdapter { position -> viewModel.deleteItem(position) }
        val recyclerView: RecyclerView? = binding?.recyclerViewToDo
        recyclerView?.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView?.adapter = myAdapter
        myAdapter!!.addTest()
        //использую LiveData для наблюдением
        viewModel.tasKList.observe(this, Observer {
            it?.let {
                myAdapter!!.addElement(it)
            }
        })

        viewModel.positionLiveData.observe(this, Observer {
            it?.let {
                myAdapter!!.deleteItem(it)
            }
        })
        
        //Добавление элемента
        binding?.addButton?.setOnClickListener {
            binding?.apply {
                    val name1 = editTextName.text.toString()
                    val description1 = editTextDescription.text.toString()
                    viewModel.addElementsViewModel(name1, description1, this@MainActivity)
                    editTextName.text.clear()
                    editTextDescription.text.clear()
                    hideKeyboard()
                }
            }
        }
    }


fun showMessage(context:Context, text: String){
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

