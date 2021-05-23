package com.iswust.config;

import com.iswust.util.QuartzJobDayUtil;
import com.iswust.util.QuartzJobWeekUtil;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class QuartzInit {
    private static final String Recommend_Week_IDENTITY = "recommendWeekQuartz";
    private static final String Recommend_Day_IDENTITY = "recommendDayQuartz";

    @Autowired
    public Scheduler scheduler;

    @PostConstruct
    public void JobInit() throws SchedulerException {
//        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
//        scheduler.clear();

        JobDetail job = JobBuilder.newJob(QuartzJobWeekUtil.class)
                .withIdentity(Recommend_Week_IDENTITY)
                .storeDurably()
                .build();
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .forJob(job)
                .startNow()
                .withIdentity(Recommend_Week_IDENTITY)
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 1 ? * 2 *"))
                .build();

        JobDetail job1 = JobBuilder.newJob(QuartzJobDayUtil.class)
                .withIdentity(Recommend_Day_IDENTITY)
                .storeDurably()
                .build();
        CronTrigger trigger1 = TriggerBuilder.newTrigger()
                .forJob(job1)
                .startNow()
                .withIdentity(Recommend_Day_IDENTITY)
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 1 * * ? *"))
                .build();

        scheduler.scheduleJob(job,trigger);
        scheduler.scheduleJob(job1, trigger1);

    }
//    private static final String Recommend_IDENTITY = "recommendQuartz";
//
//    @Bean
//    public JobDetail quartzDetail(){
//        return JobBuilder.newJob(QuartzJobUtil.class).withIdentity(Recommend_IDENTITY).storeDurably().build();
//    }
//
//    @Bean
//    public Trigger quartzTrigger(){
//        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 1 ? * 2 *");
////        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("* * * ? * * *");
//        return TriggerBuilder.newTrigger().forJob(quartzDetail())
//                .startNow()
//                .withIdentity(Recommend_IDENTITY)
//                .withSchedule(scheduleBuilder)
//                .build();
//    }
}