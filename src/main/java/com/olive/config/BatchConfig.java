package com.olive.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.olive.listener.MyJobExecutionListener;
import com.olive.process.MyItemProcessor;
import com.olive.reader.MyItemReader;
import com.olive.writer.MyItemWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	//create Itemreader obj
	@Bean
	public ItemReader<String> read(){
		return new MyItemReader();
	}
	//create ItemProcessor obj
	@Bean
	public ItemProcessor<String, String> process(){
		return new MyItemProcessor();
	}
	//create ItemWriter obj
	@Bean
	public ItemWriter<String> writer(){
		return new MyItemWriter();
	}
	//create the object of JobExecutionListener
	@Bean
	public JobExecutionListener listener() {
		return new MyJobExecutionListener();
	}
	//autowired on StepBuilderFactory
	@Autowired
	private StepBuilderFactory sf;
	//create the object of Sep
	@Bean
	public Step stepA() {
		return sf.get("stepA")
				.<String,String>chunk(20)
				.reader(read())
				.processor(process())
				.writer(writer())
				.build();
	}
	//apply autowired on JobBuilderFactory
	 private JobBuilderFactory jf;
	//create the object of Job
	 @Bean
	 public Job jobA() {
		 return jf.get("jobA")
				 .incrementer(new RunIdIncrementer())
				  .listener(listener())
				  .start(stepA())
				  //.next(step) for next Step
				  .build();	 
	 }
	

}
