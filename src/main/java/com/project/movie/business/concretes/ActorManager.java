package com.project.movie.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.project.movie.business.abstracts.ActorService;
import com.project.movie.business.abstracts.MovieService;
import com.project.movie.dataAccess.abstracts.ActorRepository;
import com.project.movie.dto.requests.ActorRequestDto;
import com.project.movie.dto.requests.UpdateActorRequestDto;
import com.project.movie.dto.responses.ActorResponseDto;
import com.project.movie.dto.responses.MovieResponseDto;
import com.project.movie.dto.responses.MovieResponseInActorDto;
import com.project.movie.entities.concretes.Actor;
import com.project.movie.entities.concretes.Movie;

@Service
public class ActorManager implements ActorService {

	private ActorRepository actorRepository;
	private ModelMapper modelMapper;
	private MovieService movieService;

	public ActorManager(ActorRepository actorRepository, ModelMapper modelMapper, MovieService movieService) {
		this.actorRepository = actorRepository;
		this.modelMapper = modelMapper;
		this.movieService = movieService;
	}

	@Override
	public void add(ActorRequestDto createActorRequest) throws Exception {
		Actor actor = modelMapper.map(createActorRequest, Actor.class);
		actorRepository.save(actor);
	}

	@Override
	public void delete(long id) {
		actorRepository.deleteById(id);
	}

	@Override
	public void update(UpdateActorRequestDto updateActorRequestDto) throws Exception {
		if (updateActorRequestDto.getFirstName().isEmpty() || updateActorRequestDto.getFirstName().isBlank()
				|| updateActorRequestDto.getLastName().isEmpty() || updateActorRequestDto.getLastName().isBlank()) {
			throw new Exception("Name or last name can not be null");
		} else {
			Actor actor = actorRepository.findById(updateActorRequestDto.getId())
					.orElseThrow(() -> new Exception("Id does not exists"));
			actor = modelMapper.map(updateActorRequestDto, Actor.class);
			actorRepository.save(actor);
		}
	}

	@Override
	public List<ActorResponseDto> getAll() {
		List<ActorResponseDto> getActorResponses = actorRepository.findAll().stream()
				.map(s -> modelMapper.map(s, ActorResponseDto.class)).collect(Collectors.toList());
		return getActorResponses;
	}

	@Override
	public ActorResponseDto getById(long id) throws Exception {
		Actor actor = actorRepository.findById(id).orElseThrow(() -> new Exception("Actor id does not exists!"));
		ActorResponseDto getActorResponse = modelMapper.map(actor, ActorResponseDto.class);
		return getActorResponse;
	}

	@Override
	public List<ActorResponseDto> getActorByMovieId(long id) {
		List<ActorResponseDto> getActorMovieResponses = actorRepository.findByMovieId(id).stream()
				.map(s -> modelMapper.map(s, ActorResponseDto.class)).collect(Collectors.toList());
		return getActorMovieResponses;
	}

	@Override
	public void addMovie(long actorId, List<Long> movieIds) throws Exception {
		Actor actor = actorRepository.findById(actorId).orElseThrow(() -> new Exception("Actor does not exists!"));
		for (Long movieId : movieIds) {
			MovieResponseDto movieResponseDTO = movieService.getById(movieId);
			Movie movie = modelMapper.map(movieResponseDTO, Movie.class);
			if (!checkMovieInActor(actorId, movieId))
				actor.getMovies().add(movie);
		}
		actorRepository.save(actor);
	}

	@Override
	public void deleteMovie(long actorId, List<Long> movieIds) throws Exception {
		ActorResponseDto actorResponseDto = getById(actorId);
		for (Long movieId : movieIds) {
			MovieResponseDto movieResponseDto = movieService.getById(movieId);
			if (checkMovieInActor(actorId, movieId)) {
				MovieResponseInActorDto movieResponseInActorDto = modelMapper.map(movieResponseDto,
						MovieResponseInActorDto.class);
				actorResponseDto.getMovies().remove(movieResponseInActorDto);
			}
		}
		Actor actor = modelMapper.map(actorResponseDto, Actor.class);
		actorRepository.save(actor);
	}

	@Override
	public boolean checkMovieInActor(long actorId, long movieId) throws Exception {
		Actor actor = actorRepository.findById(actorId).orElseThrow(() -> new Exception("Actor does not exists!"));
		for (Movie movie : actor.getMovies()) {
			if (movie.getId() == movieId)
				return true;
		}
		return false;
	}

}
