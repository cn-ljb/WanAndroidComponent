package  com.ljb.android.component.user.model

import com.blankj.utilcode.util.SPStaticUtils
import com.ljb.android.component.user.api.UserProtocol
import com.ljb.android.component.user.bean.LoginBean
import com.ljb.android.component.user.common.UserConstant
import com.ljb.android.component.user.contract.UserLoginContract
import io.reactivex.rxjava3.core.Observable
import mvp.ljb.kt.model.BaseModel
import net.ljb.kt.HttpFactory
import net.ljb.kt.utils.JsonParser

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/23
 * @Description input description
 **/
class UserLoginModel : BaseModel(), UserLoginContract.IModel {

    override fun login(userName: String, pwd: String): Observable<LoginBean> {
        return HttpFactory.getProtocol(UserProtocol::class.java)
            .loginUser(userName, pwd)
    }

    override fun saveLoginToSP(loginBean: LoginBean): LoginBean {
        SPStaticUtils.put(UserConstant.SPKey.KEY_LOGIN_USER, JsonParser.toJson(loginBean.data))
        return loginBean
    }
}