package com.project.movie.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.project.movie.business.abstracts.ActorService;
import com.project.movie.dataAccess.abstracts.ActorRepository;
import com.project.movie.dto.requests.CreateActorRequest;
import com.project.movie.dto.responses.GetActorResponse;
import com.project.movie.entities.concretes.Actor;
import com.project.movie.entities.concretes.Movie;

@Service
public class ActorManager implements ActorService {

	private ActorRepository actorRepository;
	private ModelMapper modelMapper;

	public ActorManager(ActorRepository actorRepository, ModelMapper modelMapper) {
		this.actorRepository = actorRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public void add(CreateActorRequest createActorRequest) throws Exception {
		Actor actor = modelMapper.map(createActorRequest, Actor.class);
		actorRepository.save(actor);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Movie movie) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<GetActorResponse> getAll() {
		List<GetActorResponse> getActorResponses = actorRepository.findAll().stream()
				.map(s -> modelMapper.map(s, GetActorResponse.class)).collect(Collectors.toList());
		return getActorResponses;
	}

	@Override
	public GetActorResponse getById(long id) throws Exception {
		Actor actor = actorRepository.findById(id).orElseThrow(() -> new Exception("Actor id does not exists!"));
		GetActorResponse getActorResponse = modelMapper.map(actor, GetActorResponse.class);
		return getActorResponse;
	}

	@Override
	public List<GetActorResponse> getActorByMovieId(long id) {
		List<GetActorResponse> getActorMovieResponses = actorRepository.findByMovieId(id).stream()
				.map(s -> modelMapper.map(s, GetActorResponse.class)).collect(Collectors.toList());
		return getActorMovieResponses;
	}

}
