package com.project.movie.business.abstracts;

import java.util.List;

import com.project.movie.dto.requests.CreateMovieRequest;
import com.project.movie.dto.responses.GetMovieResponse;
import com.project.movie.entities.concretes.Movie;

public interface MovieService {

	void add(CreateMovieRequest createMovieRequest) throws Exception;

	void delete(long id);

	void update(Movie movie);

//	List<Movie> getAll();

	List<GetMovieResponse> getAll();

	GetMovieResponse getById(long id) throws Exception;

}
