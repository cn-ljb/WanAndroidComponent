package com.ljb.android.component.search.testkt;

import android.view.View;


class TestJava {

    public void test1() {
        boolean result = true;
        int num = result ? 1 : 2;
    }

    // ------------  高阶函数 -------------
    public void test2() {
        test3(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void test3(OnClickListener listener) {

    }

    interface OnClickListener {
        void onClick(View view);
    }

    // ----------- 伴生对象 --------------
    class A {
        public final static String KEY_A = "a";
    }

    public void test4() {
        String a = A.KEY_A;
        String b = B.KEY_B;
        String c = B.KEY_C;
        String d = B.KEY_D;
    }

}
