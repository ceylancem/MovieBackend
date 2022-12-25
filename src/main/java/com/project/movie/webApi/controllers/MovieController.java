package com.project.movie.webApi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.movie.business.abstracts.MovieService;
import com.project.movie.dto.requests.MovieRequestDTO;
import com.project.movie.dto.requests.UpdateMovieRequestDTO;
import com.project.movie.dto.responses.MovieResponseDTO;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

	private MovieService movieService;

	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	@PostMapping("/add")
	public void add(@RequestBody MovieRequestDTO createMovieRequest) throws Exception {
		movieService.add(createMovieRequest);
	}

	@GetMapping("/getall")
	public List<MovieResponseDTO> getAll() {
		return movieService.getAll();
	}

	@GetMapping("/getbyid")
	public MovieResponseDTO getById(@RequestParam long id) throws Exception {
		return movieService.getById(id);
	}

	@PutMapping("/update")
	public void update(@RequestBody UpdateMovieRequestDTO updateMovieRequestDTO) throws Exception {
		movieService.update(updateMovieRequestDTO);
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@RequestParam long id) {
		movieService.delete(id);
	}

}
