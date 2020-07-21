package com.gbt.cloud.listener;

import static io.quarkus.mongodb.panache.PanacheMongoEntityBase.persist;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.enterprise.event.Observes;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gbt.cloud.model.Score;

import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ProfileManager;

public class DataInitializer {

	Logger log = LoggerFactory.getLogger(DataInitializer.class);
	private final Random random = new Random();

	@Transactional
	public void startup(@Observes StartupEvent startupEvent) {
		log.debug("The application is starting with profile {} ", ProfileManager.getActiveProfile());
		log.debug("Creating initial data");

		//Score.deleteAll();
		persist(generateScores(50));
		log.info("Counting Scores {}", Score.count());
	}

	List<Score> generateScores(final int size) {
		
		return IntStream.range(1, size).mapToObj(val -> new Score(random.nextInt(1000), random.nextInt(10)))
				.collect(Collectors.toList());
	}

}
