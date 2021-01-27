package com.ljb.android.component.home.bean

class BannerBean(var data: List<DataBean>) : HttpBean() {

    class DataBean(
        var desc: String, var id: String, var imagePath: String, var isVisible: String,
        var order: String, var title: String, var type: String, var url: String
    )
}