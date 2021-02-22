package com.ljb.android.component.wechatcode.presenter

import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.ljb.android.component.wechatcode.contract.WXArticleListContract
import com.ljb.android.component.wechatcode.model.WXArticleListModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/22
 * @Description input description
 **/
class WXArticleListPresenter : BaseMvpPresenter<WXArticleListContract.IView, WXArticleListContract.IModel>(), WXArticleListContract.IPresenter{

    override fun registerModel() = WXArticleListModel::class.java

}
