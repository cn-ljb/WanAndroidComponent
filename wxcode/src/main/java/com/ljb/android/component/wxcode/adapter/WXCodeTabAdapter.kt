package com.ljb.android.component.wxcode.adapter

import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.ljb.android.component.wxcode.bean.WXCodeTabBean
import com.ljb.android.component.wxcode.view.fragment.WXArticleListFragment

/**
 * Author:Ljb
 * Time:2021/2/23
 * There is a lot of misery in life
 **/
class WXCodeTabAdapter(private val data: List<WXCodeTabBean.ListBean>, manager: FragmentManager) :
    FragmentStatePagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var curFragment: WXArticleListFragment? = null

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Fragment {
        val item = data[position]
        val f = WXArticleListFragment()
        val bundle = Bundle()
        bundle.putString(WXArticleListFragment.KEY_ID, item.id)
        bundle.putString(WXArticleListFragment.KEY_NAME, item.name)
        f.arguments = bundle
        return f
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        super.setPrimaryItem(container, position, `object`)
        if (`object` is WXArticleListFragment) {
            curFragment = `object`
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return data[position].name
    }

    fun getCurFragment(): WXArticleListFragment? {
        return curFragment
    }
}