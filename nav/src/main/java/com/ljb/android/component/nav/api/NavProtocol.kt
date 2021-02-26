package com.ljb.android.component.nav.api

import com.ljb.android.component.nav.bean.NavBean
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface NavProtocol {

    @GET("navi/json")
    fun getNvaTabList(): Observable<NavBean>
}