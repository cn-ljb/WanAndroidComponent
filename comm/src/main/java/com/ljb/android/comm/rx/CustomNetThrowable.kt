package com.ljb.android.comm.rx

class CustomNetThrowable(val code: String, val errMsg: String) : Throwable(errMsg) {
}