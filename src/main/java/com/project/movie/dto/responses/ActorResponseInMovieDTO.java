package com.project.movie.dto.responses;

import java.time.LocalDate;

import com.project.movie.entities.concretes.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActorResponseInMovieDTO {

	private long id;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private Gender gender;

}
