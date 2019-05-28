package com.example.demo.job.step2;

import com.example.demo.data.domain.Person;
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
@Component
public class PersonToConsoleItemWriter implements ItemWriter<Person> {

	@Override
	public void write(List<? extends Person> items) throws Exception {
		log.info("writing {} items to console: ", items.size());
		items.forEach(item -> log.info(item.toString()));
		Thread.sleep(2000);
	}
}
