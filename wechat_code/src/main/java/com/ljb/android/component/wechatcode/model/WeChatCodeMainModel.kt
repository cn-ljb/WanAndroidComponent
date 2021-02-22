package  com.ljb.android.component.wechatcode.model

import com.ljb.android.component.wechatcode.api.WeChatCodeProtocol
import com.ljb.android.component.wechatcode.bean.WCodeTabBean
import com.ljb.android.component.wechatcode.contract.WeChatCodeMainContract
import io.reactivex.rxjava3.core.Observable
import mvp.ljb.kt.model.BaseModel
import net.ljb.kt.HttpFactory

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/22
 * @Description input description
 **/
class WeChatCodeMainModel : BaseModel(), WeChatCodeMainContract.IModel {

    override fun getTabList(): Observable<WCodeTabBean> {
        return HttpFactory.getProtocol(WeChatCodeProtocol::class.java).getWeChatTabList()
    }


}