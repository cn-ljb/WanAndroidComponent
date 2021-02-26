package com.ljb.android.component.project.view.fragment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ljb.android.comm.mvp.CommMvpFragment
import com.ljb.android.comm.view.act.CommWebViewActivity
import com.ljb.android.comm.weiget.page.PageState
import com.ljb.android.component.project.R
import com.ljb.android.component.project.adapter.ProjectListAdapter
import com.ljb.android.component.project.bean.ProjectListBean
import com.ljb.android.component.project.contract.ProjectListContract
import com.ljb.android.component.project.databinding.FragmentProjectListBinding
import com.ljb.android.component.project.databinding.ProjectLayoutProjectListContentBinding
import com.ljb.android.component.project.presenter.ProjectListPresenter

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/26
 * @Description input description
 **/
class ProjectListFragment :
    CommMvpFragment<ProjectListContract.IPresenter, FragmentProjectListBinding>(),
    ProjectListContract.IView, SwipeRefreshLayout.OnRefreshListener {

    companion object {
        const val KEY_ID = "key_id"
        const val KEY_NAME = "key_name"
    }

    private lateinit var mBindContentView: ProjectLayoutProjectListContentBinding

    private val mListAdapter = ProjectListAdapter()

    private var mId: String = "0"
    private var mName: String = ""
    private var mPage: Int = 1

    override fun registerPresenter() = ProjectListPresenter::class.java

    override fun getLayoutId() = R.layout.fragment_project_list

    override fun registerBinding(): FragmentProjectListBinding {
        return FragmentProjectListBinding.inflate(layoutInflater, mParentView, false)
    }

    override fun immersionBarEnabled() = false

    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        arguments?.run {
            mId = getString(KEY_ID, "0")
            mName = getString(KEY_NAME, "")
        }
    }

    override fun initView() {
        initPageView()
        initRecyclerView()
    }

    private fun initPageView() {
        val contentView = mBind.pageLayout.getPageView(PageState.STATE_SUCCESS)
        mBindContentView = ProjectLayoutProjectListContentBinding.bind(contentView)
    }

    private fun initRecyclerView() {
        mBindContentView.swRefresh.apply {
            setColorSchemeResources(
                R.color.color_red,
                R.color.color_FF9D9D
            )
            setOnRefreshListener(this@ProjectListFragment)
        }

        mBindContentView.rvList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = mListAdapter
            mListAdapter.loadMoreModule.isEnableLoadMore = true
            mListAdapter.loadMoreModule.isEnableLoadMoreIfNotFullPage = true
            mListAdapter.loadMoreModule.setOnLoadMoreListener { onLoadMore() }
            mListAdapter.setOnItemClickListener { _, _, position ->
                val url = mListAdapter.data[position].link
                goWebView(url)
            }
        }
    }

    override fun initData() {
        mBindContentView.swRefresh.isRefreshing = true
        showPage(PageState.STATE_LOADING)
        onRefresh()
    }

    fun scrollToTop() {
        mBindContentView.rvList.scrollToPosition(0)
    }

    override fun onRefresh() {
        mPage = 1
        getPresenter().getProjectList(mId, mPage)
    }

    private fun onLoadMore() {
        getPresenter().getProjectList(mId, mPage)
    }

    override fun onProjectListSuccess(bean: ProjectListBean) {
        if (mPage == 1) {
            mBindContentView.swRefresh.isRefreshing = false
            showPage(PageState.STATE_EMPTY)
            mListAdapter.data.clear()
        } else {
            mListAdapter.loadMoreModule.loadMoreComplete()
        }
        if (bean.data.datas.isEmpty()) {
            //没有更多数据
            mListAdapter.loadMoreModule.loadMoreEnd(true)
        } else {
            mListAdapter.data.addAll(bean.data.datas)
            mListAdapter.notifyDataSetChanged()
            mPage++
        }
    }

    override fun onProjectListError() {
        if (mPage == 1) {
            mBindContentView.swRefresh.isRefreshing = false
        } else {
            mListAdapter.loadMoreModule.loadMoreComplete()
        }
    }

    private fun goWebView(url: String) {
        CommWebViewActivity.startActivity(activity!!, url)
    }


    private fun showPage(state: PageState) {
        mBind.pageLayout.setPage(state)
    }
}
