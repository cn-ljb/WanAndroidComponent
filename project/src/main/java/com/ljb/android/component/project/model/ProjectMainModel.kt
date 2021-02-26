package  com.ljb.android.component.project.model

import com.ljb.android.component.project.api.ProjectProtocol
import com.ljb.android.component.project.bean.ProjectTabBean
import com.ljb.android.component.project.contract.ProjectMainContract
import io.reactivex.rxjava3.core.Observable
import mvp.ljb.kt.model.BaseModel
import net.ljb.kt.HttpFactory

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/26
 * @Description input description
 **/
class ProjectMainModel : BaseModel(), ProjectMainContract.IModel {

    override fun getTabList(): Observable<ProjectTabBean> {
        return HttpFactory.getProtocol(ProjectProtocol::class.java).getProjectTabList()
    }
}