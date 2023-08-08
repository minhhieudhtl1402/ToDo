package com.minhhieu1402.todo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.minhhieu1402.todo.adapter.NoteAdapter
import com.minhhieu1402.todo.databinding.FragmentListBinding
import com.minhhieu1402.todo.helper.NoteDBHelper

class ListFragment : Fragment(), NoteAdapter.INoteAdapter, MyDialogFragment.OnInputListener,
    View.OnClickListener {
    private lateinit var binding: FragmentListBinding
    private lateinit var dbHelper: NoteDBHelper
    private var notes = arrayListOf<com.minhhieu1402.todo.model.Note>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        dbHelper = NoteDBHelper(requireContext())
        notes = dbHelper.readAllNotes()
        binding.rcList.adapter = NoteAdapter(this)
        binding.rcList.layoutManager =
            GridLayoutManager(requireContext(), 1, GridLayoutManager.VERTICAL, false)

        binding.btnAdd.setOnClickListener(this)

        return binding.root
    }

    override fun getItemNoteCount(): Int {
        return notes.size
    }

    override fun getItemNoteByPosition(position: Int): com.minhhieu1402.todo.model.Note {
        return notes.get(position)
    }

    override fun onLongClick(position: Int) {
        dbHelper.delete(notes.get(position))
        updateData()
    }

    private fun updateData() {
        notes = dbHelper.readAllNotes()
        binding.rcList.adapter?.notifyDataSetChanged()
    }

    override fun sendInput(note: com.minhhieu1402.todo.model.Note) {
        dbHelper.insertToTable(note)
        updateData()
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.btnAdd.id -> {
                val dialog = MyDialogFragment(this)
                dialog.show(childFragmentManager, "My custom dialog")
                binding.rcList.adapter?.notifyDataSetChanged()
            }

        }
    }


}