package  com.ljb.android.component.home.model

import com.blankj.utilcode.util.SPStaticUtils
import com.ljb.android.comm.bean.base.HttpBean
import com.ljb.android.component.home.api.HomeProtocol
import com.ljb.android.component.home.bean.BannerBean
import com.ljb.android.component.home.bean.HomeListBean
import com.ljb.android.component.home.common.HomeConstant
import com.ljb.android.component.home.contract.HomeMainContract
import io.reactivex.rxjava3.core.Observable
import mvp.ljb.kt.model.BaseModel
import net.ljb.kt.HttpFactory
import net.ljb.kt.utils.JsonParser
import org.json.JSONArray
import java.lang.Exception

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/27
 * @Description input description
 **/
class HomeMainModel : BaseModel(), HomeMainContract.IModel {

    override fun getBanner(): Observable<BannerBean> {
        return HttpFactory.getProtocol(HomeProtocol::class.java)
            .getBanner()
    }

    override fun getHomeList(page: Int): Observable<HomeListBean> {
        return HttpFactory.getProtocol(HomeProtocol::class.java)
            .getHomeList(page)
    }

    override fun doCollect(id: String): Observable<HttpBean> {
        return HttpFactory.getProtocol(HomeProtocol::class.java)
            .doCollect(id)
    }

    override fun cancelCollect(id: String): Observable<HttpBean> {
        return HttpFactory.getProtocol(HomeProtocol::class.java)
            .cancelCollect(id)
    }

    override fun saveHomeCache(page: Int, cache: List<Any>): List<Any> {
        if (page == 0) {
            SPStaticUtils.put(HomeConstant.SPKey.KEY_HOME_CACHE, JsonParser.toJson(cache))
        }
        return cache
    }

    override fun getHomeCache(): Observable<MutableList<Any>> {
        return Observable.create {
            try {
                val list = mutableListOf<Any>()
                val cache = SPStaticUtils.getString(HomeConstant.SPKey.KEY_HOME_CACHE, "")
                if (cache.isNullOrEmpty()) {
                    it.onNext(list)
                    it.onComplete()
                } else {
                    val jsonArr = JSONArray(cache)
                    //banner缓存
                    if (jsonArr.length() > 0) {
                        val json1 = jsonArr.getString(0)
                        val bannerBean = JsonParser.fromJsonObj(json1, BannerBean::class.java)
                        list.add(bannerBean)
                    }
                    //列表缓存
                    if (jsonArr.length() > 1) {
                        val json2 = jsonArr.getString(1)
                        val homeBean = JsonParser.fromJsonObj(json2, HomeListBean::class.java)
                        list.add(homeBean)
                    }
                    it.onNext(list)
                    it.onComplete()
                }
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }
}