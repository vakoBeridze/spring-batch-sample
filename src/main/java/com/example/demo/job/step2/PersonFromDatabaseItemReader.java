package com.example.demo.job.step2;

import com.example.demo.data.domain.Person;
import com.example.demo.data.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * <br/><br/>
 * Created by <b> Vako Beridze &lt;vako.beridze@gmail.com&gt; </b> <br/>
 * Created at  <b> 5/28/19 </b> <br/>
 */

@AllArgsConstructor
@Component
@JobScope
public class PersonFromDatabaseItemReader extends RepositoryItemReader<Person> {

	private PersonRepository repository;

	@PostConstruct
	protected void init() {
		final Map<String, Sort.Direction> sorts = new HashMap<>();
		sorts.put("id", Sort.Direction.ASC);// This could be any field name of your Entity class
		this.setRepository(this.repository);
		this.setSort(sorts);
		this.setMethodName("findAll");
	}

}
