package com.project.movie.business.abstracts;

import java.util.List;

import com.project.movie.dto.requests.ActorRequestDto;
import com.project.movie.dto.requests.UpdateActorRequestDto;
import com.project.movie.dto.responses.ActorResponseDto;

public interface ActorService {

	void add(ActorRequestDto createActorRequest) throws Exception;

	void delete(long id);

	void update(UpdateActorRequestDto updateActorRequestDto) throws Exception;

	List<ActorResponseDto> getAll();

	ActorResponseDto getById(long id) throws Exception;

	List<ActorResponseDto> getActorByMovieId(long id);

	void addMovie(long actorId, List<Long> movieIds) throws Exception;

	void deleteMovie(long actorId, List<Long> movieIds) throws Exception;

	boolean checkMovieInActor(long actorId, long movieId) throws Exception;

}
