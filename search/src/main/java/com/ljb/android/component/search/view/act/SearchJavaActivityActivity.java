package com.ljb.android.component.search.view.act;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ljb.android.comm.adapter.decoration.RVItemDecorationMargin;
import com.ljb.android.comm.mvp.CommMvpActivity;
import com.ljb.android.comm.view.act.CommWebViewActivity;
import com.ljb.android.comm.weiget.page.PageState;
import com.ljb.android.component.search.R;
import com.ljb.android.component.search.adapter.SearchListAdapter;
import com.ljb.android.component.search.bean.SearchBean;
import com.ljb.android.component.search.contract.SearchContract;
import com.ljb.android.component.search.databinding.SearchActivityMainBinding;
import com.ljb.android.component.search.databinding.SearchLayoutRecyclerViewBinding;
import com.ljb.android.component.search.presenter.SearchPresenter;

import org.jetbrains.annotations.NotNull;

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/06/02
 * @Description input description
 **/
public class SearchJavaActivityActivity extends CommMvpActivity<SearchContract.IPresenter, SearchActivityMainBinding> implements SearchContract.IView {

    private int mPage = 0;
    private String mKey = "";
    private SearchLayoutRecyclerViewBinding mBindContent;

    private SearchListAdapter mListAdapter = new SearchListAdapter();

    @Override
    protected int getLayoutId() {
        return R.layout.search_activity_main;
    }

    @NotNull
    @Override
    public Class<? extends SearchContract.IPresenter> registerPresenter() {
        return SearchPresenter.class;
    }

    @NotNull
    @Override
    public SearchActivityMainBinding registerBinding() {
        return SearchActivityMainBinding.inflate(getLayoutInflater(), getMParentView(), false);
    }

    @Override
    protected void initView() {
        mBind.ivBack.setOnClickListener(v -> onBackPressed());
        mBind.tvSearch.setOnClickListener(v -> doSearch());
        mBind.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable it) {
                if (TextUtils.isEmpty(it)) {
                    mBind.ivClear.setVisibility(View.GONE);
                } else {
                    mBind.ivClear.setVisibility(View.VISIBLE);
                }
            }
        });
        mBind.etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_SEARCH
                        || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)
                ) {
                    doSearch();
                    return true;
                } else {
                    return false;
                }
            }
        });
        mBind.ivClear.setOnClickListener(v -> clearText());
        initPageContentView();
    }

    private void initPageContentView() {
        View contentView = mBind.pageLayout.getPageView(PageState.STATE_SUCCESS);
        mBindContent = SearchLayoutRecyclerViewBinding.bind(contentView);
        mBindContent.rvList.setLayoutManager(new LinearLayoutManager(this));
        mBindContent.rvList.setAdapter(mListAdapter);
        mListAdapter.getLoadMoreModule().setEnableLoadMore(true);
        mListAdapter.getLoadMoreModule().setEnableLoadMoreIfNotFullPage(false);
        mListAdapter.getLoadMoreModule().setOnLoadMoreListener(() -> SearchJavaActivityActivity.this.onLoadMore());
        mListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                String url = mListAdapter.getData().get(position).getLink();
                goWebView(url);
            }
        });

        mBindContent.rvList.addItemDecoration(
                new RVItemDecorationMargin(
                        0,
                        SizeUtils.dp2px(10f),
                        0,
                        0,
                        false,
                        true
                )
        );
    }

    private void clearText() {
        mBind.etSearch.setText("");
        mListAdapter.getData().clear();
        mListAdapter.notifyDataSetChanged();
    }

    private void goWebView(String url) {
        CommWebViewActivity.Companion.startActivity(this, url, "");
    }

    private void onLoadMore() {
        getPresenter().doSearch(mPage, mKey);
    }

    private void doSearch() {
        mPage = 0;
        mKey = mBind.etSearch.getText().toString().trim();
        getPresenter().doSearch(mPage, mKey);
    }

    @Override
    public void onSearchResult(SearchBean searchBean) {
        if (mPage == 0) {
            mListAdapter.getData().clear();
        } else {
            mListAdapter.getLoadMoreModule().loadMoreComplete();
        }

        if (searchBean.getData().getDatas().isEmpty()) {
            //没有更多数据
            mBind.pageLayout.setPage(PageState.STATE_EMPTY);
            mListAdapter.getLoadMoreModule().loadMoreEnd(true);
        } else {
            mBind.pageLayout.setPage(PageState.STATE_SUCCESS);
            mListAdapter.getData().addAll(searchBean.getData().getDatas());
            mPage++;
        }
        mListAdapter.notifyDataSetChanged();
    }
}
