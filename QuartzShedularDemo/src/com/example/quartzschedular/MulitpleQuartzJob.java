package com.example.quartzschedular;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * SimpleTrigger – Allows to set start time, end time, repeat interval.
 * CronTrigger – Allows Unix cron expression to specify the dates and times to
 * run your job.
 */
public class MulitpleQuartzJob {

	public static void main(String[] args) {

		JobKey jkA = new JobKey("JobA", "group1");
		JobDetail jobA = JobBuilder.newJob(JobA.class).withIdentity(jkA)
				.build();

		JobKey jkB = new JobKey("JobB", "group1");
		JobDetail jobB = JobBuilder.newJob(JobB.class).withIdentity(jkB)
				.build();

		JobKey jkC = new JobKey("JobC", "group1");
		JobDetail jobC = JobBuilder.newJob(JobC.class).withIdentity(jkC)
				.build();

		Trigger trigger1 = TriggerBuilder
				.newTrigger()
				.withIdentity("dummyTriggerName1", "group1")
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule()
								.withIntervalInSeconds(5).repeatForever())
				.build();

		Trigger trigger2 = TriggerBuilder
				.newTrigger()
				.withIdentity("dummyTriggerName2", "group1")
				.withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
				.build();

		Trigger trigger3 = TriggerBuilder
				.newTrigger()
				.withIdentity("dummyTriggerName3", "group1")
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule()
								.withIntervalInSeconds(5).repeatForever())
				.build();

		Scheduler scheduler;
		try {
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(jobA, trigger1);
			scheduler.scheduleJob(jobB, trigger2);
			scheduler.scheduleJob(jobC, trigger3);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

	}

}
