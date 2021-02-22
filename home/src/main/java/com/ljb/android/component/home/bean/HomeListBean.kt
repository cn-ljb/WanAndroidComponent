package com.ljb.android.component.home.bean

import com.ljb.android.comm.bean.base.HttpBean


/**
 * Author:Ljb
 * Time:2021/1/30
 * There is a lot of misery in life
 **/
class HomeListBean(var data: DataBean) : HttpBean() {

    class DataBean(var curPage: Int, var pageCount: Int, var datas: List<ListBean>)

    class ListBean(var id: String, var title: String, var link: String) {

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
