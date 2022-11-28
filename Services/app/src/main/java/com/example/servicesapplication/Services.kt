package com.example.servicesapplication

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class MyService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Toast.makeText(this, "MyService started", Toast.LENGTH_LONG).show()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "MyService destroyed", Toast.LENGTH_LONG).show()

    }


    fun doWork()
    {
        val thread = object : Thread() {
            override fun run() {
                Log.i("Service", "MyService ... in doWork() - Thread Started")
                try {
                    sleep(5000)
                    val broadcastIntent = Intent()

                    broadcastIntent.putExtra("Data","A short String of data")
                    broadcastIntent.action = "WORK_COMPLETE_ACTION"

                    baseContext.sendBroadcast(broadcastIntent)


                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                Log.i("Service", "MyService ... in doWork() - Thread Finished")
            }
        }
        thread.start()

    }
}
