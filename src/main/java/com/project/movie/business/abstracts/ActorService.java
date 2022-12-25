package com.project.movie.business.abstracts;

import java.util.List;

import com.project.movie.dto.requests.ActorRequestDTO;
import com.project.movie.dto.requests.UpdateActorRequestDTO;
import com.project.movie.dto.responses.ActorResponseDTO;

public interface ActorService {

	void add(ActorRequestDTO createActorRequest) throws Exception;

	void delete(long id);

	void update(UpdateActorRequestDTO updateActorRequestDTO) throws Exception;

	List<ActorResponseDTO> getAll();

	ActorResponseDTO getById(long id) throws Exception;

	List<ActorResponseDTO> getActorByMovieId(long id);

}
