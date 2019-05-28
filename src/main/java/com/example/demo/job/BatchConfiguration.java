package com.example.demo.job;


import com.example.demo.data.domain.Person;
import com.example.demo.job.step1.Customer;
import com.example.demo.job.step1.CustomerToPersonItemProcessor;
import com.example.demo.job.step1.PersonToDatabaseItemWriter;
import com.example.demo.job.step2.PersonFromDatabaseItemReader;
import com.example.demo.job.step2.PersonToConsoleItemWriter;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <br/><br/>
 * Created by <b> Vako Beridze &lt;vako.beridze@gmail.com&gt; </b> <br/>
 * Created at  <b> 5/23/19 </b> <br/>
 */


@AllArgsConstructor
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	JobBuilderFactory jobBuilderFactory;
	StepBuilderFactory stepBuilderFactory;

	FlatFileItemReader<Customer> csvReader;
	PersonFromDatabaseItemReader personFromDatabaseItemReader;
	PersonToConsoleItemWriter personToConsoleItemWriter;

	CustomerToPersonItemProcessor customerToPersonItemProcessor;
	PersonToDatabaseItemWriter personToDatabaseItemWriter;

	@Bean
	public Job job() {

		return jobBuilderFactory.get("person-job")
				.incrementer(new RunIdIncrementer())
				.start(step1())
				.next(step2())
				.build();
	}

	private Step step1() {
		return stepBuilderFactory.get("csv-to-database-step")
				.<Customer, Person>chunk(4)
				.reader(csvReader)
				.processor(customerToPersonItemProcessor)
				.writer(personToDatabaseItemWriter)
				.build();
	}

	private Step step2() {
		return stepBuilderFactory.get("database-to-console-step")
				.<Person, Person>chunk(2)
				.reader(personFromDatabaseItemReader)
				.writer(personToConsoleItemWriter)
				.build();
	}


}
