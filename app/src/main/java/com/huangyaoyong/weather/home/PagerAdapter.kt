package com.huangyaoyong.weather.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.huangyaoyong.weather.citypage.CitiWeatherFragment

/**
 * @Author: huangyaoyong
 * @datetime: 2022/5/7
 * @desc:
 */
class PagerAdapter(act: FragmentActivity) : FragmentStateAdapter(act) {
    private val tab = ArrayList<String>()
    private val fragments = ArrayList<Fragment>()

    fun getTabTitle(position: Int): String {
        return tab[position]
    }

    fun resetDataAndNotify(newTab: List<CityInfo>?) {
        if (newTab == null) return

        fragments.clear()
        tab.clear()
        newTab.forEach {
            tab.add(it.name)
            fragments.add(CitiWeatherFragment.newInstance(it.code))
        }

        notifyDataSetChanged()
    }

    override fun getItemCount() = tab.size

    override fun createFragment(position: Int) = fragments[position]
}