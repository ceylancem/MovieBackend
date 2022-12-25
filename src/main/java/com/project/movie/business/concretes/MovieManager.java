package com.project.movie.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.project.movie.business.abstracts.MovieService;
import com.project.movie.dataAccess.abstracts.MovieRepository;
import com.project.movie.dto.requests.MovieRequestDTO;
import com.project.movie.dto.requests.UpdateMovieRequestDTO;
import com.project.movie.dto.responses.MovieResponseDTO;
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
	public void add(MovieRequestDTO createMovieRequest) throws Exception {
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
	public void update(Movie movie) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<MovieResponseDTO> getAll() {
		List<MovieResponseDTO> getMovieResponse = movieRepository.findAll().stream()
				.map(s -> modelMapper.map(s, MovieResponseDTO.class)).collect(Collectors.toList());
		return getMovieResponse;
	}

	@Override
	public MovieResponseDTO getById(long id) throws Exception {
		Movie movie = movieRepository.findById(id).orElseThrow(() -> new Exception("Movie id does not exists!"));
		MovieResponseDTO getMovieResponse = modelMapper.map(movie, MovieResponseDTO.class);
		return getMovieResponse;
	}

	@Override
	public void update(UpdateMovieRequestDTO updateMovieRequestDTO) throws Exception {
		if (updateMovieRequestDTO.getName().isEmpty() || updateMovieRequestDTO.getName().isBlank()) {
			throw new Exception("Name can not be null");
		} else {
			Movie movie = movieRepository.findById(updateMovieRequestDTO.getId())
					.orElseThrow(() -> new Exception("Id does not exists"));
			movie = modelMapper.map(updateMovieRequestDTO, Movie.class);
			movieRepository.save(movie);
		}
	}

}
