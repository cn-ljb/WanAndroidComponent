package  com.ljb.android.component.home.model

import com.ljb.android.component.home.bean.BannerBean
import com.ljb.android.component.home.contract.HomeMainContract
import io.reactivex.rxjava3.core.Observable
import mvp.ljb.kt.model.BaseModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/27
 * @Description input description
 **/
class HomeMainModel : BaseModel(), HomeMainContract.IModel {

    override fun getBanner(): Observable<BannerBean> {
        TODO("Not yet implemented")
    }
}