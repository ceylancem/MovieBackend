package com.project.movie.business.abstracts;

import java.util.List;

import com.project.movie.dto.requests.ActorRequestDTO;
import com.project.movie.dto.responses.ActorResponseDTO;
import com.project.movie.entities.concretes.Movie;

public interface ActorService {

	void add(ActorRequestDTO createActorRequest) throws Exception;

	void delete(int id);

	void update(Movie movie);

	List<ActorResponseDTO> getAll();

	ActorResponseDTO getById(long id) throws Exception;

	List<ActorResponseDTO> getActorByMovieId(long id);

}
