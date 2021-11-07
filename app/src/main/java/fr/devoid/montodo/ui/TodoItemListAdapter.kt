package fr.devoid.montodo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fr.devoid.montodo.R
import fr.devoid.montodo.data.TodoItem
import fr.devoid.montodo.databinding.TodoItemBinding

class TodoItemListAdapter(private val onTodoItemCheckListener: OnTodoItemCheckListener? = null) :
    ListAdapter<TodoItem, TodoItemListAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TodoItem>() {

            override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.todo_item, parent, false)
        return ViewHolder(itemView, onTodoItemCheckListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(itemView: View, private val onTodoItemCheckListener: OnTodoItemCheckListener? = null) :
        RecyclerView.ViewHolder(itemView) {
        private val binding = TodoItemBinding.bind(itemView)

        fun bind(todoItem: TodoItem) {
            val todoChecked = todoItem.doneAt != null
            binding.ivTaskDone.setImageResource(
                if (todoChecked) R.drawable.ic_checkbox_check else R.drawable.ic_checkbox_uncheck
            )
            binding.tvTaskText.text = todoItem.text

            binding.ivTaskDone.setOnClickListener {
                onTodoItemCheckListener?.onItemCheck(todoItem)
            }
        }

    }

    interface OnTodoItemCheckListener {
        fun onItemCheck(todoItem: TodoItem)
    }
}