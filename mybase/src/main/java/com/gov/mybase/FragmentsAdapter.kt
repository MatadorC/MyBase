package com.gov.mybase

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * ===================================
 * @PackageName ：com.check.safemanage.adapter
 * @Description ：TODO
 * @Author ：fengxiaochuan
 * @Date ：2023/3/21 14:41
 * @Version ：1.0
 * ===================================
 */
class FragmentsAdapter(fm: FragmentManager?, private val mList: List<Fragment>?) :
    FragmentPagerAdapter(fm!!) {
    override fun getCount(): Int {
        return mList?.size ?: 0
    }

    override fun getItem(position: Int): Fragment {
        return mList?.get(position)!!
    }
}