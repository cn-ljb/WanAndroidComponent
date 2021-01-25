package  com.ljb.android.component.user.model

import com.ljb.android.component.user.api.UserProtocol
import com.ljb.android.component.user.bean.LoginBean
import com.ljb.android.component.user.contract.UserRegisterContract
import io.reactivex.rxjava3.core.Observable
import mvp.ljb.kt.model.BaseModel
import net.ljb.kt.HttpFactory

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/23
 * @Description input description
 **/
class UserRegisterModel : BaseModel(), UserRegisterContract.IModel {

    override fun registerUser(
        userName: String,
        pwd: String,
        confirmPwd: String
    ): Observable<LoginBean> {
        return HttpFactory.getProtocol(UserProtocol::class.java)
            .registerUser(userName, pwd, confirmPwd)
    }
}