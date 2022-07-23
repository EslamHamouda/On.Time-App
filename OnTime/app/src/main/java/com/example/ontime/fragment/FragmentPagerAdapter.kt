package com.example.ontime.fragment

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.ontime.R


class FragmentPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    val mFragmentList=ArrayList<Fragment>()
    val mFragmentTitle=ArrayList<String>()
    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return mFragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitle[position]
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return mFragmentList.size
    }
    fun addFragment(fragment: Fragment,title: String){
        mFragmentList.add(fragment)
        mFragmentTitle.add(title)
    }
}