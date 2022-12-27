package com.project.movie.business.abstracts;

import java.util.List;

import com.project.movie.dto.requests.MovieRequestDto;
import com.project.movie.dto.requests.UpdateMovieRequestDto;
import com.project.movie.dto.responses.MovieResponseDto;

public interface MovieService {

	void add(MovieRequestDto createMovieRequest) throws Exception;

	void delete(long id);

	List<MovieResponseDto> getAll();

	MovieResponseDto getById(long id) throws Exception;

	void update(UpdateMovieRequestDto updateMovieRequestDto) throws Exception;

}
