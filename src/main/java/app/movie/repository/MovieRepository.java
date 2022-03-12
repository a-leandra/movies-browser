package app.movie.repository;

import app.datastore.DataStore;
import app.genre.entity.Genre;
import app.movie.entity.Movie;
import app.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Repository for movie entity.
 */
@org.springframework.stereotype.Repository
public class MovieRepository implements Repository<Movie, String> {

    /**
     * Underlying data store. In future should be replaced with database connection.
     */
    private DataStore store;

    /**
     * @param store data store
     */
    @Autowired
    public MovieRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Movie> find(String title) {
        return store.findMovie(title);
    }

    @Override
    public List<Movie> findAll() {
        return store.findAllMovies();
    }

    @Override
    public void create(Movie entity) {
        store.createMovie(entity);
    }

    @Override
    public void delete(Movie entity) {
        store.deleteMovie(entity.getTitle());
    }

    @Override
    public void update(Movie entity) {
        store.updateMovie(entity);
    }

    /**
     * Seeks for all genre's movies.
     *
     * @param genre movies' genre
     * @return list (can be empty) of genre's movies
     */
    public List<Movie> findAllByGenre(Genre genre) {
        return store.getMovieStream()
                .filter(movie -> movie.getGenre().equals(genre))
                .collect(Collectors.toList());
    }
}
