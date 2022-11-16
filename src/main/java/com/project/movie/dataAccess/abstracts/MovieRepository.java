package com.project.movie.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.movie.entities.concretes.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
