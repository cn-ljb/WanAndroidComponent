package  com.ljb.android.component.wechatcode.model

import com.ljb.android.comm.bean.base.HttpBean
import com.ljb.android.component.wechatcode.api.WeChatCodeProtocol
import com.ljb.android.component.wechatcode.bean.WXArticleListBean
import com.ljb.android.component.wechatcode.contract.WXArticleListContract
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
        return HttpFactory.getProtocol(WeChatCodeProtocol::class.java).getArticleList(id, page)
    }

    override fun cancelCollect(id: String): Observable<HttpBean> {
        return HttpFactory.getProtocol(WeChatCodeProtocol::class.java).cancelCollect(id)
    }

    override fun doCollect(id: String): Observable<HttpBean> {
        return HttpFactory.getProtocol(WeChatCodeProtocol::class.java).doCollect(id)
    }
}