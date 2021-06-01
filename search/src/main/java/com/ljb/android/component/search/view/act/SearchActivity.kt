package com.ljb.android.component.search.view.act

import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.SizeUtils
import com.ljb.android.comm.adapter.decoration.RVItemDecorationMargin
import com.ljb.android.comm.mvp.CommMvpActivity
import com.ljb.android.comm.router.RouterConfig
import com.ljb.android.comm.view.act.CommWebViewActivity
import com.ljb.android.comm.weiget.page.PageState
import com.ljb.android.component.search.R
import com.ljb.android.component.search.adapter.SearchListAdapter
import com.ljb.android.component.search.bean.SearchBean
import com.ljb.android.component.search.contract.SearchContract
import com.ljb.android.component.search.databinding.SearchActivityMainBinding
import com.ljb.android.component.search.databinding.SearchLayoutRecyclerViewBinding
import com.ljb.android.component.search.presenter.SearchPresenter

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/30
 * @Description input description
 **/
@Route(path = RouterConfig.Activity.SEARCH_MAIN)
class SearchActivity : CommMvpActivity<SearchContract.IPresenter, SearchActivityMainBinding>(),
    SearchContract.IView {

    private var mPage = 0
    private var mKey = ""
    private var mBindContent: SearchLayoutRecyclerViewBinding? = null

    private val mListAdapter = SearchListAdapter()

    override fun registerPresenter() = SearchPresenter::class.java

    override fun getLayoutId() = R.layout.search_activity_main

    override fun registerBinding(): SearchActivityMainBinding {
        return SearchActivityMainBinding.inflate(layoutInflater, mParentView, false)
    }

    override fun initView() {
        mBind.ivBack.setOnClickListener { onBackPressed() }
        mBind.tvSearch.setOnClickListener { doSearch() }
        mBind.etSearch.addTextChangedListener {
            if (it.isNullOrEmpty()) {
                mBind.ivClear.visibility = View.GONE
            } else {
                mBind.ivClear.visibility = View.VISIBLE
            }
        }
        mBind.etSearch.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEND
                || actionId == EditorInfo.IME_ACTION_SEARCH
                || (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER)
            ) {
                doSearch()
                true
            } else {
                false
            }
        }
        mBind.ivClear.setOnClickListener {
            clearText()
        }

        initPageContentView()
    }

    private fun initPageContentView() {
        val contentView = mBind.pageLayout.getPageView(PageState.STATE_SUCCESS)
        mBindContent = SearchLayoutRecyclerViewBinding
            .bind(contentView).apply {
                rvList.apply {
                    layoutManager = LinearLayoutManager(this@SearchActivity)
                    adapter = mListAdapter
                    mListAdapter.loadMoreModule.isEnableLoadMore = true
                    mListAdapter.loadMoreModule.isEnableLoadMoreIfNotFullPage = false
                    mListAdapter.loadMoreModule.setOnLoadMoreListener { onLoadMore() }
                    mListAdapter.setOnItemClickListener { _, _, position ->
                        val url = mListAdapter.data[position].link
                        goWebView(url)
                    }
                    addItemDecoration(
                        RVItemDecorationMargin(
                            bottom = SizeUtils.dp2px(10f),
                            onlyLastItem = true
                        )
                    )
                }
            }
    }

    private fun clearText() {
        mBind.etSearch.setText("")
        mListAdapter.data.clear()
        mListAdapter.notifyDataSetChanged()
    }

    private fun goWebView(url: String) {
        CommWebViewActivity.startActivity(this, url)
    }

    private fun onLoadMore() {
        getPresenter().doSearch(mPage, mKey)
    }

    private fun doSearch() {
        mPage = 0
        mKey = mBind.etSearch.text.trim().toString()
        getPresenter().doSearch(mPage, mKey)
    }

    override fun onSearchResult(searchBean: SearchBean) {
        if (mPage == 0) {
            mListAdapter.data.clear()
        } else {
            mListAdapter.loadMoreModule.loadMoreComplete()
        }

        if (searchBean.data.datas.isEmpty()) {
            //没有更多数据
            mBind.pageLayout.setPage(PageState.STATE_EMPTY)
            mListAdapter.loadMoreModule.loadMoreEnd(true)
        } else {
            mBind.pageLayout.setPage(PageState.STATE_SUCCESS)
            mListAdapter.data.addAll(searchBean.data.datas)
            mPage++
        }
        mListAdapter.notifyDataSetChanged()
    }

}
