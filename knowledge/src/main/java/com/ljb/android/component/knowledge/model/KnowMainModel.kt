package  com.ljb.android.component.knowledge.model

import com.ljb.android.component.knowledge.api.KnowProtocol
import com.ljb.android.component.knowledge.bean.KnowMainListBean
import com.ljb.android.component.knowledge.contract.KnowMainContract
import io.reactivex.rxjava3.core.Observable
import mvp.ljb.kt.model.BaseModel
import net.ljb.kt.HttpFactory

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/21
 * @Description input description
 **/
class KnowMainModel : BaseModel(), KnowMainContract.IModel {

    override fun getKnowMainList(): Observable<KnowMainListBean> {
        return HttpFactory.getProtocol(KnowProtocol::class.java).getKnowMainList()
    }

    override fun countChildrenName(bean: KnowMainListBean): KnowMainListBean {
        bean.data.map { child ->
            child.children.map {
                if (child.msg.isNullOrEmpty()) {
                    child.msg = it.name
                } else {
                    child.msg += "  " + it.name
                }
            }
        }
        return bean
    }
}