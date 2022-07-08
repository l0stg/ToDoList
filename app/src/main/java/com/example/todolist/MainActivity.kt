package com.example.todolist
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    var myAdapter : ToDoAdapter? = null

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        myAdapter = ToDoAdapter {viewModel.deleteItem(it)}
        val recyclerView: RecyclerView? = binding?.recyclerViewToDo
        recyclerView?.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView?.adapter = myAdapter

        fun <T> MutableLiveData<T>.subscribe(action: (T) -> Unit) {
            observe(this@MainActivity) { it?.let { action(it) } }
        }
        //использую LiveData для наблюдением
        with(viewModel) {
            newItemsLiveData.subscribe {
                myAdapter!!.addElement(it)
                binding!!.editTextName.text.clear()
                binding!!.editTextDescription.text.clear()
            }
            positionLiveData.subscribe {
                myAdapter!!.deleteItem(it)
            }
            showMessageLiveData.subscribe {
                showMessage(this@MainActivity, it)
            }
        }
        //Добавление элемента
        binding?.addButton?.setOnClickListener {
            binding?.apply {
                    viewModel.addElementsViewModel(editTextName.text.toString(), editTextDescription.text.toString())
                    hideKeyboard()
                }
            }
        }
    }

fun showMessage(context:Context, text: String){
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

