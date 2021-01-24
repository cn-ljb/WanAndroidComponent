package  com.yuelvdaren.www.user.model

import com.yuelvdaren.www.user.api.UserProtocol
import com.yuelvdaren.www.user.bean.LoginBean
import com.yuelvdaren.www.user.contract.UserLoginContract
import io.reactivex.rxjava3.core.Observable
import mvp.ljb.kt.model.BaseModel
import net.ljb.kt.HttpFactory

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/23
 * @Description input description
 **/
class UserLoginModel : BaseModel(), UserLoginContract.IModel {

    override fun login(userName: String, pwd: String): Observable<LoginBean> {
        return HttpFactory.getProtocol(UserProtocol::class.java)
            .getLoginData(userName, pwd)
    }
}