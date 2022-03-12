package app.datastore;

import app.genre.entity.Genre;
import app.movie.entity.Movie;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * For the sake of simplification instead of using real database this version is using a data source object which
 * should be put in servlet context in a single instance.
 */
@Log
@Component
public class DataStore {

    /**
     * Set of all movies.
     */
    private Set<Movie> movies = new HashSet<>();

    /**
     * Set of all genres.
     */
    private Set<Genre> genres = new HashSet<>();

    /**
     * Seeks for all genres.
     *
     * @return list (can be empty) of all genres
     */
    public synchronized List<Genre> findAllGenres() {
        return new ArrayList<>(genres);
    }

    /**
     * Seeks for single genre.
     *
     * @param name genre's name
     * @return container (can be empty) with genre
     */
    public synchronized Optional<Genre> findGenre(String name) {
        return genres.stream()
                .filter(genre -> genre.getName().equals(name))
                .findFirst();
    }

    /**
     * Stores new genre.
     *
     * @param genre new genre
     * @throws IllegalArgumentException if genre with provided name already exists
     */
    public synchronized void createGenre(Genre genre) throws IllegalArgumentException {
        findMovie(genre.getName()).ifPresentOrElse(
                genre1 -> { throw new IllegalArgumentException("Genre with the given name already exists: " + genre.getName());
                },
                () -> genres.add(genre));
    }

    /**
     * Seeks for all movies.
     *
     * @return list (can be empty) of all movies
     */
    public synchronized List<Movie> findAllMovies() {
        return new ArrayList<>(movies);
    }

    /**
     * Seeks for single movie.
     *
     * @param title movie's title
     * @return container (can be empty) with movie
     */
    public synchronized Optional<Movie> findMovie(String title) {
        return movies.stream()
                .filter(movie -> movie.getTitle().equals(title))
                .findFirst();
    }

    /**
     * Stores new movie.
     *
     * @param movie new movie
     * @throws IllegalArgumentException if movie with provided title already exists
     */
    public synchronized void createMovie(Movie movie) throws IllegalArgumentException {
        findMovie(movie.getTitle()).ifPresentOrElse(
                movie1 -> { throw new IllegalArgumentException("Movie with the given title already exists: " + movie.getTitle());
                },
                () -> movies.add(movie));
    }

    /**
     * Updates existing movie.
     *
     * @param movie movie to be updated
     * @throws IllegalArgumentException if movie with the same title does not exist
     */
    public synchronized void updateMovie(Movie movie) throws IllegalArgumentException {
        findMovie(movie.getTitle()).ifPresentOrElse(
                original -> {
                    movies.remove(original);
                    movies.add(movie);
                },
                () -> {
                    throw new IllegalArgumentException("Movie with the given title does not exist: " + movie.getTitle());
                });
    }

    /**
     * Deletes existing movie.
     *
     * @param title movie's title
     * @throws IllegalArgumentException if movie with provided title does not exist
     */
    public synchronized void deleteMovie(String title) throws IllegalArgumentException {
        findMovie(title).ifPresentOrElse(
                movie -> movies.remove(movie),
                () -> {
                    throw new IllegalArgumentException("Movie with the given title does not exist: " + title);
                });
    }

    public Stream<Movie> getMovieStream() {
        return movies.stream();
    }
}
