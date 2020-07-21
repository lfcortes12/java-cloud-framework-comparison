package com.gbt.cloud.springorder.listener;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.gbt.cloud.springorder.model.Score;
import com.gbt.cloud.springorder.repository.GameScoreRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@AllArgsConstructor
@Component
public class DataInitializer {

	private final GameScoreRepository gameScoreRepository;

	@EventListener(ApplicationReadyEvent.class)
	public void dataInitializer() {
		gameScoreRepository.deleteAll().thenMany(Flux.fromStream(generateScores(50)))
				.flatMap(score -> gameScoreRepository.save(score)).thenMany(gameScoreRepository.count())
				.subscribe(total -> log.info("Total Game Scores {}", total));

	}

	Stream<Score> generateScores(final int size) {
		Random random = new Random();
		return IntStream.range(1, size).mapToObj(val -> new Score(null, random.nextInt(1000), random.nextInt(10)));
	}

}
