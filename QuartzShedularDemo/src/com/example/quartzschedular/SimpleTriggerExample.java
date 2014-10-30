package com.example.quartzschedular;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/** SimpleTrigger – Allows to set start time, end time, repeat interval. */

public class SimpleTriggerExample {
	public static void main(String[] args) throws Exception {
		JobDetail job = JobBuilder.newJob(HelloJob.class)
				.withIdentity("dummyJobName", "group1").build();

		/** Trigger the job to run on the next round minute */
		Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity("dummyTriggerName", "group1")
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule()
								.withIntervalInSeconds(2).repeatForever())
				.build();

		/** schedule it */
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		scheduler.scheduleJob(job, trigger);
	}
}
