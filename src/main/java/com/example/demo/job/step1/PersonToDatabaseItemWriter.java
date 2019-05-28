package com.example.demo.job.step1;

import com.example.demo.data.domain.Person;
import com.example.demo.data.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <br/><br/>
 * Created by <b> Vako Beridze &lt;vako.beridze@gmail.com&gt; </b> <br/>
 * Created at  <b> 5/28/19 </b> <br/>
 */
@Slf4j
@AllArgsConstructor
@Component
public class PersonToDatabaseItemWriter implements ItemWriter<Person> {

	PersonRepository repository;

	@Override
	public void write(List<? extends Person> items) throws Exception {
		log.info("writing {} items to database: ", items.size());
		repository.saveAll(items);
		Thread.sleep(2000);
	}
}
