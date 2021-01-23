package  com.yuelvdaren.www.user.model

import com.yuelvdaren.www.user.api.UserProtocol
import com.yuelvdaren.www.user.bean.LoginBean
import com.yuelvdaren.www.user.contract.UserRegisterContract
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
            .getRegisterData(userName, pwd, confirmPwd)
    }
}