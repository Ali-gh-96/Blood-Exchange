package com.example.myapplication
import android.app.Notification.EXTRA_NOTIFICATION_ID
import android.os.Bundle
import android.widget.Toast
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val bundle: Bundle? = intent.extras;
        bundle?.let {
            val msg = bundle.getString("key")
            if (msg == "0") {
                Toast.makeText(this,
                    "You can give to:A+, AB+/ You can recive From:A+, A-, O+, O-",
                    Toast.LENGTH_SHORT).show()
            }
            if (msg == "1") {
                Toast.makeText(this,
                    "You can give to:B+, AB+/ You can recive From:B+, B-, O+, O-",
                    Toast.LENGTH_SHORT).show()
            }
            if (msg == "2") {
                Toast.makeText(this,
                    "You can give to:AB+ Only/ You can recive From: All Blood Types",
                    Toast.LENGTH_SHORT).show()
            }
            if (msg == "3") {
                Toast.makeText(this,
                    "You can give to:All Blood Types/ You can recive From:O- ",
                    Toast.LENGTH_SHORT).show()
            }
            if (msg == "4") {
                Toast.makeText(this,
                    "You can give to:A-, A+, AB-, AB+ /:You can recive From:A-, O-",
                    Toast.LENGTH_SHORT).show()
            }
            if (msg == "5") {
                Toast.makeText(this,
                    "You can give to:B-, B+, AB-, AB+/ You can recive From:B-, O- ",
                    Toast.LENGTH_SHORT).show()
            }
            if (msg == "6") {
                Toast.makeText(this,
                    "You can give to:AB-, AB+/ You can recive From:AB-, A-, B-, O-\n ",
                    Toast.LENGTH_SHORT).show()
            }
            if (msg == "7") {
                Toast.makeText(this,
                    "You can give to:O+, A+, B+, AB+/ You can recive From:O+, O-",
                    Toast.LENGTH_SHORT).show()
            }
            Toast.makeText(this, msg, Toast.LENGTH_SHORT)
        }
        createNotificationChannel()
        val btnShowNotification: Button = findViewById(R.id.bt2)
        btnShowNotification.setOnClickListener {
            basicNotification()
        }

    }
    private var notificationId1: Int = 123
    private var notificationId2: Int = 234
    private var notificationId3 :Int = 345
    private val channelId = "App_Channel.testNotification"
    private val description = "Trying to test different types notification"

    private fun basicNotification() {
        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("From Blood exchange")
            .setContentText("Hope the results Get you safe ")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(notificationId1, builder.build())
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun actionsNotification() {
        val snoozeIntent = Intent(this, MainActivity::class.java).apply {
            action = "snooze"
            putExtra(EXTRA_NOTIFICATION_ID, 0)
        }
        val snoozePendingIntent: PendingIntent =
            PendingIntent.getBroadcast(this, 0, snoozeIntent, 0)
        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("My notification")
            .setContentText("Hello World!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(snoozePendingIntent)
            .addAction(
                R.drawable.ic_launcher_background, "Snooze",
                snoozePendingIntent
            )
        with(NotificationManagerCompat.from(this)) {
            notify(notificationId2, builder.build())
        }
        val br: BroadcastReceiver = AfterNotification()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        }
    }
    private fun createNotificationChannel() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = "test_notification"
                val descriptionText = description
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(channelId, name, importance).apply {
                    description = descriptionText
                }
                val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
        }


    }



