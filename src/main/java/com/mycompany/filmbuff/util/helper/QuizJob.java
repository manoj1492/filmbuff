package com.mycompany.filmbuff.util.helper;

import com.mycompany.filmbuff.service.QuizService;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class QuizJob extends QuartzJobBean {

    @Autowired
    private QuizService quizService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        String quizId = jobDataMap.getString("quizId");
        quizService.publishQuestions(quizId);

    }
    
}
