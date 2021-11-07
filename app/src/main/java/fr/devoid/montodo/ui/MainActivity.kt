package fr.devoid.montodo.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import fr.devoid.montodo.R
import fr.devoid.montodo.data.TodoItem
import fr.devoid.montodo.databinding.ActivityMainBinding
import fr.devoid.montodo.databinding.NewTaskDialogBinding
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), TodoItemListAdapter.OnTodoItemCheckListener {

    private var binding: ActivityMainBinding? = null

    private val viewModel: MainViewModel by viewModels()

    private var todoItemListAdapter: TodoItemListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.fabAddTask?.setOnClickListener {
            showAddTaskDialog()
        }

        todoItemListAdapter = TodoItemListAdapter(this)

        setupTodoList()

        lifecycleScope.launchWhenStarted {
            viewModel.todoItemsFlow.collect {
                renderUi(it)
            }
        }
    }

    private fun setupTodoList() {
        binding?.rvTodoList?.adapter = todoItemListAdapter
        binding?.rvTodoList?.layoutManager = LinearLayoutManager(this)

        //Ajout d'un DividerItemDecoration pour avoir une ligne entre chaque Item
        binding?.rvTodoList?.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        //Permet d'ajouter la fonctionnalité de "SWIPE" à un recyclerview
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val todoItem = todoItemListAdapter?.currentList?.get(viewHolder.adapterPosition)

                if (direction == ItemTouchHelper.LEFT || direction == ItemTouchHelper.RIGHT) {
                    todoItem?.let { viewModel.onTodoDelete(todoItem) }
                }
            }

        }).attachToRecyclerView(binding?.rvTodoList)

    }

    private fun renderUi(todoItems: List<TodoItem>) {
        todoItemListAdapter?.submitList(todoItems)
        binding?.tvHomeMsg?.text = getHomeMessageForItems(todoItems)

        val todoListEmpty = todoItems.isEmpty()

        binding?.tvHomeMsg?.isVisible = !todoListEmpty
        binding?.rvTodoList?.isVisible = !todoListEmpty

        binding?.ivSmiley?.isVisible = todoListEmpty
        binding?.tvNoTasks?.isVisible = todoListEmpty
    }

    private fun getHomeMessageForItems(todoItems: List<TodoItem>): String {
        val taskUndoneCount = todoItems.count {
            it.doneAt == null
        }

        return resources.getQuantityString(R.plurals.you_have_tasks_to_do, taskUndoneCount, taskUndoneCount)
    }

    private fun showAddTaskDialog() {
        val newTaskDialogBinding = NewTaskDialogBinding.inflate(layoutInflater)

        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.new_task)
            .setView(newTaskDialogBinding.root)
            .setNeutralButton(R.string.cancel, null)
            .setPositiveButton(R.string.add) { dialog, which ->
                viewModel.onAddTodoItem(newTaskDialogBinding.root.text.toString())
            }
            .show()
    }

    override fun onItemCheck(todoItem: TodoItem) {
        viewModel.onTodoItemCheck(todoItem)
    }

}