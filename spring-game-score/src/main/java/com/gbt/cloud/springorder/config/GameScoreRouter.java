package com.gbt.cloud.springorder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.gbt.cloud.springorder.config.handler.GameScoreHandler;

import lombok.AllArgsConstructor;



@AllArgsConstructor
@Configuration
public class GameScoreRouter {

	@Bean
	RouterFunction<ServerResponse> gameScoreRoutes(final GameScoreHandler gameScoreHandler) {
		return RouterFunctions.route(RequestPredicates.GET("/game-score/scores"), gameScoreHandler::findAll)
				.andRoute(RequestPredicates.GET("/game-score/key"), gameScoreHandler::getKey);
	}

}
