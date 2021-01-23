package com.yuelvdaren.www.user.presenter

import com.ljb.android.comm.rx.subscribeNet
import com.yuelvdaren.www.user.R
import com.yuelvdaren.www.user.contract.UserRegisterContract
import com.yuelvdaren.www.user.model.UserRegisterModel
import mvp.ljb.kt.presenter.BaseMvpPresenter
import net.ljb.kt.utils.RxUtils

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/23
 * @Description input description
 **/
class UserRegisterPresenter :
    BaseMvpPresenter<UserRegisterContract.IView, UserRegisterContract.IModel>(),
    UserRegisterContract.IPresenter {

    override fun registerModel() = UserRegisterModel::class.java

    override fun registerUser(userName: String, pwd: String, confirmPwd: String) {
        if (userName.isEmpty() || pwd.isEmpty() || confirmPwd.isEmpty()) {
            getMvpView().showToast(R.string.user_input_has_null)
            return
        }
        if (pwd != confirmPwd) {
            getMvpView().showToast(R.string.user_confirm_pwd_error)
            return
        }
        getModel().registerUser(userName, pwd, confirmPwd)
            .compose(RxUtils.schedulerIO2Main())
            .compose(RxUtils.bindToLife(getMvpView()))
            .subscribeNet(getContext()) {

                onSubscribeEx {
                    getMvpView().showLoading()
                }

                onNextEx {
                    if ("0" == it.errorCode) {
                        getMvpView().onRegisterUserSuccess(it)
                    } else {
                        getMvpView().showToast(it.errorMsg)
                    }
                }

                onCompleteEx {
                    getMvpView().hideLoading()
                }

                onErrorEx {
                    getMvpView().showToast(R.string.net_error)
                    getMvpView().hideLoading()
                }
            }
    }

}
