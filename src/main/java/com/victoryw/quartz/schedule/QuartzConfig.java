package com.victoryw.quartz.schedule;

import org.quartz.SimpleTrigger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import com.victoryw.quartz.schedule.jobs.SampleJob;

@Configuration
public class QuartzConfig {

    @Bean
    SpringBeanJobFactory jobFactory(ApplicationContext applicationContext)  {
        AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(ApplicationContext applicationContext) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();

        schedulerFactory.setOverwriteExistingJobs(true);
        schedulerFactory.setSchedulerName("quartz-scheduler");


        schedulerFactory.setJobFactory(jobFactory(applicationContext));
        schedulerFactory.setTriggers(sampleJobTrigger().getObject());
        return schedulerFactory;
    }

    @Bean(name = "sampleJobDetails")
    public JobDetailFactoryBean sampleJobDetails() {

        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(SampleJob.class);
        jobDetailFactoryBean.setName("sample");

        return jobDetailFactoryBean;
    }

    @Bean(name = "sampleJobTrigger")
    public SimpleTriggerFactoryBean sampleJobTrigger() {
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(sampleJobDetails().getObject());
        factoryBean.setStartDelay(3000);
        factoryBean.setRepeatInterval(10000);
        factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT);
        return factoryBean;
    }
}
