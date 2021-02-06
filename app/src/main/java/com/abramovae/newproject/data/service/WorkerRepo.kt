package com.abramovae.newproject.data.service

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import java.util.concurrent.TimeUnit

class WorkerRepo {

    val constr = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
        .setRequiresCharging(true)
        .build()

    val locationWork =
        PeriodicWorkRequest.Builder(MyWorker::class.java, 8, TimeUnit.HOURS)
            .addTag("MyWorker")
            .setConstraints(constr)
            .build()



}