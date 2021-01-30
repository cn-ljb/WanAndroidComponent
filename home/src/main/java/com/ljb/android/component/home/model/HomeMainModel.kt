package  com.ljb.android.component.home.model

import com.ljb.android.component.home.api.HomeProtocol
import com.ljb.android.component.home.bean.BannerBean
import com.ljb.android.component.home.bean.HomeListBean
import com.ljb.android.component.home.bean.base.HttpBean
import com.ljb.android.component.home.contract.HomeMainContract
import io.reactivex.rxjava3.core.Observable
import mvp.ljb.kt.model.BaseModel
import net.ljb.kt.HttpFactory

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
}