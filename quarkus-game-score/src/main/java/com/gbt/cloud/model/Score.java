package com.gbt.cloud.model;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

@MongoEntity(collection = "GameScore")
public class Score extends PanacheMongoEntity {
	
	public Integer score;
	public Integer playerId;
	
	public Score() {
		super();
	}

	public Score(Integer score, Integer playerId) {
		super();
		this.score = score;
		this.playerId = playerId;
	}

}
