package com.movieapi.movies;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@ControllerAdvice
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/movies")
public class MovieController {


    @Autowired
    private MovieService service;

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies() {
        return new ResponseEntity<List<Movie>>(service.allMovies(), HttpStatus.OK);
    }

    @GetMapping("/{imdbId}")
    public ResponseEntity<Optional<Movie>> getSingleMovie(@PathVariable String imdbId){

        return new ResponseEntity<Optional<Movie>>(service.singleMovie(imdbId),HttpStatus.OK);

    }

}
