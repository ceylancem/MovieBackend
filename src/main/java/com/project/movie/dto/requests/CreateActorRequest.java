package com.project.movie.dto.requests;

import java.time.LocalDate;

import com.project.movie.entities.concretes.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateActorRequest {

	private String firstName;

	private String lastName;

	private LocalDate dateOfBirth;

	private Gender gender;

}
