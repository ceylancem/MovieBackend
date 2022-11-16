package com.project.movie.business.abstracts;

import java.util.List;

import com.project.movie.dto.requests.CreateActorRequest;
import com.project.movie.dto.responses.GetActorResponse;
import com.project.movie.entities.concretes.Movie;

public interface ActorService {

	void add(CreateActorRequest createActorRequest) throws Exception;

	void delete(int id);

	void update(Movie movie);

	List<GetActorResponse> getAll();

	GetActorResponse getById(long id) throws Exception;

	List<GetActorResponse> getActorByMovieId(long id);

}
