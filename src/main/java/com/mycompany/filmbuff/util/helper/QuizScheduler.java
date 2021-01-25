package com.mycompany.filmbuff.util.helper;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

import com.mycompany.filmbuff.entity.Quiz;
import com.mycompany.filmbuff.util.Constants;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizScheduler {

    @Autowired
    private Scheduler scheduler;

    public void scheduleQuiz(Quiz quiz) throws SchedulerException {

        ZonedDateTime dateTime = ZonedDateTime.of(quiz.getStartTime(), ZoneId.systemDefault());
        JobDetail jobDetail = buildJobDetail(quiz);
        SimpleTrigger trigger = buildJobTrigger(jobDetail, dateTime);
        scheduler.scheduleJob(jobDetail, trigger);
    }

    private JobDetail buildJobDetail(Quiz quiz) {
        JobDataMap jobDataMap = new JobDataMap();

        jobDataMap.put("quizId", quiz.getId().toString());
        jobDataMap.put("repeatCount", quiz.getQuestionLimit());

        return JobBuilder.newJob(QuizJob.class)
                .withIdentity(UUID.randomUUID().toString(), "quiz-jobs")
                .withDescription("Call Publish Questions API")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    private SimpleTrigger buildJobTrigger(JobDetail jobDetail, ZonedDateTime startAt) {

        Integer repeatCount = jobDetail.getJobDataMap().getInt("repeatCount");
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "email-triggers")
                .withDescription("Send Email Trigger")
                .startAt(Date.from(startAt.toInstant()))
                .endAt(Date.from(startAt.toInstant().plusSeconds(repeatCount * Constants.TIME_INTERVAL+ 1)))
                .withSchedule(SimpleScheduleBuilder
                    .simpleSchedule()
                    .withIntervalInSeconds(Constants.TIME_INTERVAL)
                    .withRepeatCount(repeatCount - 1))
                .build();
    }
}
