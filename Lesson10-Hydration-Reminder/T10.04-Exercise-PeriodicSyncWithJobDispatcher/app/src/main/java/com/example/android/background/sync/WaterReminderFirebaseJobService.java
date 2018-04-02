/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.background.sync;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class WaterReminderFirebaseJobService extends JobService{

    private BackGroundTask mBackGroundTask = new BackGroundTask();

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        mBackGroundTask.execute(jobParameters);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        if (mBackGroundTask != null) {
            mBackGroundTask.cancel(true);
        }
        return true;
    }
    // COMPLETE (3) WaterReminderFirebaseJobService should extend from JobService

    // COMPLETE (4) Override onStartJob
        // COMPLETE (5) By default, jobs are executed on the main thread, so make an anonymous class extending
        //  AsyncTask called mBackgroundTask.
    @SuppressLint("StaticFieldLeak")
    class BackGroundTask extends AsyncTask<JobParameters, Void, JobParameters> {

        @Override
        protected JobParameters doInBackground(JobParameters... voids) {
            ReminderTasks.executeTask(WaterReminderFirebaseJobService.this, ReminderTasks.ACTION_CHARGING_REMINDER);
            return voids[0];
        }

        @Override
        protected void onPostExecute(JobParameters jobParameters) {
            super.onPostExecute(jobParameters);
            jobFinished(jobParameters, false);
        }

    }
            // COMPLETE (6) Override doInBackground
                // COMPLETE (7) Use ReminderTasks to execute the new charging reminder task you made, use
                // this service as the context (WaterReminderFirebaseJobService.this) and return null
                // when finished.
            // COMPLETE (8) Override onPostExecute and called jobFinished. Pass the job parameters
            // and false to jobFinished. This will inform the JobManager that your job is done
            // and that you do not want to reschedule the job.

        // COMPLETE (9) Execute the AsyncTask
        // COMPLETE (10) Return true

    // COMPLETE (11) Override onStopJob
        // COMPLETE (12) If mBackgroundTask is valid, cancel it
        // COMPLETE (13) Return true to signify the job should be retried

}
