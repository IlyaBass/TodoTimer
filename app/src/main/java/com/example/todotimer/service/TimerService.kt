package com.example.todotimer.service

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.os.Vibrator
import com.example.domain.common.core.service.TimeFormatService
import com.example.domain.interactor.GetTodoByIdUseCase
import com.example.domain.interactor.UpdateTodoUseCase
import com.example.todotimer.App
import com.example.todotimer.R
import com.example.todotimer.screens.timer.ui.TimerActivity
import com.example.todotimer.utils.CountDownTimer
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class TimerService : Service() {

	companion object {
		val isServicePaused = MutableStateFlow(true)
		val serviceId = MutableStateFlow<Long?>(null)
	}

	@Inject
	lateinit var getTodoByIdUseCase: GetTodoByIdUseCase

	@Inject
	lateinit var updateTodoUseCase: UpdateTodoUseCase

	@Inject
	lateinit var timeFormatService: TimeFormatService

	lateinit var timer: CountDownTimer
	private val notificationId = "TimerServiceNotificationId"

	@SuppressLint("CheckResult", "UnspecifiedImmutableFlag")
	override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
		(applicationContext as App).appComponent.inject(this)

		val todoId = intent?.getLongExtra("todoId", 0)!!
		serviceId.value = todoId

		val notificationManager = getSystemService(NotificationManager::class.java)
		notificationManager.createNotificationChannel(notificationChannel())

		val notificationBuilder = Notification
			.Builder(this, notificationId)
			.setSmallIcon(R.mipmap.ic_launcher)
			.setContentIntent(buildPendingIntent(todoId))

		getTodoByIdUseCase.execute(todoId).subscribe { todoData ->

			notificationBuilder.setContentTitle(todoData.title)

			timer = object : CountDownTimer(
				timeFormatService.fromPattern(todoData.time),
				1000
			) {

				override fun onTick(millisUntilFinished: Long) {
					notificationBuilder.setContentText(
						timeFormatService.toPattern(millisUntilFinished)
					)
					notificationManager.notify(todoId.toInt(), notificationBuilder.build())

					updateTodoUseCase.execute(
							todoData.id,
							todoData.title,
							timeFormatService.toPattern(millisUntilFinished),
							true
						).subscribe()
				}

				override fun onFinish() {
					val vibrator: Vibrator =
						getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
					vibrator.vibrate(700)

					getTodoByIdUseCase.execute(todoData.id).subscribe {
						updateTodoUseCase.execute(
							it.id,
							it.title,
							timeFormatService.toPattern(0),
							false
						).subscribe()
					}
					stopSelf()
				}

				override fun pause(): Long {
					if (isServicePaused.value) {
						getTodoByIdUseCase.execute(todoData.id).subscribe {
							updateTodoUseCase.execute(
								it.id,
								it.title,
								it.time,
								false
							).subscribe()
						}
					}
					return super.pause()
				}
			}
			timer.start()
		}

		startForeground(todoId.toInt(), notificationBuilder.build())
		return START_NOT_STICKY
	}

	private fun notificationChannel() = NotificationChannel(
		notificationId,
		notificationId,
		NotificationManager.IMPORTANCE_HIGH
	)

	private fun buildPendingIntent(todoId: Long): PendingIntent {
		val notificationIntent = Intent(this, TimerActivity::class.java)
			.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
			.putExtra("todoId", todoId)

		return PendingIntent.getActivity(
			this, todoId.toInt(), notificationIntent, PendingIntent.FLAG_IMMUTABLE
		)
	}

	override fun onDestroy() {
		super.onDestroy()
		timer.pause()
	}

	override fun onBind(p0: Intent?): IBinder? {
		return null
	}
}
