package com.gbt.cloud.springorder.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.gbt.cloud.springorder.model.Score;

public interface GameScoreRepository extends ReactiveMongoRepository<Score, Long> {
}
