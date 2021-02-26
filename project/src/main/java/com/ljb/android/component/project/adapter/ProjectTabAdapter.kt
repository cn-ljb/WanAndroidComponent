package com.ljb.android.component.project.adapter

import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.ljb.android.component.project.bean.ProjectTabBean
import com.ljb.android.component.project.view.fragment.ProjectListFragment

class ProjectTabAdapter(private val data: List<ProjectTabBean.ListBean>, manager: FragmentManager) :
    FragmentStatePagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var curFragment: ProjectListFragment? = null

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Fragment {
        val item = data[position]
        val f = ProjectListFragment()
        val bundle = Bundle()
        bundle.putString(ProjectListFragment.KEY_ID, item.id)
        bundle.putString(ProjectListFragment.KEY_NAME, item.name)
        f.arguments = bundle
        return f
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        super.setPrimaryItem(container, position, `object`)
        if (`object` is ProjectListFragment) {
            curFragment = `object`
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return data[position].name
    }

    fun getCurFragment(): ProjectListFragment? {
        return curFragment
    }
}