package io.djnr.backdrop.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.view.ViewGroup
import io.djnr.backdrop.fragments.HomeFragment
import java.util.ArrayList


class MainPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }

    override fun getItemPosition(`object`: Any): Int {
        return if (mFragmentList.contains(`object` as Fragment)) {
            mFragmentList.indexOf(`object`)
        } else {
            PagerAdapter.POSITION_NONE
        }
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    fun clear() {
        mFragmentList.clear()
        mFragmentTitleList.clear()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        // return null to display only the icon
        return null
    }
}