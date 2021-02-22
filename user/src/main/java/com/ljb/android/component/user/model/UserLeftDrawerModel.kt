package  com.ljb.android.component.user.model

import com.blankj.utilcode.util.SPStaticUtils
import com.ljb.android.comm.bean.base.HttpBean
import com.ljb.android.component.user.api.UserProtocol
import com.ljb.android.component.user.common.UserConstant
import com.ljb.android.component.user.contract.UserLeftDrawerContract
import io.reactivex.rxjava3.core.Observable
import mvp.ljb.kt.model.BaseModel
import net.ljb.kt.HttpFactory

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/25
 * @Description input description
 **/
class UserLeftDrawerModel : BaseModel(), UserLeftDrawerContract.IModel {

    override fun logout(): Observable<HttpBean> {
        return HttpFactory.getProtocol(UserProtocol::class.java)
            .logoutUser()
    }

    override fun clearUserToSP(httpBean: HttpBean): HttpBean {
        SPStaticUtils.put(UserConstant.SPKey.KEY_LOGIN_USER, "")
        return httpBean
    }
}