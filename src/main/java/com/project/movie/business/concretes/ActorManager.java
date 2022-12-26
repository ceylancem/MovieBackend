package com.project.movie.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.project.movie.business.abstracts.ActorService;
import com.project.movie.dataAccess.abstracts.ActorRepository;
import com.project.movie.dataAccess.abstracts.MovieRepository;
import com.project.movie.dto.requests.ActorRequestDTO;
import com.project.movie.dto.requests.UpdateActorRequestDTO;
import com.project.movie.dto.responses.ActorResponseDTO;
import com.project.movie.entities.concretes.Actor;
import com.project.movie.entities.concretes.Movie;

@Service
public class ActorManager implements ActorService {

	private ActorRepository actorRepository;
	private ModelMapper modelMapper;
	private MovieRepository movieRepository;

	public ActorManager(ActorRepository actorRepository, ModelMapper modelMapper, MovieRepository movieRepository) {
		this.actorRepository = actorRepository;
		this.modelMapper = modelMapper;
		this.movieRepository = movieRepository;
	}

	@Override
	public void add(ActorRequestDTO createActorRequest) throws Exception {
		Actor actor = modelMapper.map(createActorRequest, Actor.class);
		actorRepository.save(actor);
	}

	@Override
	public void delete(long id) {
		actorRepository.deleteById(id);
	}

	@Override
	public void update(UpdateActorRequestDTO updateActorRequestDTO) throws Exception {
		if (updateActorRequestDTO.getFirstName().isEmpty() || updateActorRequestDTO.getFirstName().isBlank()
				|| updateActorRequestDTO.getLastName().isEmpty() || updateActorRequestDTO.getLastName().isBlank()) {
			throw new Exception("Name or last name can not be null");
		} else {
			Actor actor = actorRepository.findById(updateActorRequestDTO.getId())
					.orElseThrow(() -> new Exception("Id does not exists"));
			actor = modelMapper.map(updateActorRequestDTO, Actor.class);
			actorRepository.save(actor);
		}
	}

	@Override
	public List<ActorResponseDTO> getAll() {
		List<ActorResponseDTO> getActorResponses = actorRepository.findAll().stream()
				.map(s -> modelMapper.map(s, ActorResponseDTO.class)).collect(Collectors.toList());
		return getActorResponses;
	}

	@Override
	public ActorResponseDTO getById(long id) throws Exception {
		Actor actor = actorRepository.findById(id).orElseThrow(() -> new Exception("Actor id does not exists!"));
		ActorResponseDTO getActorResponse = modelMapper.map(actor, ActorResponseDTO.class);
		return getActorResponse;
	}

	@Override
	public List<ActorResponseDTO> getActorByMovieId(long id) {
		List<ActorResponseDTO> getActorMovieResponses = actorRepository.findByMovieId(id).stream()
				.map(s -> modelMapper.map(s, ActorResponseDTO.class)).collect(Collectors.toList());
		return getActorMovieResponses;
	}

	@Override
	public void addMovie(long actorId, List<Long> movieIds) throws Exception {
		Actor actor = actorRepository.findById(actorId).orElseThrow(() -> new Exception("Actor does not exists!"));
		for (Long movieId : movieIds) {
			Movie movie = movieRepository.findById(movieId).orElse(null);
			if (!checkMovieInActor(actorId, movieId))
				actor.getMovies().add(movie);
		}
		actorRepository.save(actor);
	}

	@Override
	public void deleteMovie(long actorId, List<Long> movieIds) throws Exception {
		Actor actor = actorRepository.findById(actorId).orElseThrow(() -> new Exception("Actor does not exists!"));
		for (Long movieId : movieIds) {
			Movie movie = movieRepository.findById(movieId).orElse(null);
			if (checkMovieInActor(actorId, movieId))
				actor.getMovies().remove(movie);
		}
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
