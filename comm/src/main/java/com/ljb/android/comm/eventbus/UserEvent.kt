package com.ljb.android.comm.eventbus

class UserEvent(val type: EventType) {

    enum class EventType {
        /**
         * 用户登录事件
         * */
        TYPE_LOGIN,

        /**
         * 用户登出事件
         * */
        TYPE_LOGOUT
    }

}