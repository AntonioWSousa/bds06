package com.devsuperior.movieflix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.movieflix.entites.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long>{

}
