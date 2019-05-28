package com.example.demo.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br/><br/>
 * Created by <b> Vako Beridze &lt;vako.beridze@gmail.com&gt; </b> <br/>
 * Created at  <b> 5/28/19 </b> <br/>
 */

@Slf4j
@AllArgsConstructor
@RestController
public class ApiController {

	JobLauncher jobLauncher;
	Job job;

	@GetMapping("run")
	public BatchStatus run() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {


		JobExecution execution = jobLauncher.run(
				job,
				new JobParametersBuilder().addLong("uniqueness", System.nanoTime()).toJobParameters()
		);

		log.info("JobExecution status: {}", execution.getStatus());

		while (execution.isRunning()) {
			System.out.println(". . . ");
		}

		log.info("Job Ended status: {}", execution.getStatus());
		return execution.getStatus();
	}
}
