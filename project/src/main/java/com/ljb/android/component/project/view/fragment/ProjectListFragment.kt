package com.ljb.android.component.project.view.fragment

import com.ljb.android.component.project.contract.ProjectListContract
import com.ljb.android.component.project.presenter.ProjectListPresenter
import mvp.ljb.kt.fragment.BaseMvpFragment
import com.ljb.android.component.project.R

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/26
 * @Description input description
 **/
class ProjectListFragment : BaseMvpFragment<ProjectListContract.IPresenter>(),
    ProjectListContract.IView {

    companion object {
        const val KEY_ID = "key_id"
        const val KEY_NAME = "key_name"
    }

    override fun registerPresenter() = ProjectListPresenter::class.java

    override fun getLayoutId() = R.layout.fragment_project_list
}
