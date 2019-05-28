package com.example.demo.job.step1;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/**
 * <br/><br/>
 * Created by <b> Vako Beridze &lt;vako.beridze@gmail.com&gt; </b> <br/>
 * Created at  <b> 5/28/19 </b> <br/>
 */
@Component
public class CSVItemReader {

	@Bean
	public FlatFileItemReader<Customer> csvReader() {
		return new FlatFileItemReaderBuilder<Customer>()
				.name("customerItemReader")
				.resource(new ClassPathResource("customer-data.csv"))
				.delimited()
				.names(new String[]{"firstName", "lastName"})
				.fieldSetMapper(new BeanWrapperFieldSetMapper<Customer>() {{
					setTargetType(Customer.class);
				}})
				.build();
	}
}
