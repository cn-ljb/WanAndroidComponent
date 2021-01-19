package com.ljb.android.comm.weiget.page

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.ljb.android.comm.R


/**
 * 页面切换管理的Layout
 * Created by Ljb on 2016/1/8.
 */
class PageStateLayout : FrameLayout {


    private var mCurPageState = PageState.STATE_SUCCESS
    private var mLoadingResId: Int = -1
    private var mErrorResId: Int = -1
    private var mEmptyResId: Int = -1
    private var mContentResId: Int = -1
    private var mLoadingView: View? = null
    private var mErrorView: View? = null
    private var mEmptyView: View? = null
    private var mContentView: View? = null


    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initAttr(context.obtainStyledAttributes(attrs, R.styleable.PageStateLayout))
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initAttr(context.obtainStyledAttributes(attrs, R.styleable.PageStateLayout))
        init()
    }

    private fun initAttr(typedArray: TypedArray) {
        mLoadingResId = typedArray.getResourceId(R.styleable.PageStateLayout_page_load, -1)
        mEmptyResId = typedArray.getResourceId(R.styleable.PageStateLayout_page_empty, -1)
        mErrorResId = typedArray.getResourceId(R.styleable.PageStateLayout_page_error, -1)
        mContentResId = typedArray.getResourceId(R.styleable.PageStateLayout_page_content, -1)
    }

    private fun init() {

        initLoadingView()
        initEmptyView()
        initErrorView()
        initContentView()

        //setPage(PageState.STATE_SUCCESS)
    }

    /**
     * 初始化内容页面
     * */
    private fun initContentView() {
        if (mContentView == null) {
            if (mContentResId != -1) {
                mContentView = LayoutInflater.from(context).inflate(mContentResId, this, false)
            } else {
                val defContentView = getDefTextView()
                defContentView.text = context.resources.getString(R.string.page_content_msg)
                mContentView = defContentView
            }
        }
        mContentView!!.visibility =
            if (mCurPageState == PageState.STATE_SUCCESS) View.VISIBLE else View.GONE
        //addView(mContentView)
    }


    /**
     * 初始化错误界面
     */
    private fun initErrorView() {
        if (mErrorView == null) {
            if (mErrorResId != -1) {
                mErrorView = LayoutInflater.from(context).inflate(mErrorResId, this, false)
            } else {
                val defErrorView = getDefTextView()
//                defErrorView.id = R.id.page_error_reload
                defErrorView.text = context.resources.getString(R.string.page_error_msg)
                mErrorView = defErrorView
            }
        }
//        mErrorView!!.findViewById<View>(R.id.page_error_reload)?.setOnClickListener { onPageErrorClick() }
        mErrorView!!.visibility =
            if (mCurPageState == PageState.STATE_ERROR) View.VISIBLE else View.GONE
        //   addView(mErrorView)
    }

    /**
     * 初始化空界面
     */
    private fun initEmptyView() {
        if (mEmptyView == null) {
            if (mEmptyResId != -1) {
                mEmptyView = LayoutInflater.from(context).inflate(mEmptyResId, this, false)
            } else {
                val defEmptyView = getDefTextView()
                defEmptyView.text = context.resources.getString(R.string.page_empty_msg)
                mEmptyView = defEmptyView
            }
        }
        mEmptyView!!.visibility =
            if (mCurPageState == PageState.STATE_EMPTY) View.VISIBLE else View.GONE
        //    addView(mEmptyView)
    }

    /**
     * 初始化加载中界面
     */
    private fun initLoadingView() {
        if (mLoadingView == null) {
            if (mLoadingResId != -1) {
                mLoadingView = LayoutInflater.from(context).inflate(mLoadingResId, this, false)
            } else {
                val defLoadingView = ProgressBar(context)
                val widthAndHeight = dip2px(context, 40f)
                val layoutParams = FrameLayout.LayoutParams(widthAndHeight, widthAndHeight)
                layoutParams.gravity = Gravity.CENTER
                defLoadingView.layoutParams = layoutParams
                mLoadingView = defLoadingView
            }
        }

        mLoadingView!!.visibility =
            if (mCurPageState == PageState.STATE_LOADING) View.VISIBLE else View.GONE
        //   addView(mLoadingView)
    }


    /**
     * 根据状态显示界面
     */
    private fun updatePage(pageState: PageState) {

        changePageStatus(pageState, mContentView, PageState.STATE_SUCCESS)
        changePageStatus(pageState, mErrorView, PageState.STATE_ERROR)
        changePageStatus(pageState, mEmptyView, PageState.STATE_EMPTY)
        changePageStatus(pageState, mLoadingView, PageState.STATE_LOADING)

        //  mContentView?.visibility = if (pageState == PageState.STATE_SUCCESS) View.VISIBLE else View.INVISIBLE
//        mErrorView?.visibility = if (pageState == PageState.STATE_ERROR) View.VISIBLE else View.GONE
//        mEmptyView?.visibility = if (pageState == PageState.STATE_EMPTY) View.VISIBLE else View.GONE
//        mLoadingView?.visibility = if (pageState == PageState.STATE_LOADING) View.VISIBLE else View.GONE

        mCurPageState = pageState

    }

    /**
     * 检测要显示的页面状态，并添加View到页面上
     * */
    private fun changePageStatus(
        targetPageState: PageState,
        view: View?,
        viewPageState: PageState
    ) {
        view?.apply {
            visibility = if (targetPageState == viewPageState) {
                if (parent == null) {
                    addView(view)
                }
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    fun setPage(pageState: PageState) {
        updatePage(pageState)
    }

    fun getPageState() = mCurPageState

    fun setContentView(resId: Int) {
        checkPreViewInParent(mContentView)
        mContentView = null
        mContentResId = resId
        initContentView()
        updatePage(mCurPageState)
    }


    fun setContentView(view: View) {
        checkPreViewInParent(mContentView)
        mContentResId = -1
        mContentView = view
        initContentView()
        updatePage(mCurPageState)
    }

    fun setErrorView(resId: Int) {
        checkPreViewInParent(mErrorView)
        mErrorView = null
        mErrorResId = resId
        initErrorView()
        updatePage(mCurPageState)
    }

    fun setErrorView(view: View) {
        checkPreViewInParent(mErrorView)
        mErrorResId = -1
        mErrorView = view
        initErrorView()
        updatePage(mCurPageState)
    }

    fun setLoadView(resId: Int) {
        checkPreViewInParent(mLoadingView)
        mLoadingView = null
        mLoadingResId = resId
        initLoadingView()
        updatePage(mCurPageState)
    }

    fun setLoadView(view: View) {
        checkPreViewInParent(mLoadingView)
        mLoadingResId = -1
        mLoadingView = view
        initLoadingView()
        updatePage(mCurPageState)
    }

    fun setEmptyView(resId: Int) {
        checkPreViewInParent(mEmptyView)
        mEmptyView = null
        mEmptyResId = resId
        initEmptyView()
        updatePage(mCurPageState)
    }

    fun setEmptyView(view: View) {
        checkPreViewInParent(mEmptyView)
        mEmptyResId = -1
        mEmptyView = view
        initEmptyView()
        updatePage(mCurPageState)
    }


    fun getPageView(page: PageState): View? {
        return when (page) {
            PageState.STATE_SUCCESS -> mContentView
            PageState.STATE_LOADING -> mLoadingView
            PageState.STATE_ERROR -> mErrorView
            PageState.STATE_EMPTY -> mEmptyView
        }
    }


    private fun checkPreViewInParent(preView: View?) {
        if (preView != null && preView.parent != null) {
            removeView(preView)
        }
    }

    private fun getDefTextView(): TextView {
        val tv = TextView(context)
        tv.layoutParams =
            FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
        tv.setTextColor(Color.parseColor("#666666"))
        tv.gravity = Gravity.CENTER
        return tv
    }


    private fun dip2px(context: Context, dip: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dip * scale + 0.5f).toInt()
    }

}
