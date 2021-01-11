package com.example.appmusicc.Service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.eftimoff.viewpager.transformators.BuildConfig
import com.example.appmusicc.Activity.MainActivity
import com.example.appmusicc.Activity.PlayMusicActivity
import com.example.appmusicc.R
import kotlin.random.Random

class PlayMusicService : Service() {
    val musicPlayer by lazy { PlayMusicActivity() }
    override fun onBind(intent: Intent?): IBinder = LocalBinder()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action == "STOP_FOREGROUND_REMOVE") {
            stopForeground(true)
        } else {
            startForegroundService()
            musicPlayer.run { playMusicService((intent?.extras?.get("url").toString())) }
            // musicPlayer.playMusicc(url = intent?.extras?.get("url") as String)
        }
        return START_REDELIVER_INTENT
    }

    private fun startForegroundService() {
        createNotificationChannel()
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
                this,
                REQUEST_CODE_PENDING_INTENT,
                notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        )
        val notification = NotificationCompat.Builder(this, CHANNEL_ID_NOTIFICATION)
                .setContentTitle(applicationContext.getString(R.string.app_name))
                .setContentText(applicationContext.getString(R.string.app_name))
                .setSmallIcon(R.drawable.ic_baseline_music_note_24)
                .setContentIntent(pendingIntent)
                .build()
        startForeground(ID_START_FOREGROUND_SERVICE, notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                    CHANNEL_ID_NOTIFICATION,
                    CHANNEL_NAME_NOTIFICATION,
                    NotificationManager.IMPORTANCE_DEFAULT
            ).also { getSystemService(NotificationManager::class.java).createNotificationChannel(it) }
        }
    }

    inner class LocalBinder : Binder() {
        fun getService() = this@PlayMusicService
    }

    companion object {
        const val CHANNEL_ID_NOTIFICATION = "CHANNEL_ID_IN_${BuildConfig.APPLICATION_ID}"
        const val CHANNEL_NAME_NOTIFICATION = "CHANNEL_NAME_IN_${BuildConfig.APPLICATION_ID}"
        const val REQUEST_CODE_PENDING_INTENT = 0
        val ID_START_FOREGROUND_SERVICE = Random.nextInt()
    }
}
