package com.gbt.cloud.springorder.repository;

import com.gbt.cloud.springorder.model.Score;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameScoreRepository extends MongoRepository<Score, Long> {
}
