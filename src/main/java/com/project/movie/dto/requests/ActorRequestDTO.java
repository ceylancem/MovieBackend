package com.project.movie.dto.requests;

import java.time.LocalDate;
import java.util.List;

import com.project.movie.entities.concretes.Gender;
import com.project.movie.entities.concretes.Movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActorRequestDTO {

	private String firstName;

	private String lastName;

	private LocalDate dateOfBirth;

	private Gender gender;
	
	private List<Movie> movies;

}
