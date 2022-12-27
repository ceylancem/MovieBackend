package com.project.movie.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.project.movie.business.abstracts.MovieService;
import com.project.movie.dataAccess.abstracts.MovieRepository;
import com.project.movie.dto.requests.MovieRequestDto;
import com.project.movie.dto.requests.UpdateMovieRequestDto;
import com.project.movie.dto.responses.MovieResponseDto;
import com.project.movie.entities.concretes.Movie;

@Service
public class MovieManager implements MovieService {

	private MovieRepository movieRepository;
	private ModelMapper modelMapper;

	public MovieManager(MovieRepository movieRepository, ModelMapper modelMapper) {
		this.movieRepository = movieRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public void add(MovieRequestDto createMovieRequest) throws Exception {
		if (createMovieRequest.getName().isEmpty() || createMovieRequest.getName().isBlank())
			throw new Exception("Movie name can not be empty!");
		Movie movie = modelMapper.map(createMovieRequest, Movie.class);
		movieRepository.save(movie);
	}

	@Override
	public void delete(long id) {
		movieRepository.deleteById(id);
	}

	@Override
	public List<MovieResponseDto> getAll() {
		List<MovieResponseDto> getMovieResponse = movieRepository.findAll().stream()
				.map(s -> modelMapper.map(s, MovieResponseDto.class)).collect(Collectors.toList());
		return getMovieResponse;
	}

	@Override
	public MovieResponseDto getById(long id) throws Exception {
		Movie movie = movieRepository.findById(id).orElseThrow(() -> new Exception("Movie id does not exists!"));
		MovieResponseDto getMovieResponse = modelMapper.map(movie, MovieResponseDto.class);
		return getMovieResponse;
	}

	@Override
	public void update(UpdateMovieRequestDto updateMovieRequestDto) throws Exception {
		if (updateMovieRequestDto.getName().isEmpty() || updateMovieRequestDto.getName().isBlank()) {
			throw new Exception("Name can not be null");
		} else {
			Movie movie = movieRepository.findById(updateMovieRequestDto.getId())
					.orElseThrow(() -> new Exception("Id does not exists"));
			movie = modelMapper.map(updateMovieRequestDto, Movie.class);
			movieRepository.save(movie);
		}
	}

}
