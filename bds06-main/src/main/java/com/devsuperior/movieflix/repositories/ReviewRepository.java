package com.devsuperior.movieflix.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.movieflix.entites.Movie;
import com.devsuperior.movieflix.entites.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
	
	@Query("SELECT obj "
			+ "FROM Review obj "
			+ "WHERE obj.movie = :movie")
	List<Review> findAllByMovie(Movie movie);

}
