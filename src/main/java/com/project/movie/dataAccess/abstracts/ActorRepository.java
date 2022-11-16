package com.project.movie.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.movie.entities.concretes.Actor;

public interface ActorRepository extends JpaRepository<Actor, Long> {

	@Query(value = "SELECT a.* FROM actors a, movies_actors ab  WHERE a.id = ab.actor_id AND ab.movie_id = :movieId", nativeQuery = true)
//	@Query(value = "SELECT a.* FROM actors a JOIN movies_actors ma ON ma.actor_id = a.id WHERE ma.movie_id = :movieId", nativeQuery = true)
	List<Actor> findByMovieId(@Param("movieId") Long movieId);

}
