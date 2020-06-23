package com.gbt.cloud.springorder.listener;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.gbt.cloud.springorder.model.Score;
import com.gbt.cloud.springorder.repository.GameScoreRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Component
public class DataInitializer {

    private final GameScoreRepository gameScoreRepository;
    @EventListener(ApplicationReadyEvent.class)
    public void dataInitializer() {

        gameScoreRepository.deleteAll();
        gameScoreRepository.saveAll(generateScores(10));
        log.info("Counting Scores {}", gameScoreRepository.count());

    }


    List<Score> generateScores(final int size) {
        Random random = new Random();
        return IntStream.range(1,size).mapToObj(val -> new Score(null, random.nextInt(1000), random.nextInt(10))).collect(Collectors.toList());
    }


}
