package com.ljb.android.component.search.presenter

import android.os.Looper
import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.ljb.android.comm.presenter.presenterScope
import com.ljb.android.comm.rx.subscribeNet
import com.ljb.android.comm.utils.XLog
import com.ljb.android.component.search.api.SearchProtocol
import com.ljb.android.component.search.contract.SearchContract
import com.ljb.android.component.search.model.SearchModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.*
import mvp.ljb.kt.presenter.BaseMvpPresenter
import net.ljb.kt.HttpFactory
import net.ljb.kt.client.HttpClient
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/30
 * @Description input description
 **/
class SearchPresenter() : BaseMvpPresenter<SearchContract.IView, SearchContract.IModel>(),
    SearchContract.IPresenter {

    override fun registerModel() = SearchModel::class.java

    override fun doSearch(page: Int, text: String) {
        presenterScope.launch {
            try {
                if (page == 0) {
                    getMvpView().showLoading()
                }
                val searchBean = getModel().doSearch(page, text)
                getMvpView().onSearchResult(searchBean)
            } catch (e: Exception) {
                XLog.e(e)
            } finally {
                if (page == 0) {
                    getMvpView().hideLoading()
                }
            }
        }
    }

    private fun <T> launch(next: () -> T, onNext: ((T) -> Unit)?=null, onError: ((Throwable) -> Unit)?=null) {
        presenterScope.launch {
            try {
                val result = next.invoke()
                onNext?.invoke(result)
            } catch (e: Exception) {
                onError?.invoke(e)
            }
        }
    }

    fun doSearchAll(page: Int, text: String) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                if (page == 0) {
                    getMvpView().showLoading()
                }

                val searchBean = getModel().doSearch(page, text)
//                val searchBean2 = getModel().doSearch(page, searchBean.toString())
//                val searchBean3 = getModel().doSearch(page, searchBean2.toString())

                getMvpView().onSearchResult(searchBean)
            } catch (e: Exception) {
                XLog.e(e)
            } finally {
                if (page == 0) {
                    getMvpView().hideLoading()
                }
            }
        }
    }

    fun doSearchCallBack(page: Int, text: String) {
        val request: Request = Request.Builder().url("http://www.baidu.com").get().build()
        val httpClient = HttpClient.getHttpClient()
        httpClient.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                httpClient.newCall(request).enqueue(object : Callback {

                    override fun onFailure(call: Call, e: IOException) {

                    }

                    override fun onResponse(call: Call, response: Response) {
                        httpClient.newCall(request).enqueue(object : Callback {

                            override fun onFailure(call: Call, e: IOException) {

                            }

                            override fun onResponse(call: Call, response: Response) {

                            }
                        })
                    }
                })
            }
        })
    }

    fun doSearchRX(page: Int, text: String) {
        HttpFactory.getProtocol(SearchProtocol::class.java)
            .doSearchRx(0, "kotlin")
            .flatMap {
                HttpFactory.getProtocol(SearchProtocol::class.java)
                    .doSearchRx(0, "kotlin")
            }
            .flatMap {
                HttpFactory.getProtocol(SearchProtocol::class.java)
                    .doSearchRx(0, "kotlin")
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeNet(getMvpView()) {
                onNextEx {

                }

                onErrorEx {

                }
            }

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

//        val launch = presenterScope.launch {
//            XLog.d("is Main Thread:" + "${Thread.currentThread() == Looper.getMainLooper().thread}")
//
//            XLog.d("Start Thread :" + Thread.currentThread().name)
//            val data2 = getModel().test2()
//            XLog.d("End Thread :" + Thread.currentThread().name)
//
//            XLog.d("数据2：${data2}")
//            getMvpView().showToast("数据2：${data2}")
//        }

    }

}
