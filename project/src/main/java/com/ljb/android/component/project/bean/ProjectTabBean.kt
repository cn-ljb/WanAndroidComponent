package com.ljb.android.component.project.bean

import com.ljb.android.comm.bean.base.HttpBean

class ProjectTabBean(val data: List<ListBean>) : HttpBean() {

    class ListBean(val id: String, val name: String)
}