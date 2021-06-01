package com.ljb.android.component.search.presenter

import android.os.Looper
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.ljb.android.comm.presenter.getLifecycle
import com.ljb.android.comm.presenter.presenterScope
import com.ljb.android.comm.utils.XLog
import com.ljb.android.component.search.contract.SearchContract
import com.ljb.android.component.search.model.SearchModel
import kotlinx.coroutines.*
import mvp.ljb.kt.presenter.BaseMvpPresenter

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/30
 * @Description input description
 **/
class SearchPresenter : BaseMvpPresenter<SearchContract.IView, SearchContract.IModel>(),
    SearchContract.IPresenter {

    override fun registerModel() = SearchModel::class.java

    override fun doSearch(page: Int, text: String) {
        test3()
//        test2()
//        test()
//        GlobalScope.launch(Dispatchers.Main) {
//            XLog.d("is Main Thread:" + "${Thread.currentThread() == Looper.getMainLooper().thread}")
//            try {
//                if (page == 0) {
//                    getMvpView().showLoading()
//                }
//
//                XLog.d("Start Thread :" + Thread.currentThread().name)
//                val searchBean = getModel().doSearch(page, text)
//                XLog.d("End Thread :" + Thread.currentThread().name)
//
//                getMvpView().onSearchResult(searchBean)
//            } catch (e: Exception) {
//                XLog.e(e)
//            } finally {
//                if (page == 0) {
//                    getMvpView().hideLoading()
//                }
//            }
//        }

    }

    //    ---------- 同步请求演示代码 ------------
    private var mJob: Job? = null
    private fun test() {
        mJob?.cancel()
        mJob = GlobalScope.launch(Dispatchers.Main) {

            val startTime = System.currentTimeMillis()
            val list = withContext(Dispatchers.IO) {
                val test1 = async { getModel().test1() }
                val test2 = async { getModel().test2() }
                val data1 = test1.await()
                val data2 = test2.await()
                listOf(data1, data2)
            }
            val endTime = System.currentTimeMillis()
            XLog.d("耗时：${endTime - startTime}ms")

            XLog.d("数据1：${list[0]} 数据2：${list[1]}")
            getMvpView().showToast("数据1：${list[0]} 数据2：${list[1]}")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mJob?.cancel()
    }

    //    ---------- life ------------
    private fun test2() {
        if (getContext() is LifecycleOwner) {
            val life = getContext() as LifecycleOwner
            life.lifecycleScope.launch {
                XLog.d("is Main Thread:" + "${Thread.currentThread() == Looper.getMainLooper().thread}")

                XLog.d("Start Thread :" + Thread.currentThread().name)
                val data2 = getModel().test2()
                XLog.d("End Thread :" + Thread.currentThread().name)

                XLog.d("数据2：${data2}")
                getMvpView().showToast("数据2：${data2}")
            }
        }
    }

    //    ---------- lifeEx ------------
    private fun test3() {
//        getLifecycle().lifecycleScope.launch {
//
//            val data2 = getModel().test2()
//            XLog.d("数据2：${data2}")
//            getMvpView().showToast("数据2：${data2}")
//        }

        val launch = presenterScope.launch {
            XLog.d("is Main Thread:" + "${Thread.currentThread() == Looper.getMainLooper().thread}")

            XLog.d("Start Thread :" + Thread.currentThread().name)
            val data2 = getModel().test2()
            XLog.d("End Thread :" + Thread.currentThread().name)

            XLog.d("数据2：${data2}")
            getMvpView().showToast("数据2：${data2}")
        }

    }

}
