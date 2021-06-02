package  com.ljb.android.component.search.model

import com.ljb.android.comm.utils.XLog
import com.ljb.android.component.search.api.SearchProtocol
import com.ljb.android.component.search.bean.SearchBean
import com.ljb.android.component.search.contract.SearchContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import mvp.ljb.kt.model.BaseModel
import net.ljb.kt.HttpFactory

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/30
 * @Description input description
 **/
class SearchModel : BaseModel(), SearchContract.IModel {

    override suspend fun doSearch(page: Int, key: String): SearchBean {
        return withContext(Dispatchers.IO) {
            XLog.d("do Thread :" + Thread.currentThread().name)
            HttpFactory.getProtocol(SearchProtocol::class.java).doSearch(page, key)
        }
    }

    override suspend fun test1(): String {
        return withContext(Dispatchers.IO) {
            delay(2000)
            "data1"
        }
    }

    override suspend fun test2(): String {
        return withContext(Dispatchers.IO) {
            delay(3000)
            "data2"
        }
    }
}