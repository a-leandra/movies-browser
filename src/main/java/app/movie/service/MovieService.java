package app.movie.service;

import app.genre.entity.Genre;
import app.movie.entity.Movie;
import app.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for all business actions regarding movie entity.
 */
@Service
public class MovieService {

    /**
     * Repository for movie entity.
     */
    private MovieRepository repository;

    /**
     * @param repository repository for movie entity
     */
    @Autowired
    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    /**
     * Finds single movie.
     *
     * @param title movie's title
     * @return container with movie
     */
    public Optional<Movie> find(String title) {
        return repository.find(title);
    }

    /**
     * @return all movies
     */
    public List<Movie> findAll() {
        return repository.findAll();
    }

    /**
     * @param genre existing genre
     * @return all movies of the selected genre
     */
    public List<Movie> findAll(Genre genre) {
        return repository.findAllByGenre(genre);
    }

    /**
     * Creates new movie.
     *
     * @param movie new movie
     */
    public void create(Movie movie) {
        repository.create(movie);
    }

    /**
     * Updates existing movie.
     *
     * @param movie movie to be updated
     */
    public void update(Movie movie) {
        repository.update(movie);
    }

    /**
     * Deletes existing movie.
     *
     * @param title existing movie's title to be deleted
     */
    public void delete(String title) {
        repository.delete(repository.find(title).orElseThrow());
    }
}
