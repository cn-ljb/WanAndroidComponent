package com.ljb.android.component.wxcode.view.fragment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.blankj.utilcode.util.SizeUtils
import com.gyf.immersionbar.ImmersionBar
import com.ljb.android.comm.adapter.decoration.RVItemDecorationMargin
import com.ljb.android.comm.common.LocUser
import com.ljb.android.comm.eventbus.UserEvent
import com.ljb.android.comm.mvp.CommMvpFragment
import com.ljb.android.comm.view.act.CommWebViewActivity
import com.ljb.android.comm.weiget.page.PageState
import com.ljb.android.component.wxcode.R
import com.ljb.android.component.wxcode.adapter.WXArticleListAdapter
import com.ljb.android.component.wxcode.bean.WXArticleListBean
import com.ljb.android.component.wxcode.contract.WXArticleListContract
import com.ljb.android.component.wxcode.databinding.FragmentWxArticleListBinding
import com.ljb.android.component.wxcode.databinding.WechatLayoutArticleListContentBinding
import com.ljb.android.component.wxcode.presenter.WXArticleListPresenter
import org.greenrobot.eventbus.Subscribe

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
    private var mPage: Int = 1

    private val mListAdapter = WXArticleListAdapter()

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

    override fun initData() {
        mBindContentView.swRefresh.isRefreshing = true
        onRefresh()
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
            adapter = mListAdapter
            mListAdapter.loadMoreModule.isEnableLoadMore = true
            mListAdapter.loadMoreModule.isEnableLoadMoreIfNotFullPage = false
            mListAdapter.loadMoreModule.setOnLoadMoreListener { onLoadMore() }
            mListAdapter.mCollectListener = { doCollect(it) }
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


    override fun onRefresh() {
        mPage = 1
        getPresenter().getArticleList(mId, mPage)
    }

    private fun onLoadMore() {
        getPresenter().getArticleList(mId, mPage)
    }

    private fun goSearch() {
        //TODO 搜索公众号文章
        showToast(R.string.comm_wait_develop)
    }

    override fun onArticleListSuccess(data: WXArticleListBean) {
        if (mPage == 1) {
            mBindContentView.swRefresh.isRefreshing = false
            mListAdapter.data.clear()
        } else {
            mListAdapter.loadMoreModule.loadMoreComplete()
        }

        if (data.data.datas.isEmpty()) {
            //没有更多数据
            mListAdapter.loadMoreModule.loadMoreEnd(true)
        } else {
            mListAdapter.data.addAll(data.data.datas)
            mListAdapter.notifyDataSetChanged()
            mPage++
        }
    }

    override fun onArticleListError() {
        if (mPage == 1) {
            mBindContentView.swRefresh.isRefreshing = false
        } else {
            mListAdapter.loadMoreModule.loadMoreComplete()
        }
    }

    private fun goWebView(url: String) {
        CommWebViewActivity.startActivity(activity!!, url)
    }

    private fun doCollect(position: Int) {
        if (!LocUser.isLogIn(activity!!, true)) {
            return
        }
        val item = mListAdapter.data[position]
        if (item.collect) {
            getPresenter().cancelCollect(position, item.id)
        } else {
            getPresenter().doCollect(position, item.id)
        }
    }

    override fun onCollectStatus(position: Int, status: Boolean) {
        mListAdapter.data[position].collect = status
        mListAdapter.notifyItemChanged(position)
    }

    fun scrollToTop() {
        mBindContentView.rvList.scrollToPosition(0)
    }

    override fun supportEventBus() = true

    @Subscribe
    fun onUserEvent(event: UserEvent) {
        when (event.type) {
            UserEvent.EventType.TYPE_LOGOUT,
            UserEvent.EventType.TYPE_LOGIN -> {
                onRefresh()
            }
        }
    }
}

