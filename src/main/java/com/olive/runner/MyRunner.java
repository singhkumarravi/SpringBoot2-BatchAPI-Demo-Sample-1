package com.olive.runner;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component
public class MyRunner implements CommandLineRunner {
	//a.read job and JobExecution
	//b. override run() method
	//c. pass input argument to current JobLauncher

	@Autowired
	private Job jobA;

	@Autowired
	private JobLauncher launcher;


	@Override
	public void run(String... args) throws Exception {
		JobParameters jobParameter =
				new JobParametersBuilder()
				.addLong("Time ::", System.currentTimeMillis())
				.addString("Launcher By ", "Ravi Kumar Singh")
				.toJobParameters();
		launcher.run(jobA, jobParameter);

	}

}
