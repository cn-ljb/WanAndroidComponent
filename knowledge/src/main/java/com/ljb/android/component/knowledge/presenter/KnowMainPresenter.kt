package com.ljb.android.component.knowledge.presenter

import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.ljb.android.component.knowledge.contract.KnowMainContract
import com.ljb.android.component.knowledge.model.KnowMainModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/21
 * @Description input description
 **/
class KnowMainPresenter : BaseMvpPresenter<KnowMainContract.IView, KnowMainContract.IModel>(), KnowMainContract.IPresenter{

    override fun registerModel() = KnowMainModel::class.java

}
