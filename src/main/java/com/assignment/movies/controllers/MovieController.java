package com.assignment.movies.controllers;

import com.assignment.movies.entities.Movie;
import com.assignment.movies.services.MovieService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("movies")
@RequestMapping("/movies")
public class MovieController
{

    @Autowired
    private MovieService service;

    //This piece of code simply feeds the view with all the movies to present them to the registered user
    @GetMapping("/allmovies")
    public String getMovies(Model model)
    {
        List<Movie> movies = service.getAllMovies();
        model.addAttribute("movies", movies);
        return "movies";
    }

    //This piece of code presents the view of a specific movie, the one the user will select based on its Id
    @GetMapping(value = "/movieDetails/{movieId}")
    public String getMovieDetails(@PathVariable("movieId") Long movieId, Model model)
    {
        Movie movieDetails = service.getById(movieId);
        model.addAttribute("movieDetails", movieDetails);
        return "moviedetails";
    }

    //This is the view where customers will watch the movie they paid for.
    //We use the idea to put movies on the Tomcat server but we couldn't put 20 movies because the sevrer wouldn't handle the large amount 
    //of data and possibly have crushing issues so we put on just one movie to a specific folder in the server (../webapps/Movie/) 
    //and we use the front to take it directly from the server.
    @GetMapping("/watch")
    public String watchMovie()
    {
        return "watchmovie";
    }

}
