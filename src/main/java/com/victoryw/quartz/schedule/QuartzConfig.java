package com.victoryw.quartz.schedule;

import org.quartz.SimpleTrigger;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import com.victoryw.quartz.schedule.jobs.SampleJob;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class QuartzConfig {

    @Bean
    SpringBeanJobFactory jobFactory(ApplicationContext applicationContext)  {
        AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource, JobFactory jobFactory) throws IOException {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();

        schedulerFactory.setOverwriteExistingJobs(true);
        schedulerFactory.setSchedulerName("quartz-scheduler");
        schedulerFactory.setQuartzProperties(quartzProperties());
        schedulerFactory.setDataSource(dataSource);
        schedulerFactory.setJobFactory(jobFactory);
        schedulerFactory.setTriggers(sampleJobTrigger().getObject());
        return schedulerFactory;
    }

    @Bean(name = "sampleJobDetails")
    public JobDetailFactoryBean sampleJobDetails() {

        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(SampleJob.class);
        jobDetailFactoryBean.setName("sample");
        jobDetailFactoryBean.setDurability(true);
        //http://blog.csdn.net/lzj0470/article/details/17786289
        // 抛异常 SchedulerException: Jobs added with no trigger must be durable

        return jobDetailFactoryBean;
    }

    @Bean(name = "sampleJobTrigger")
    public CronTriggerFactoryBean sampleJobTrigger() {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(sampleJobDetails().getObject());
        factoryBean.setCronExpression("0/10 * * * * ?");
        return factoryBean;
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

}
