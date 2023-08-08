package com.minhhieu1402.todo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.minhhieu1402.todo.databinding.ItemNoteBinding
import com.minhhieu1402.todo.model.Note

class NoteAdapter(val inter: INoteAdapter) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(itemNote: Note) {
            binding.tvTitle.text = itemNote.title
            binding.tvContent.text = itemNote.content
            binding.tvTime.text = itemNote.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return inter.getItemNoteCount()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemNote = inter.getItemNoteByPosition(position)
        holder.bind(itemNote)
        holder.binding.root.setOnLongClickListener { it ->
            inter.onLongClick(position)
            true
        }
    }

    interface INoteAdapter {
        fun getItemNoteCount(): Int
        fun getItemNoteByPosition(position: Int): Note
        fun onLongClick(position: Int)
    }
}