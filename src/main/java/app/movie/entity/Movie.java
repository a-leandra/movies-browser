package app.movie.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * Entity for a movie belonging to the genre. Represents movie's basic information.
 * Also contains link to genre (see @link {@link //Genre}) for the sake of database relationship.
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(callSuper = true)
//@EqualsAndHashCode(callSuper = true)
public class Movie {
    private String title;

    private LocalDate releaseDate;

    private String director;

    private int overallRating;

    //private Genre genre;
}
