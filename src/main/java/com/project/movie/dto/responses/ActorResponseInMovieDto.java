package com.project.movie.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActorResponseInMovieDto {

	private long id;
	private String firstName;
	private String lastName;
//	private LocalDate dateOfBirth;
//	private Gender gender;

}
