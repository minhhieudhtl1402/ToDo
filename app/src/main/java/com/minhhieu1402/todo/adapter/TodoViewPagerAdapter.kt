package com.minhhieu1402.todo.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.ListFragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.minhhieu1402.todo.ui.fragment.DoneFragment

class TodoViewPagerAdapter(val fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    companion object {
        val names = arrayListOf<String>("List", "Done")
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return com.minhhieu1402.todo.ui.fragment.ListFragment()
            1 -> return DoneFragment()
            else -> return ListFragment()
        }
    }

}