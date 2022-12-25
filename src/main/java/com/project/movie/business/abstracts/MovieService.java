package com.project.movie.business.abstracts;

import java.util.List;

import com.project.movie.dto.requests.MovieRequestDTO;
import com.project.movie.dto.requests.UpdateMovieRequestDTO;
import com.project.movie.dto.responses.MovieResponseDTO;
import com.project.movie.entities.concretes.Movie;

public interface MovieService {

	void add(MovieRequestDTO createMovieRequest) throws Exception;

	void delete(long id);

	void update(Movie movie);

//	List<Movie> getAll();

	List<MovieResponseDTO> getAll();

	MovieResponseDTO getById(long id) throws Exception;
	
	void update(UpdateMovieRequestDTO updateMovieRequestDTO) throws Exception;

}
