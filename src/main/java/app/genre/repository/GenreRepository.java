package app.genre.repository;

import app.datastore.DataStore;
import app.genre.entity.Genre;
import app.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Genre entity.
 */
@org.springframework.stereotype.Repository
public class GenreRepository implements Repository<Genre, String> {

    /**
     * Underlying data store. In future should be replaced with database connection.
     */
    private DataStore store;

    /**
     * @param store data store
     */
    @Autowired
    public GenreRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Genre> find(String name) {
        return store.findGenre(name);
    }

    @Override
    public List<Genre> findAll() {
        return store.findAllGenres();
    }

    @Override
    public void create(Genre entity) {
        store.createGenre(entity);
    }

    @Override
    public void delete(Genre entity) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override
    public void update(Genre entity) {
        throw new UnsupportedOperationException("Not implemented.");
    }

}
