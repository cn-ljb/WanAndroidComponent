package  com.ljb.android.component.project.model

import com.ljb.android.component.project.api.ProjectProtocol
import com.ljb.android.component.project.bean.ProjectListBean
import com.ljb.android.component.project.contract.ProjectListContract
import io.reactivex.rxjava3.core.Observable
import mvp.ljb.kt.model.BaseModel
import net.ljb.kt.HttpFactory

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/26
 * @Description input description
 **/
class ProjectListModel : BaseModel(), ProjectListContract.IModel {

    override fun getProjectList(id: String, page: Int): Observable<ProjectListBean> {
        return HttpFactory.getProtocol(ProjectProtocol::class.java).getProjectList(page, id)
    }
}