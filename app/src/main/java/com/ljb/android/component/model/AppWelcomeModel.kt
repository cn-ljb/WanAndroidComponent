package  com.ljb.android.component.model

import com.ljb.android.component.contract.AppWelcomeContract
import io.reactivex.rxjava3.core.Observable
import mvp.ljb.kt.model.BaseModel
import java.util.concurrent.TimeUnit

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/23
 * @Description input description
 **/
class AppWelcomeModel : BaseModel(), AppWelcomeContract.IModel {

    override fun delayGoHome(time: Long, unit: TimeUnit): Observable<Long> {
        return Observable.timer(time, unit)
    }
}