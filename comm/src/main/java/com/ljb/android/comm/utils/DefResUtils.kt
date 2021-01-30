package com.ljb.android.comm.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import com.ljb.android.comm.R

object DefResUtils {

    fun getDefImg(context: Context): Drawable {
        return ResourcesCompat.getDrawable(context.resources, R.mipmap.comm_def_img, null)!!
    }

}