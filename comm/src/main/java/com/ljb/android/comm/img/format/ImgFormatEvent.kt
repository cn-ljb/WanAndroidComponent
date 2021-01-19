package com.ljb.android.comm.img.format

enum class ImgFormatEvent(var radius: Int) {

    Default(0),
    Circle(0),
    Round(10),
    RoundLeft(10),
    RoundRight(10),
    RoundTopLeft(10),
    RoundTopRight(10),
    RoundBottomLeft(10),
    RoundBottomRight(10);

    /**
     * 圆角大小，单位：像素
     * */
    fun radius(radius: Int): ImgFormatEvent {
        this.radius = radius
        return this
    }

}