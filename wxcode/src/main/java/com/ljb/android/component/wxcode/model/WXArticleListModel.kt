package  com.ljb.android.component.wxcode.model

import com.ljb.android.comm.bean.base.HttpBean
import com.ljb.android.component.wxcode.api.WXCodeProtocol
import com.ljb.android.component.wxcode.bean.WXArticleListBean
import com.ljb.android.component.wxcode.contract.WXArticleListContract
import io.reactivex.rxjava3.core.Observable
import mvp.ljb.kt.model.BaseModel
import net.ljb.kt.HttpFactory

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/22
 * @Description input description
 **/
class WXArticleListModel : BaseModel(), WXArticleListContract.IModel {

    override fun getArticleList(id: String, page: Int): Observable<WXArticleListBean> {
        return HttpFactory.getProtocol(WXCodeProtocol::class.java).getArticleList(id, page)
    }

    override fun cancelCollect(id: String): Observable<HttpBean> {
        return HttpFactory.getProtocol(WXCodeProtocol::class.java).cancelCollect(id)
    }

    override fun doCollect(id: String): Observable<HttpBean> {
        return HttpFactory.getProtocol(WXCodeProtocol::class.java).doCollect(id)
    }
}