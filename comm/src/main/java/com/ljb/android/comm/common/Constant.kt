package com.ljb.android.comm.common

import com.ljb.android.comm.BuildConfig


/**
 * 常量池
 * Created by L on 2017/7/11.
 */
object Constant {

    const val APP_ID_BUGLY = "733873bc89"


    /**
     *  SharedPreferences常量池
     * */
    object SPKey {
        const val SP_NAME = "sp.${BuildConfig.APP_ID}"
    }

    /**
     * 数据库常量池
     * */
    object DB {
        const val NAME = "db_default"
        const val VERSION = 1
    }

    /**
     * 权限Code
     * */
    object PermissionCode {
        const val CODE_INIT = 0x00
    }

}