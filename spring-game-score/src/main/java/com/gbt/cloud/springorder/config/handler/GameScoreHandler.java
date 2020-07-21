package com.gbt.cloud.springorder.config.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.gbt.cloud.springorder.model.Score;
import com.gbt.cloud.springorder.repository.GameScoreRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class GameScoreHandler {

	private final GameScoreRepository gameScoreRepository;

	private final String gameScoreKey;

	public GameScoreHandler(final GameScoreRepository gameScoreRepository,
			@Value("${gamescore.key}") final String gameScoreKey) {
		super();
		this.gameScoreRepository = gameScoreRepository;
		this.gameScoreKey = gameScoreKey;
	}

	public Mono<ServerResponse> findAll(final ServerRequest request) {
		log.info("Getting all gamescores");
		return ServerResponse.ok().body(gameScoreRepository.findAll(), Score.class);
	}

	public Mono<ServerResponse> getKey(final ServerRequest request) {
		log.info("Getting key");
		return ServerResponse.ok().bodyValue(gameScoreKey);
	}

}
