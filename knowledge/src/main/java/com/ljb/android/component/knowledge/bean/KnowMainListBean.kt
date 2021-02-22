package com.ljb.android.component.knowledge.bean

import com.ljb.android.comm.bean.base.HttpBean


/**
 * Author:Ljb
 * Time:2021/2/21
 * There is a lot of misery in life
 **/
class KnowMainListBean(val data: List<ListBean>) : HttpBean() {


    class ListBean(
        val children: List<ChildrenBean>,
        val courseId: String,
        val id: String,
        val name: String,
        val order: String,
        val parentChapterId: String,
        val userControlSetTop: Boolean,
        val visible: Int
    ) {
        var msg: String? = null
    }

    class ChildrenBean(
        val courseId: String,
        val id: String,
        val name: String,
        val order: String,
        val parentChapterId: String,
        val userControlSetTop: Boolean,
        val visible: Int
    )
}
