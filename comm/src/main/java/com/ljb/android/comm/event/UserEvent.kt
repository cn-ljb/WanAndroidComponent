package com.ljb.android.comm.event

class UserEvent(val type: Int) {

    companion object {
        const val TYPE_LOGIN = 1
        const val TYPE_LOGOUT = 2
    }
}