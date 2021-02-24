package  com.ljb.android.component.nav.model

import com.ljb.android.component.nav.api.NavProtocol
import com.ljb.android.component.nav.bean.NavBean
import com.ljb.android.component.nav.contract.NavMainContract
import io.reactivex.rxjava3.core.Observable
import mvp.ljb.kt.model.BaseModel
import net.ljb.kt.HttpFactory

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/24
 * @Description input description
 **/
class NavMainModel : BaseModel(), NavMainContract.IModel {

    override fun getNavData(): Observable<NavBean> {
        return HttpFactory.getProtocol(NavProtocol::class.java).getNvaTabList()
    }
}