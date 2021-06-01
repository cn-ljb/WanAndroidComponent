package com.ljb.android.component.search.contract

import com.ljb.android.comm.mvp.ICommView
import com.ljb.android.component.search.bean.SearchBean
import mvp.ljb.kt.contract.IModelContract
import mvp.ljb.kt.contract.IPresenterContract

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/30
 * @Description input description
 **/
interface SearchContract {

    interface IView : ICommView {
        fun onSearchResult(searchBean: SearchBean)
    }

    interface IPresenter : IPresenterContract {
        fun doSearch(page: Int, text: String)
    }

    interface IModel : IModelContract {
        suspend fun doSearch(page: Int, text: String): SearchBean
        suspend fun test1(): String
        suspend fun test2(): String
    }
}
