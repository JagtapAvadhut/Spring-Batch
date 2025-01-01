package com.spring_batch.config;

import com.spring_batch.entities.Person;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SpringBatchConfig {

    @Autowired
    private FlatFileItemReader<Person> reader;


    @Bean
    PersonProcessor processor() {
        return new PersonProcessor();
    }

    @Bean
    PersonItemWriter writer() {
        return new PersonItemWriter();
    }

    @Bean
    public Job job(JobRepository jobRepository, Step step) {
        return new JobBuilder("importPersons", jobRepository)
                .start(step)
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("csv-import-step", jobRepository)
                .<Person, Person>chunk(1000, transactionManager)
                .reader(reader)
                .processor(processor())
                .writer(writer())
                .build();
    }
}
