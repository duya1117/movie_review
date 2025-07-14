package com.hs.movie_review.controller;

import com.hs.movie_review.dto.MovieDto;
import com.hs.movie_review.model.Movie;
import com.hs.movie_review.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public Page<Movie> List(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        return movieService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public Movie get(@PathVariable Long id) {
        return movieService.getById(id);
    }

    @PostMapping
    public Movie create(@Valid @RequestBody MovieDto movieDto) {
        Movie movie = new Movie();

        movie.setTitle(movieDto.getTitle());
        movie.setReleaseYear(movieDto.getReleaseYear());

        return movieService.create(movie);
    }
}
