package com.project.movie.dto.responses;

import java.util.List;

import com.project.movie.entities.concretes.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponseDto {

	private long id;
	private String name;
	private String description;
	private String imdbUrl;
	private int duration;
	private int releaseYear;
	private List<Category> categories;
	private List<ActorResponseInMovieDto> actors;

}
