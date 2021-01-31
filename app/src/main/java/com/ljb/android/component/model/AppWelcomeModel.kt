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

    private val mMsgList = mutableListOf(
        "玩",
        "安",
        "卓",
        "\n",
        "to",
        "\n",
        "组",
        "件",
        "化"
    )

    override fun delayGoHome(time: Long, unit: TimeUnit): Observable<Long> {
        return Observable.timer(time, unit)
    }

    override fun timer(time: Long, unit: TimeUnit): Observable<Long> {
        return Observable.interval(time, time, unit).take((mMsgList.size + 1).toLong())
    }

    override fun getText(): String {
        if (mMsgList.isNotEmpty()) {
            return mMsgList.removeAt(0)
        }
        return ""
    }

}