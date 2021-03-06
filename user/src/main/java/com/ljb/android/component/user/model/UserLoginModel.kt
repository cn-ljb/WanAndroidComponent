package  com.ljb.android.component.user.model

import android.text.TextUtils
import com.blankj.utilcode.util.SPStaticUtils
import com.ljb.android.comm.utils.DefResUtils
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

    override fun saveLoginToSP(loginBean: LoginBean): String {
        val toJson = JsonParser.toJson(loginBean.data)
        SPStaticUtils.put(UserConstant.SPKey.KEY_LOGIN_USER, toJson)
        return toJson
    }

    override fun checkHeaderUrl(loginBean: LoginBean): LoginBean {
        if (TextUtils.isEmpty(loginBean.data.icon)) {
            val headerUrl = DefResUtils.getRandomHeaderUrl()
            loginBean.data.icon = headerUrl
        }
        return loginBean
    }
}