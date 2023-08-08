package com.minhhieu1402.todo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.minhhieu1402.todo.databinding.FragmentDoneBinding

class DoneFragment : Fragment() {
    private lateinit var binding: FragmentDoneBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDoneBinding.inflate(inflater, container, false)

        return binding.root
    }
}