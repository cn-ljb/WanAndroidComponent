package com.ljb.android.component.search.testkt

import android.view.View
import com.ljb.android.comm.utils.XLog

/**
 * Author:Ljb
 * Time:2021/6/2
 * There is a lot of misery in life
 **/
fun main(args: Array<String>) {
    test5()
}

fun test0() {
    val num1: Int = 128
    val num2: Int? = 128
    println(num1 == num2)
    println(num1 === num2)
}

fun test1() {
    val result = true
    val num = if (result) 1 else 2
//        val num2  =  result ? 1 : 2
}

/**
 * 匿名函数
 * */
fun test2() {
    var onClickListener: (v: View) -> Unit

    onClickListener = { v ->
        XLog.i("123")
    }

    test3(onClickListener)

    test3 { v ->
        XLog.i("123")
    }
}

/**
 * 高阶函数
 * */
fun test3(listener: (v: View) -> Unit) {

}

/**
 * 伴生对象
 * */
class B {
    companion object {
        @JvmField
        val KEY_B = "b"
        const val KEY_C = "c"

        @JvmField
        var KEY_D = "d"
    }
}

fun test4() {
    val b = B.KEY_B
    val c = B.KEY_C
}


/**
 * 扩展函数、扩展属性
 * */
class Dog(val type: String) {

    fun run() {
        println("${type}：我会跑")
    }

    fun tell() {
        println("${type}：汪汪")
    }
}


fun test5() {
    val dog1 = Dog("金毛")
    dog1.run()
    dog1.tell()
    dog1.tellChinese()

    println("--------------------")

    val dog2 = Dog("二哈")
    dog2.run()
    dog2.tell()
    dog2.tellChinese()
}


val Dog.translator: Boolean
    get() {
        return type == "金毛"
    }

fun Dog.tellChinese(): Unit {
    if (translator) {
        println("${type}：主人，你好！")
    } else {
        tell()
    }
}

/**
 * 命名参数、默认参数
 * */
fun test6(width: Int, height: Int) {

}

fun test6(top: Int, bottom: Int, left: Int, right: Int) {

}

fun test6(
    width: Int = 0,
    height: Int = 0,
    top: Int = 0,
    bottom: Int = 0,
    left: Int = 0,
    right: Int = 0
) {

}

fun test7() {
//        test6(100,100)
//        test6(0,100,100,100)
    test6(width = 100, height = 100)
    test6(top = 0, bottom = 100, left = 100, right = 100)
}



