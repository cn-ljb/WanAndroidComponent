package mvp.ljb.kt.act

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.view.MvpAppCompatActivity

/**
 * Author:Ljb
 * Time:2019/7/4
 * There is a lot of misery in life
 **/
abstract class BaseMvpAppCompatActivity<out P : IPresenterContract> : MvpAppCompatActivity<P>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContentView()
        init(savedInstanceState)
        initView()
        initData()
    }

    protected open fun initContentView() {
        setContentView(getLayoutId())
    }


    protected abstract fun getLayoutId(): Int

    protected open fun init(savedInstanceState: Bundle?) {}

    protected open fun initView() {}

    protected open fun initData() {}

    override fun showToast(resId: Int) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()
    }

    override fun showToast(text: String?) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    protected open fun goActivity(cls: Class<*>, bundle: Bundle?) {
        val intent = Intent(this, cls)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    protected open fun goActivity(cls: Class<*>) {
        goActivity(cls, null)
    }

}