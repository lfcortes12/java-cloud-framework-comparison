package com.gbt.cloud.web;

import static com.gbt.cloud.model.Score.findAll;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.gbt.cloud.model.Score;

@Path("/game-score")
public class GameScoreResource {
	
	@ConfigProperty(name = "gamescore.key")
	private String gameScoreKey;
	
	@GET
	@Path("/key")
	@Produces(MediaType.TEXT_PLAIN)
	public String getKey() {
	    return gameScoreKey;
	}

	@GET
	@Path("/scores")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Score> getScores() {
	    return findAll().list();
	}
}