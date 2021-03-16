package com.ljb.android.component.chat.socket.notify

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.ljb.android.component.chat.MainActivity
import com.ljb.android.component.chat.socket.ChatMessage

/**
 * Author:Ljb
 * Time:2018/11/15
 * There is a lot of misery in life
 **/
class SocketNotificationClickReceiver : BroadcastReceiver() {

    companion object {
        const val KEY_CHAT_MESSAGE = "chat"
    }

    override fun onReceive(context: Context, intent: Intent) {
        val chatMessage = intent.getParcelableExtra(KEY_CHAT_MESSAGE) as? ChatMessage ?: return
//        val startIntent = Intent(context, MainActivity::class.java)
//        startIntent.putExtra(KEY_CHAT_MESSAGE, chatMessage)
//        startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        context.startActivity(startIntent)
    }

}
