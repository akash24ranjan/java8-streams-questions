import com.example.dao.CityDao;
import com.example.dao.CountryDao;
import com.example.dao.InMemoryWorldDao;
import com.example.domain.*;
import com.example.exercises.ContinentCityPair;
import com.example.exercises.DirectorGenrePair;
import com.example.exercises.DirectorGenresPair;
import com.example.service.InMemoryMovieService;
import com.example.service.MovieService;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class StreamsAPI_set2 {
    private static final MovieService movieService = InMemoryMovieService.getInstance();
    private static final CountryDao countryDao = InMemoryWorldDao.getInstance();
    private static final CityDao cityDao = InMemoryWorldDao.getInstance();
    public static void main(String[] args) {

        final Collection<Movie> movies = movieService.findAllMovies();
        final Collection<Country> countries = countryDao.findAllCountries();

        System.out.println("============================================================");
        System.out.println("Q.1) Find the number of movies of each director");
        System.out.println("============================================================");

        Map<String, Long> map = movies.stream()
                .map(Movie::getDirectors)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Director::getName,Collectors.counting()));
        map.forEach((name,count)->System.out.println(name+" "+count));

        System.out.println("============================================================");
        System.out.println("Q.2) Find the most populated city of each continent");
        System.out.println("============================================================");

        var highPopulatedCityOfEachContinent = countries.stream()
                .map(country -> country.getCities().stream()
                        .map(city -> new ContinentCityPair(country.getContinent(),city)).toList())
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(ContinentCityPair::continent,Collectors.maxBy(ContinentCityPair :: compareTo)));
        highPopulatedCityOfEachContinent.forEach(ContinentCityPair::printEntry);

        System.out.println("============================================================");
        System.out.println("Q.3) Find the number of genres of each director's movies");
        System.out.println("============================================================");

        var numberOfGenresOfEachDirector = movies.stream()
                .map(movie -> movie.getDirectors().stream()
                        .map(director -> new DirectorGenresPair(director,movie.getGenres())).toList())
                .flatMap(Collection::stream)
                .map(directorGenresPair -> directorGenresPair.genres().stream()
                        .map(genre ->  new DirectorGenrePair(directorGenresPair.director(),genre)).toList())
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(DirectorGenrePair::director,Collectors.groupingBy(DirectorGenrePair::genre,Collectors.counting())));


        numberOfGenresOfEachDirector.forEach(((director, genreLongMap) -> {
            System.out.println(director.getName() + " ");
            genreLongMap.forEach((genre, aLong) -> System.out.println("\t" + genre.getName()+" "+aLong));
        }));


        System.out.println("============================================================");
        System.out.println("Q.4) Find the highest populated capital city");
        System.out.println("============================================================");
        var highestPopulatedCapitalCity = countries.stream()
                .map(Country::getCapital)
                .filter(Objects::nonNull)
                .map(cityDao::findCityById)
                .filter(Objects::nonNull)
                .max(Comparator.comparing(City::getPopulation));

        highestPopulatedCapitalCity.ifPresent(System.out::println);

        System.out.println("============================================================");
        System.out.println("Q.5) Find the highest populated capital city of each continent");
        System.out.println("============================================================");

        var highestPopulatedCityOfContinent = countries.stream()
                .map(country -> new ContinentCityPair(country.getContinent(),cityDao.findCityById(country.getCapital())))
                .filter(continentCityPairStream -> Objects.nonNull(continentCityPairStream.city()))
                .collect(Collectors.groupingBy(ContinentCityPair::continent,Collectors.maxBy(ContinentCityPair::compareTo)));
        System.out.println(highestPopulatedCityOfContinent);


        //    Q.6) Sort the countries by number of their cities in descending order

        //    Q.7) Find the list of movies having the genres "Drama" and "Comedy" only

        //    Q.8) Group the movies by the year and list them

        //    Q.9) Sort the countries by their population densities in descending order ignoring zero population countries

        //    Q.10) Find the richest country of each continent with respect to their GNP (Gross National Product) values.

        //    Q.11) Find the minimum, the maximum and the average population of world countries.

        //    Q.12) Find the minimum, the maximum and the average population of each continent.

        //    Q.13) Find the countries with the minimum and the maximum population.

        //    Q.14) Find the countries of each continent with the minimum and the maximum population.

        //    Q.15) Group the countries by continent, and then sort the countries in continent by number of cities in each continent.

        //    Q.16) Find the cities with the minimum and the maximum population in countries.

        //    Q.17) Find the minimum, the maximum, the average, and the standard deviation of GNP values.

        //    Q.18) Find the year where the maximum number of movie is available
    }
}
