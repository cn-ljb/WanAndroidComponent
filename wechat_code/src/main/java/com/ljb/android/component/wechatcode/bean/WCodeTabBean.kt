package com.ljb.android.component.wechatcode.bean

import com.ljb.android.comm.bean.base.HttpBean


class WCodeTabBean(val data: List<ListBean>) : HttpBean() {

    class ListBean(
        val courseId: String,
        val id: String,
        val name: String,
        val order: String,
        val parentChapterId: String,
        val userControlSetTop: Boolean,
        val visible: Int
    )
}