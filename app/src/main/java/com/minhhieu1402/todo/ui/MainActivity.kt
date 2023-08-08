package com.minhhieu1402.todo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.minhhieu1402.todo.R
import com.minhhieu1402.todo.adapter.TodoViewPagerAdapter
import com.minhhieu1402.todo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.vpMain.adapter = TodoViewPagerAdapter(this)
        TabLayoutMediator(binding.tlMain, binding.vpMain) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = TodoViewPagerAdapter.names.get(position)
                    tab.icon = getDrawable(R.drawable.baseline_featured_play_list_24)
                }

                1 -> {
                    tab.text = TodoViewPagerAdapter.names.get(position)
                    tab.icon = getDrawable(R.drawable.baseline_done_all_24)
                }
            }
        }.attach()


    }

}