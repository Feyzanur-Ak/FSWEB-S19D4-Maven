package com.workintech.s19d1.controller;


import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;


    @GetMapping()
    public List<Movie> getAll() {
        List<Movie> movieList = movieService.findAll();


       return movieList.stream().map(movie -> {
            movie.setActors(movie.getActors());
            return movie;
        }).collect((Collectors.toList()));
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable long id) {
        Movie movie = movieService.findById(id);
        movieService.delete(movie);
    }
}
