package com.ljb.android.comm.fragment

import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.ljb.android.comm.R

class CommNotFindFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return TextView(activity).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
            gravity = Gravity.CENTER
            setTextSize(TypedValue.COMPLEX_UNIT_SP , 16f)
            setBackgroundColor(resources.getColor(R.color.color_white))
            text = "未找到页面"
        }
    }
}