package com.example.project.repository;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.project.entity.Genre;
import com.example.project.entity.Movie;
import com.example.project.entity.MovieGenre;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@SpringBootTest
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private MovieGenreRepository movieGenreRepository;

    @Test
    public void insertMovieTest() {
        IntStream.rangeClosed(1, 2).forEach(i -> {
            RestTemplate rt = new RestTemplate();
            // discover 결과값
            ResponseEntity<String> entity = rt
                    .getForEntity(
                            "https://api.themoviedb.org/3/movie/now_playing?language=ko-KR&region=KR&page="
                                    + i
                                    + "&api_key="
                                    + "a7e035c352858d4f14b0213f9415827c",
                            String.class);

            JSONParser jsonParser = new JSONParser();
            try {
                // 파싱
                JSONObject jsonObject = (JSONObject) jsonParser.parse(entity.getBody().toString());

                // results 값
                JSONArray results = (JSONArray) jsonObject.get("results");

                for (Object array : results) {
                    // results 파싱
                    JSONObject discoverMovie = (JSONObject) jsonParser.parse(array.toString());
                    // id 값
                    Long movieID = (Long) discoverMovie.get("id");

                    // movie 상세 정보 결과값
                    entity = rt
                            .getForEntity(
                                    "https://api.themoviedb.org/3/movie/" + movieID + "?language=ko-KR"
                                            + "&api_key="
                                            + "a7e035c352858d4f14b0213f9415827c",
                                    String.class);
                    // 파싱
                    JSONObject movieDetails = (JSONObject) jsonParser.parse(entity.getBody().toString());

                    String backdropPath = (String) movieDetails.get("backdrop_path");
                    Long budget = (Long) movieDetails.get("budget");
                    String homepage = (String) movieDetails.get("homepage");
                    // String origin_country = (String) movieDetails.get("origin_country");
                    String originalLanguage = (String) movieDetails.get("original_language");
                    String originalTitle = (String) movieDetails.get("original_title");
                    String overview = (String) movieDetails.get("overview");
                    Double popularity = (Double) movieDetails.get("popularity");
                    String posterPath = (String) movieDetails.get("poster_path");
                    String release_date = (String) movieDetails.get("release_date");
                    Long revenue = (Long) movieDetails.get("revenue");
                    Long runtime = (Long) movieDetails.get("runtime");
                    String status = (String) movieDetails.get("status");
                    String tagline = (String) movieDetails.get("tagline");
                    String title = (String) movieDetails.get("title");
                    Double voteAverage = (Double) movieDetails.get("vote_average");
                    Long voteCount = (Long) movieDetails.get("vote_count");

                    Movie movie = Movie.builder()
                            .id(movieID)
                            .backdrop_path(backdropPath)
                            .budget(budget)
                            .homepage(homepage)
                            // .origin_country(origin_country)
                            .originalLanguage(originalLanguage)
                            .originalTitle(originalTitle)
                            .overview(overview)
                            .popularity(popularity)
                            .posterPath(posterPath)
                            .releaseDate(release_date)
                            .revenue(revenue)
                            .runtime(runtime)
                            .status(status)
                            .tagline(tagline)
                            .title(title)
                            .voteAverage(voteAverage)
                            .voteCount(voteCount)
                            .build();
                    movieRepository.save(movie);
                }
                // System.out.println(jsonObject2.get("title"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void insertGenreTest() {
        RestTemplate rt = new RestTemplate();
        // discover 결과값
        ResponseEntity<String> entity = rt
                .getForEntity(
                        "https://api.themoviedb.org/3/genre/movie/list?language=ko"
                                + "&api_key="
                                + "a7e035c352858d4f14b0213f9415827c",
                        String.class);

        JSONParser jsonParser = new JSONParser();
        try {
            // 파싱
            JSONObject jsonObject = (JSONObject) jsonParser.parse(entity.getBody().toString());

            // genres 값
            JSONArray genres = (JSONArray) jsonObject.get("genres");

            for (Object array : genres) {

                // genres 파싱
                JSONObject genreJson = (JSONObject) jsonParser.parse(array.toString());
                Long id = (Long) genreJson.get("id");
                String name = (String) genreJson.get("name");

                Genre genre = Genre.builder().id(id).name(name).build();

                genreRepository.save(genre);
            }
            // System.out.println(genres);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ;
    }

    @Test
    public void insertMovieGenreTest() {
        IntStream.rangeClosed(1, 2).forEach(i -> {
            RestTemplate rt = new RestTemplate();
            // discover 결과값
            ResponseEntity<String> entity = rt
                    .getForEntity(
                            "https://api.themoviedb.org/3/movie/now_playing?language=ko-KR&region=KR&page="
                                    + i
                                    + "&api_key="
                                    + "a7e035c352858d4f14b0213f9415827c",
                            String.class);

            JSONParser jsonParser = new JSONParser();
            try {
                // 파싱
                JSONObject jsonObject = (JSONObject) jsonParser.parse(entity.getBody().toString());

                // results 값
                JSONArray results = (JSONArray) jsonObject.get("results");

                for (Object array : results) {
                    // results 파싱
                    JSONObject discoverMovie = (JSONObject) jsonParser.parse(array.toString());
                    // id 값
                    Long movieID = (Long) discoverMovie.get("id");

                    JSONArray genreIds = (JSONArray) jsonParser.parse(discoverMovie.get("genre_ids").toString());
                    for (Object genreId : genreIds) {
                        MovieGenre movieGenre = MovieGenre.builder()
                                .movie(Movie.builder().id(movieID).build())
                                .genre(Genre.builder().id((Long) genreId).build())
                                .build();
                        movieGenreRepository.save(movieGenre);
                    }

                }
                // System.out.println(jsonObject2.get("title"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    @Transactional
    @Test
    public void getGenre() {
        List<String> genre = movieRepository.getGenre(558449L);
        System.out.println(genre);
    }

}
