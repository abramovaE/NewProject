package com.abramovae.newproject.data.service

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters


class MyWorker(context: Context, workerParams: WorkerParameters): Worker(context, workerParams) {



    override fun doWork(): Result {
        return try {


            Result.success()
        } catch (error: Throwable) {
            Result.failure()
            // Result.retry()

        }
    }
}