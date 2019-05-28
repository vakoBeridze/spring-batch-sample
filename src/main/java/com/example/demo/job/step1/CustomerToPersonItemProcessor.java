package com.example.demo.job.step1;

import com.example.demo.data.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * <br/><br/>
 * Created by <b> Vako Beridze &lt;vako.beridze@gmail.com&gt; </b> <br/>
 * Created at  <b> 5/28/19 </b> <br/>
 */
@Slf4j
@Component
public class CustomerToPersonItemProcessor implements ItemProcessor<Customer, Person> {

	@Override
	public Person process(Customer item) throws Exception {
		log.info("processing item: {}", item.toString());

		Thread.sleep(3000);

		return Person.builder()
				.firstName(item.getFirstName())
				.lastName(item.getLastName())
				.email(item.getFirstName() + "." + item.getLastName() + "@mail.com")
				.build();

	}
}
