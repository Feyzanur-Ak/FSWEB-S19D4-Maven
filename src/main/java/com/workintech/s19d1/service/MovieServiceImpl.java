package com.workintech.s19d1.service;


import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.exceptions.ApiException;
import com.workintech.s19d1.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class MovieServiceImpl implements  MovieService{


    @Autowired
    private MovieRepository movieRepository;


    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ApiException("Movie is not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public void delete(Movie movie) {
        Movie deletedMovie=findById(movie.getId());

        movieRepository.delete(deletedMovie);
    }
}
