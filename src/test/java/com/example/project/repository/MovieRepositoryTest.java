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
import com.example.project.entity.MoviePeople;
import com.example.project.entity.People;
import com.example.project.repository.movie.GenreRepository;
import com.example.project.repository.movie.MovieGenreRepository;
import com.example.project.repository.movie.MoviePeopleRepository;
import com.example.project.repository.movie.MovieRepository;
import com.example.project.repository.movie.PeopleRepository;
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

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private MoviePeopleRepository moviePeopleRepository;

    // @Test
    // public void insertMovieTest() {
    // IntStream.rangeClosed(1, 500).forEach(i -> {
    // RestTemplate rt = new RestTemplate();
    // // discover 결과값
    // ResponseEntity<String> entity = rt
    // .getForEntity(
    // "https://api.themoviedb.org/3/movie/popular?language=ko-KR&region=KR&page="
    // + i
    // + "&api_key="
    // + "a7e035c352858d4f14b0213f9415827c",
    // String.class);

    // JSONParser jsonParser = new JSONParser();
    // try {
    // // 파싱
    // JSONObject jsonObject = (JSONObject)
    // jsonParser.parse(entity.getBody().toString());

    // // results 값
    // JSONArray results = (JSONArray) jsonObject.get("results");

    // for (Object array : results) {
    // // results 파싱
    // JSONObject discoverMovie = (JSONObject) jsonParser.parse(array.toString());
    // // id 값
    // Long movieID = (Long) discoverMovie.get("id");

    // // movie 상세 정보 결과값
    // entity = rt
    // .getForEntity(
    // "https://api.themoviedb.org/3/movie/" + movieID + "?language=ko-KR"
    // + "&api_key="
    // + "a7e035c352858d4f14b0213f9415827c",
    // String.class);
    // // 파싱
    // JSONObject movieDetails = (JSONObject)
    // jsonParser.parse(entity.getBody().toString());

    // String backdropPath = (String) movieDetails.get("backdrop_path");
    // Long budget = (Long) movieDetails.get("budget");
    // String homepage = (String) movieDetails.get("homepage");
    // // String origin_country = (String) movieDetails.get("origin_country");
    // String originalLanguage = (String) movieDetails.get("original_language");
    // String originalTitle = (String) movieDetails.get("original_title");
    // String overview = (String) movieDetails.get("overview");
    // Double popularity = (Double) movieDetails.get("popularity");
    // String posterPath = (String) movieDetails.get("poster_path");
    // String release_date = (String) movieDetails.get("release_date");
    // Long revenue = (Long) movieDetails.get("revenue");
    // Long runtime = (Long) movieDetails.get("runtime");
    // String status = (String) movieDetails.get("status");
    // String tagline = (String) movieDetails.get("tagline");
    // String title = (String) movieDetails.get("title");
    // Double voteAverage = (Double) movieDetails.get("vote_average");
    // Long voteCount = (Long) movieDetails.get("vote_count");

    // JSONArray genreIds = (JSONArray)
    // movieDetails.parse(discoverMovie.get("genres").toString());
    // for (Object genreId : genreIds) {
    // MovieGenre movieGenre = MovieGenre.builder()
    // .movie(Movie.builder().id(movieID).build())
    // .genre(Genre.builder().id((Long) genreId).build())
    // .build();
    // movieGenreRepository.save(movieGenre);
    // }

    // Movie movie = Movie.builder()
    // .id(movieID)
    // .backdrop_path(backdropPath)
    // .budget(budget)
    // .homepage(homepage)
    // // .origin_country(origin_country)
    // .originalLanguage(originalLanguage)
    // .originalTitle(originalTitle)
    // .overview(overview)
    // .popularity(popularity)
    // .posterPath(posterPath)
    // .releaseDate(release_date)
    // .revenue(revenue)
    // .runtime(runtime)
    // .status(status)
    // .tagline(tagline)
    // .title(title)
    // .voteAverage(voteAverage)
    // .voteCount(voteCount)
    // .build();

    // movieRepository.save(movie);
    // }
    // // System.out.println(jsonObject2.get("title"));
    // } catch (ParseException e) {
    // e.printStackTrace();
    // }
    // });
    // }

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
        List<Movie> movies = movieRepository.findAll();
        movies.forEach(movie -> {

        });

        IntStream.rangeClosed(1, 100).forEach(i -> {
            RestTemplate rt = new RestTemplate();
            // discover 결과값
            ResponseEntity<String> entity = rt
                    .getForEntity(
                            "https://api.themoviedb.org/3/movie/popular?language=ko-KR&region=KR&page="
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

    // @Transactional
    // @Test
    // public void getGenre() {
    // List<String> genre = movieRepository.getGenre(558449L);
    // System.out.println(genre);
    // }

    // // Movie 테이블에 있는 영화의 id와 그 영화에 출연하는 배우 id, 역할 MoviePeople 테이블에 저장
    // // 및 People 테이블에 그 배우들 저장(id, 이름만 저장)
    // @Test
    // public void insertMoviePeopleTest() {
    // // movie 테이블 영화 리스트
    // List<Movie> movies = movieRepository.findAll();

    // RestTemplate rt = new RestTemplate();
    // for (Movie movie : movies) {
    // // 영화 인물 정보
    // ResponseEntity<String> entity = rt
    // .getForEntity(
    // "https://api.themoviedb.org/3/movie/" + movie.getId() +
    // "/credits?language=ko-KR"
    // + "&api_key="
    // + "a7e035c352858d4f14b0213f9415827c",
    // String.class);

    // JSONParser jsonParser = new JSONParser();
    // try {
    // // 파싱
    // JSONObject jsonObject = (JSONObject)
    // jsonParser.parse(entity.getBody().toString());

    // JSONArray cast = (JSONArray) jsonObject.get("cast");

    // for (Object p : cast) {
    // // 인물 정보 파싱
    // JSONObject person = (JSONObject) jsonParser.parse(p.toString());
    // // id 값
    // Long id = (Long) person.get("id");
    // String character = (String) person.get("character");
    // String name = (String) person.get("name");

    // // 영화에 출연한 배우 id people 테이블에 저장
    // People people = People.builder()
    // .id(id)
    // .name(name)
    // .build();
    // peopleRepository.save(people);

    // // 영화 id, 배우 id, 배우 역할 moviepeople 테이블에 저장
    // MoviePeople moviePeople = MoviePeople.builder()
    // .movie(movieRepository.findById(movie.getId()).get())
    // .people(people)
    // .character(character)
    // .build();
    // moviePeopleRepository.save(moviePeople);
    // }

    // } catch (ParseException e) {
    // e.printStackTrace();
    // }
    // }
    // }

    // 영화 id 통해 배우 이름 얻기
    // @Transactional
    // @Test
    // public void getpeople() {
    // List<String> people = movieRepository.getpeople(645985L);
    // System.out.println(people);
    // }

    @Transactional
    @Test
    public void getGenresTest() {
        // List<Object[]> genres = movieRepository.getGenres(99L);
        // for (Object[] objects : genres) {
        // String title = (String) objects[0];
        // String releaseDate = (String) objects[1];
        // System.out.println(title);
        // System.out.println(releaseDate);
        // }
    }

    @Transactional
    @Test
    public void getGenres() {
        List<Genre> genres = genreRepository.findAll();
        for (Genre genre : genres) {
            System.out.println(genre.getId());
            System.out.println(genre.getName());
        }
    }

}
