package com.gbt.cloud.springorder.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gbt.cloud.springorder.model.Score;
import com.gbt.cloud.springorder.repository.GameScoreRepository;


@RefreshScope
@RequestMapping(path = "/game-score")
@RestController
public class GameScoreResource {

	private final GameScoreRepository gameScoreRepository;

	private final String gameScoreKey;

	public GameScoreResource(GameScoreRepository gameScoreRepository, @Value("${gamescore.key}") String gameScoreKey) {
		super();
		this.gameScoreRepository = gameScoreRepository;
		this.gameScoreKey = gameScoreKey;
	}

	@GetMapping(path = "/key")
	public ResponseEntity<String> getKey() {
		return ResponseEntity.ok(gameScoreKey);
	}

	@GetMapping(path = "/search")
	public ResponseEntity<List<Score>> getAll() {
		return ResponseEntity.ok(gameScoreRepository.findAll());
	}

}
