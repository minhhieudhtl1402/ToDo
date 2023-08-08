package com.minhhieu1402.todo.ui.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.DialogFragment
import com.minhhieu1402.todo.R
import com.minhhieu1402.todo.databinding.FragmentDialogBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class MyDialogFragment(val inter: OnInputListener) : DialogFragment(), View.OnClickListener {


    interface OnInputListener {
        fun sendInput(note: com.minhhieu1402.todo.model.Note)
    }

    private lateinit var currentTime: String
    private lateinit var binding: FragmentDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDialogBinding.inflate(inflater, container, false)
        binding.btnAdd.setOnClickListener(this)
        val simpleDateFormat = SimpleDateFormat("MMMM d h:mm a")
        currentTime = simpleDateFormat.format(Date())
        binding.tvTime.setText(currentTime)

        binding.edtContent.addTextChangedListener(textWatcher())
        binding.btnDismiss.setOnClickListener(this)
        return binding.root
    }

    inner class textWatcher : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val count=s?.length
            binding.tvCharacterCount.setText(count.toString()+" characters")
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }

    override fun getTheme(): Int {
        return R.style.DialogTheme
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.btnAdd.id -> {
                val title = binding.edtTitle.text.toString()
                val content = binding.edtContent.text.toString()
                val note = com.minhhieu1402.todo.model.Note(title, content, currentTime)
                inter.sendInput(note)
                dialog?.dismiss()
            }

            binding.btnDismiss.id -> {
                dialog?.dismiss()
            }


        }
    }
}