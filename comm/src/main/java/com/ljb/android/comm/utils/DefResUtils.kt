package com.ljb.android.comm.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import com.ljb.android.comm.R
import kotlin.random.Random

object DefResUtils {

    fun getDefImg(context: Context): Drawable {
        return ResourcesCompat.getDrawable(context.resources, R.mipmap.comm_def_img, null)!!
    }

    fun getRandomHeaderUrl(): String {
        val list = listOf(
            "https://scpic.chinaz.net/files/pic/pic9/202101/apic30409.jpg",
            "http://5b0988e595225.cdn.sohucs.com/images/20171212/53d348077fdb41838a8c7a84744611dc.jpeg",
            "https://c-ssl.duitang.com/uploads/item/201412/28/20141228171331_L5T2n.jpeg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2647885825,124974193&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2508669923,267695363&fm=11&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1045740110,3694766658&fm=26&gp=0.jpg",
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3651731953,574363730&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2210849323,355458084&fm=26&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2840706493,3966977333&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2719034487,3779311913&fm=26&gp=0.jpg"
        )
        return list[Random(System.currentTimeMillis()).nextInt(0, list.size)]
    }


}