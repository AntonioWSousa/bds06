package com.devsuperior.movieflix.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.movieflix.entites.Genre;
import com.devsuperior.movieflix.entites.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

	@Query("SELECT DISTINCT obj FROM Movie obj WHERE "
			+ ":genre IS NULL OR :genre = obj.genre "
			+ "ORDER BY obj.title")
	Page<Movie> findByGenre(Genre genre, Pageable pageable);

}