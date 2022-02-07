package com.devsuperior.movieflix.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieWithGenreDTO;
import com.devsuperior.movieflix.entites.Genre;
import com.devsuperior.movieflix.entites.Movie;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository repository;
	
	@Autowired
	private GenreRepository genreRepository;

	@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {
		Optional<Movie> obj = repository.findById(id);
		Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
		return new MovieWithGenreDTO(entity);
	}

	@Transactional(readOnly = true)
	public Page<MovieDTO> findByGenre(Pageable pageable, Long genreId) {
		Genre genre = (genreId == 0) ? null : genreRepository.getOne(genreId);
		Page<Movie> page = repository.findByGenre(genre, pageable);
		return (genreId == 0) ? page.map(x -> new MovieDTO(x)) : page.map(x -> new MovieWithGenreDTO(x));
	}

}
