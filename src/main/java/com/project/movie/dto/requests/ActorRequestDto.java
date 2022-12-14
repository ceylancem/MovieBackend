package com.project.movie.dto.requests;

import java.time.LocalDate;
import java.util.List;

import com.project.movie.dto.responses.ActorMovieResponseDto;
import com.project.movie.entities.concretes.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActorRequestDto {

	private String firstName;

	private String lastName;

	private LocalDate dateOfBirth;

	private Gender gender;
	
	private List<ActorMovieResponseDto> movies;

}
