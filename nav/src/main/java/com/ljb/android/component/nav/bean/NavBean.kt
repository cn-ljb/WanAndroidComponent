package com.ljb.android.component.nav.bean

import com.ljb.android.comm.bean.base.HttpBean

class NavBean(val data: List<ListBean>) : HttpBean() {


    class ListBean(
        val articles: MutableList<ArticlesBean>,
        val cid: String,
        val name: String,
        var checked: Boolean = false
    )

    class ArticlesBean(var id: String, var title: String, var link: String) {

        //文章时间
        var niceDate: String = ""
        var niceShareDate: String = ""
        var shareDate: Long = 0L
        var publishTime: Long = 0L

        //文章作者
        var shareUser: String = ""
        var author: String = ""

        //分类
        var chapterId: String = ""
        var superChapterId: String = ""
        var chapterName: String = ""
        var superChapterName: String = ""

        //收藏
        var collect: Boolean = false
    }

}