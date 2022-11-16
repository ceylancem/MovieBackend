package com.project.movie.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.project.movie.business.abstracts.MovieService;
import com.project.movie.dataAccess.abstracts.MovieRepository;
import com.project.movie.dto.requests.CreateMovieRequest;
import com.project.movie.dto.responses.GetMovieResponse;
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
	public void add(CreateMovieRequest createMovieRequest) throws Exception {
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
	public List<GetMovieResponse> getAll() {
		List<GetMovieResponse> getMovieResponse = movieRepository.findAll().stream()
				.map(s -> modelMapper.map(s, GetMovieResponse.class)).collect(Collectors.toList());
		return getMovieResponse;
	}

	@Override
	public GetMovieResponse getById(long id) throws Exception {
		Movie movie = movieRepository.findById(id).orElseThrow(() -> new Exception("Movie id does not exists!"));
		GetMovieResponse getMovieResponse = modelMapper.map(movie, GetMovieResponse.class);
		return getMovieResponse;
	}

}
