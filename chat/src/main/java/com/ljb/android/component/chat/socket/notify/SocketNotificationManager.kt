package com.ljb.android.component.chat.socket.notify

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.ljb.android.component.chat.BuildConfig
import com.ljb.android.component.chat.R
import com.ljb.android.component.chat.socket.ChatMessage
import io.reactivex.rxjava3.disposables.Disposable
import net.ljb.kt.utils.RxUtils
import java.lang.ref.WeakReference
import java.util.*

/**
 * Author:Ljb
 * Time:2018/11/15
 * There is a lot of misery in life
 **/
object SocketNotificationManager {


    @JvmStatic
    private var isShowIm = true

    /**
     * 普通聊天 通知栏开关
     * */
    @JvmStatic
    fun setShowImNotification(show: Boolean) {
        isShowIm = show
    }

    private val mRxLife by lazy { ArrayList<WeakReference<Disposable>>() }

    fun showNotificationMessage(context: Context, chatMessage: ChatMessage) {
//        val switch = SPUtils.getBoolean(Constant.SPKey.SWITCH_NOTIFICATION, true)
//        //消息开关
//        if (switch && isShowIm) {
//            val data = NotificationData()
//            val intent = Intent(context, SocketNotificationClickReceiver::class.java)
//            intent.putExtra(SocketNotificationClickReceiver.KEY_CHAT_MESSAGE, chatMessage)
//            data.pIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
//            data.notifId = chatMessage.conversation.hashCode()
//            val subscribe = Observable.just(data)
//                    .map { transformTitleAndImgUrl(it, chatMessage.fromId, context) }
//                    .map { transformContent(it, chatMessage) }
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribeEx(onNext = {
//                        showNotification(context, it)
//                    })
//            mRxLife.add(WeakReference(subscribe))
//        }
    }

    private fun transformContent(
        data: NotificationData,
        chatMessage: ChatMessage
    ): NotificationData {
//        val newNum = DaoFactory.getProtocol(INewNumDaoProtocol::class.java)
//                .queryNewNumImpl(ImNewNumTable(), chatMessage.conversation)
//        val body = ChatUtils.setBodyStr(chatMessage)
//        if (newNum > 1) {
//            data.content = "[${newNum}条] $body"
//        } else {
//            data.content = body
//        }
        return data
    }

    private fun transformTitleAndImgUrl(
        data: NotificationData,
        fromId: String,
        context: Context
    ): NotificationData {
//        val user = DaoFactory.getProtocol(IContactProtocol::class.java).queryContactByIdImpl(ContactTable(), fromId)
//        if (user == null) {
//            data.bitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.icon_app)
//            data.title = "新消息提醒"
//        } else {
//            data.title = user.name
//            data.bitmap = Glide.with(context).asBitmap().load(user.headUrl).apply(ImageLoader.getCircleRequest()).submit(300, 300).get()
//        }
        return data
    }

    private fun showNotification(context: Context, data: NotificationData) {
        val notifBuilder = Notification.Builder(context)
            .setContentTitle(data.title)
            .setContentText(data.content)
            .setSmallIcon(R.mipmap.comm_icon_app)
            .setLargeIcon(data.bitmap)
            .setContentIntent(data.pIntent)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notifBuilder.setChannelId(BuildConfig.APP_ID)
        } else {
            notifBuilder.setPriority(Notification.PRIORITY_HIGH)
            notifBuilder.setDefaults(Notification.DEFAULT_SOUND or Notification.DEFAULT_VIBRATE)
            notifBuilder.setVibrate(longArrayOf(100, 200, 300))
        }

        val notification = notifBuilder.build()
        notification.flags = Notification.FLAG_AUTO_CANCEL
        val notifManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notifManager.notify(data.notifId, notification)
    }


    fun cancelNotification(context: Context, notifId: Int) {
        val notifManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notifManager.cancel(notifId)
    }

    fun releaseAll() {
        mRxLife.map { RxUtils.dispose(it.get()) }
        mRxLife.clear()
    }
}