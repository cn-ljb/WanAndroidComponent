package com.ljb.android.component.wechatcode.view.fragment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.gyf.immersionbar.ImmersionBar
import com.ljb.android.comm.mvp.CommMvpFragment
import com.ljb.android.comm.weiget.page.PageState
import com.ljb.android.component.wechatcode.R
import com.ljb.android.component.wechatcode.contract.WXArticleListContract
import com.ljb.android.component.wechatcode.databinding.FragmentWxArticleListBinding
import com.ljb.android.component.wechatcode.databinding.WechatLayoutArticleListContentBinding
import com.ljb.android.component.wechatcode.presenter.WXArticleListPresenter

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/22
 * @Description input description
 **/
class WXArticleListFragment :
    CommMvpFragment<WXArticleListContract.IPresenter, FragmentWxArticleListBinding>(),
    WXArticleListContract.IView, SwipeRefreshLayout.OnRefreshListener {

    companion object {
        const val KEY_ID = "key_id"
        const val KEY_NAME = "key_name"
    }


    private lateinit var mBindContentView: WechatLayoutArticleListContentBinding

    private var mId: String = "0"
    private var mName: String = ""

    override fun registerPresenter() = WXArticleListPresenter::class.java

    override fun getLayoutId() = R.layout.fragment_wx_article_list

    override fun registerBinding(): FragmentWxArticleListBinding {
        return FragmentWxArticleListBinding.inflate(layoutInflater, mParentView, false)
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
        val contentView = mBind.pageLayout.getPageView(PageState.STATE_SUCCESS)
        mBindContentView = WechatLayoutArticleListContentBinding.bind(contentView)
        initSearchView()
        initRecyclerView()
    }

    private fun initSearchView() {
        mBindContentView.btnSearch.setOnClickListener {
            goSearch()
        }
        mBindContentView.etSearch.hint = resources.getString(R.string.wechat_search_hint, mName)
    }

    private fun initRecyclerView() {
        mBindContentView.swRefresh.apply {
            setColorSchemeResources(
                R.color.color_22DD6D,
                R.color.color_55AA66,
                R.color.color_388347
            )
            setOnRefreshListener(this@WXArticleListFragment)
        }

        mBindContentView.rvList.apply {
            layoutManager = LinearLayoutManager(activity)
//            adapter = mListAdapter
//            mListAdapter.loadMoreModule.isEnableLoadMore = true
//            mListAdapter.loadMoreModule.isEnableLoadMoreIfNotFullPage = false
//            mListAdapter.loadMoreModule.setOnLoadMoreListener { getPresenter().getHomeList(mPage) }
//            mListAdapter.mCollectListener = { doCollect(it) }
//            mListAdapter.setOnItemClickListener { _, _, position ->
//                val url = mListAdapter.data[position].link
//                goWebView(url)
//            }
        }
    }

    override fun onRefresh() {

    }

    private fun goSearch() {

    }
}

