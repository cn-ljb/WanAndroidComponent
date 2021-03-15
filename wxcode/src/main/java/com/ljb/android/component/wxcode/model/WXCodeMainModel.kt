package  com.ljb.android.component.wxcode.model

import com.ljb.android.component.wxcode.api.WXCodeProtocol
import com.ljb.android.component.wxcode.bean.WXCodeTabBean
import com.ljb.android.component.wxcode.contract.WXCodeMainContract
import io.reactivex.rxjava3.core.Observable
import mvp.ljb.kt.model.BaseModel
import net.ljb.kt.HttpFactory

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/22
 * @Description input description
 **/
class WXCodeMainModel : BaseModel(), WXCodeMainContract.IModel {

    override fun getTabList(): Observable<WXCodeTabBean> {
        return HttpFactory.getProtocol(WXCodeProtocol::class.java).getWeChatTabList()
    }


}