package com.gbt.cloud.springorder.web;


import com.gbt.cloud.springorder.model.Score;
import com.gbt.cloud.springorder.repository.GameScoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController("/game-score")
public class GameScoreResource {

    private final GameScoreRepository gameScoreRepository;

    @GetMapping(name = "/")
    public ResponseEntity<List<Score>> getAll() {
        return ResponseEntity.ok(gameScoreRepository.findAll());
    }

}
