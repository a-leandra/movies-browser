package app.genre.entity;

import app.movie.entity.Movie;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Entity for genre. Represents information about particular genre.
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Genre {

    private String name;

    private String description;

    @ToString.Exclude
    private List<Movie> movies;
}
