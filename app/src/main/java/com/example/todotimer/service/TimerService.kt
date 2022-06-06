package com.example.todotimer.service

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.os.Vibrator
import android.util.Log
import com.example.domain.common.core.service.TimeFormatService
import com.example.domain.interactor.GetTodoByIdUseCase
import com.example.domain.interactor.UpdateTodoUseCase
import com.example.todotimer.App
import com.example.todotimer.R
import com.example.todotimer.screens.timer.ui.TimerActivity
import com.example.todotimer.utils.CountDownTimer
import javax.inject.Inject

class TimerService : Service() {

    @Inject
    lateinit var getTodoByIdUseCase: GetTodoByIdUseCase

    @Inject
    lateinit var updateTodoUseCase: UpdateTodoUseCase

    @Inject
    lateinit var timeFormatService: TimeFormatService

    lateinit var timer: CountDownTimer
    private val notificationId = "TimerServiceNotificationId"
    private var isFinished = false

    @SuppressLint("CheckResult", "UnspecifiedImmutableFlag")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.d("myLogger", "onStartCommand")
        (applicationContext as App).appComponent.inject(this)

        val todoId = intent?.getLongExtra("todoId", 0)!!
        serviceId = todoId

        val notificationChannel = NotificationChannel(
            notificationId,
            notificationId,
            NotificationManager.IMPORTANCE_HIGH
        )

        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(notificationChannel)

        val notificationIntent = Intent(this, TimerActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            .putExtra("todoId", todoId)

        val pendingIntent = PendingIntent.getActivity(
            this, todoId.toInt(), notificationIntent, 0
        )

        val notificationBuilder = Notification
            .Builder(this, notificationId)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentIntent(pendingIntent)

        if (startId <= 1) {
            getTodoByIdUseCase.execute(todoId).subscribe { todoData ->
                timer = object : CountDownTimer(todoData.time, 1000) {

                    override fun onTick(millisUntilFinished: Long) {
                        notificationBuilder.setContentText(
                            timeFormatService.toPattern(
                                millisUntilFinished
                            )
                        )
                        notificationManager.notify(todoId.toInt(), notificationBuilder.build())
                        updateTodoUseCase
                            .execute(todoData.id, todoData.title, millisUntilFinished, true)
                            .subscribe()
                    }

                    override fun onFinish() {
                        val vibrator: Vibrator =
                            getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                        vibrator.vibrate(700)
                        isFinished = true
                        getTodoByIdUseCase.execute(todoData.id).subscribe {
                            updateTodoUseCase.execute(
                                it.id,
                                it.title,
                                0,
                                false
                            ).subscribe()
                        }
                        stopSelf()
                    }

                    override fun pause(): Long {
                        getTodoByIdUseCase.execute(todoData.id).subscribe {
                            updateTodoUseCase.execute(
                                it.id,
                                it.title,
                                it.time,
                                false
                            ).subscribe()
                        }
                        return super.pause()
                    }
                }
                timer.start()
            }

            startForeground(todoId.toInt(), notificationBuilder.build())
        }
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!isFinished) {
            timer.pause()
        }
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    companion object {
        var serviceId: Long? = null
    }
}
