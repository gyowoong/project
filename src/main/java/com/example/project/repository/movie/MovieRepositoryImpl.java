package com.example.project.repository.movie;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.project.entity.Genre;
import com.example.project.entity.Movie;
import com.example.project.entity.MoviePerson;
import com.example.project.entity.Person;
import com.example.project.entity.QGenre;
import com.example.project.entity.QMovie;
import com.example.project.entity.QMovieGenre;
import com.example.project.entity.QMoviePerson;
import com.example.project.entity.QPerson;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MovieRepositoryImpl extends QuerydslRepositorySupport implements MovieCustomRepository {

    public MovieRepositoryImpl() {
        super(Movie.class);
    }

    // @Override
    // public Page<Movie> getTotalList(String type, String keyword, String
    // movieList, Long genreId, Pageable pageable) {
    // QMovie movie = QMovie.movie;
    // QMovieGenre movieGenre = QMovieGenre.movieGenre;

    // JPQLQuery<Movie> query =
    // from(movie).leftJoin(movieGenre).on(movie.id.eq(movieGenre.movie.id));
    // if (genreId == null) {
    // query.distinct();
    // }

    // // 기본 조건: movie.id > 0
    // BooleanBuilder builder = new BooleanBuilder();
    // builder.and(movie.id.gt(0L));

    // // genreId가 null이 아닌 경우에만 장르 조건 추가
    // if (genreId != null) {
    // builder.and(movieGenre.genre.id.eq(genreId)); // 장르 ID 조건
    // }
    // // if ("m".equals(type) && keyword != null && !keyword.isEmpty()) {
    // builder.and(movie.title.like("%" + keyword + "%")); // 제목에 keyword가 포함된 영화 검색
    // // }
    // if ("nowPlaying".equals(movieList)) {
    // System.out.println("nowPlaying");
    // builder.and(
    // movie.releaseDate.between(
    // LocalDate.now().minusWeeks(3).toString(), LocalDate.now().toString()));
    // } else if ("upcoming".equals(movieList)) {
    // builder.and(movie.releaseDate.gt(LocalDate.now().toString()));
    // }

    // query.where(builder); // 조건을 모두 적용

    // // Sort
    // Sort sort = pageable.getSort();
    // sort.stream().forEach(order -> {
    // // com.querydsl.core.types.Order
    // Order direction = order.isAscending() ? Order.ASC : Order.DESC;
    // String prop = order.getProperty();
    // // PathBuilder : Sort 객체 속성 - bno or title 이런 것들 지정
    // PathBuilder<Movie> orderByExpression = new PathBuilder<>(Movie.class,
    // "movie");
    // // Sort 객체 사용 불가로 OrderSpecifier() 사용
    // // com.querydsl.core.types.OrderSpecifier.OrderSpecifier(Order order,
    // Expression
    // // target)
    // query.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
    // });

    // // 페이지네이션
    // query.offset(pageable.getOffset());
    // query.limit(pageable.getPageSize());

    // // 결과 및 전체 개수 조회
    // List<Movie> result = query.fetch(); // Movie 객체만 가져옵니다
    // long count = query.fetchCount(); // 전체 데이터 개수

    // // 결과를 Movie로 반환
    // return new PageImpl<>(result, pageable, count);
    // }

    // 영화 리스트 불러오기
    @Override
    public Page<Object[]> getTotalList(String type, String keyword, String movieList, Long genreId,
            Pageable pageable) {
        QMovie movie = QMovie.movie;
        QMoviePerson moviePerson = QMoviePerson.moviePerson;
        QPerson person = QPerson.person;
        QMovieGenre movieGenre = QMovieGenre.movieGenre;
        QGenre genre = QGenre.genre;

        // 기본 Query 구성
        JPQLQuery<Movie> query = from(movie)
                .leftJoin(moviePerson).on(movie.id.eq(moviePerson.movie.id))
                .leftJoin(person).on(moviePerson.person.id.eq(person.id))
                .leftJoin(movieGenre).on(movie.id.eq(movieGenre.movie.id))
                .leftJoin(genre).on(movieGenre.genre.id.eq(genre.id));

        // 조건 설정
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(movie.id.gt(0L));

        // 장르 필터 추가
        if (genreId != null) {
            builder.and(movieGenre.genre.id.eq(genreId));
        }

        // 제목 키워드 필터 추가
        if (keyword != null && !keyword.trim().isEmpty()) {
            builder.and(movie.title.like("%" + keyword + "%"));
        }

        // 영화 상태 필터 추가 (상영 중 또는 예정)
        if ("nowPlaying".equals(movieList)) {
            builder.and(
                    movie.releaseDate.between(
                            LocalDate.now().minusWeeks(3).toString(),
                            LocalDate.now().toString()));
        } else if ("upcoming".equals(movieList)) {
            builder.and(movie.releaseDate.gt(LocalDate.now().toString()));
        }

        // 조건 적용
        query.where(builder);

        // 정렬 적용
        Sort sort = pageable.getSort();
        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();
            PathBuilder<Movie> orderByExpression = new PathBuilder<>(Movie.class, "movie");
            query.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });

        // 페이지네이션 설정
        query.offset(pageable.getOffset());
        query.limit(pageable.getPageSize());

        // 데이터 조회
        List<Movie> movies = query.distinct().fetch();
        long total = query.fetchCount();

        // 영화 세부 정보를 생성
        List<Object[]> results = movies.stream().map(movieResult -> {
            Long movieId = movieResult.getId();

            // 관련 데이터 수집
            List<MoviePerson> moviePersonList = from(moviePerson)
                    .where(moviePerson.movie.id.eq(movieId))
                    .fetch();

            List<Person> personList = from(person)
                    .join(moviePerson).on(moviePerson.person.id.eq(person.id))
                    .where(moviePerson.movie.id.eq(movieId))
                    .fetch();

            List<Genre> genreList = from(genre)
                    .join(movieGenre).on(movieGenre.genre.id.eq(genre.id))
                    .where(movieGenre.movie.id.eq(movieId))
                    .fetch();

            // Object[]에 담기
            return new Object[] { movieResult, moviePersonList, personList, genreList };
        }).collect(Collectors.toList());

        // 결과를 Page로 반환
        return new PageImpl<>(results, pageable, total);
    }

    // @Override
    // public List<String> getDirectorList(Long id) {
    // QMovie movie = QMovie.movie;
    // QMoviePerson moviePerson = QMoviePerson.moviePerson;
    // QPerson person = QPerson.person;

    // JPQLQuery<String> query =
    // from(movie).leftJoin(moviePerson).on(movie.id.eq(moviePerson.movie.id))
    // .leftJoin(moviePerson).on(moviePerson.person.id.eq(person.id)).select(person.name).distinct();

    // // 기본 조건: movie.id > 0
    // BooleanBuilder builder = new BooleanBuilder();
    // builder.and(movie.id.gt(0L));

    // builder.and(moviePerson.movie.id.eq(id)).and(moviePerson.role.eq("Director"));

    // query.where(builder);

    // List<String> result = query.fetch();

    // return result;
    // }

    // @Override
    // public List<String> getActorList(Long id) {
    // QMovie movie = QMovie.movie;
    // QMoviePerson moviePerson = QMoviePerson.moviePerson;
    // QPerson person = QPerson.person;

    // JPQLQuery<String> query =
    // from(movie).leftJoin(moviePerson).on(movie.id.eq(moviePerson.movie.id))
    // .leftJoin(moviePerson).on(moviePerson.person.id.eq(person.id)).select(person.name)
    // .orderBy(person.popularity.desc());

    // // 기본 조건: movie.id > 0
    // BooleanBuilder builder = new BooleanBuilder();
    // builder.and(movie.id.gt(0L));

    // builder.and(moviePerson.movie.id.eq(id)).and(moviePerson.character.isNotNull());

    // query.where(builder);

    // List<String> result = query.fetch();

    // return result;
    // }

    // @Override
    // public List<String> getGenreList(Long id) {
    // QMovie movie = QMovie.movie;
    // QMovieGenre movieGenre = QMovieGenre.movieGenre;
    // QGenre genre = QGenre.genre;

    // JPQLQuery<String> query =
    // from(movie).leftJoin(movieGenre).on(movie.id.eq(movieGenre.movie.id))
    // .leftJoin(movieGenre).on(movieGenre.genre.id.eq(genre.id)).select(genre.name);

    // // 기본 조건: movie.id > 0
    // BooleanBuilder builder = new BooleanBuilder();
    // builder.and(movie.id.gt(0L));

    // builder.and(movieGenre.movie.id.eq(id));

    // query.where(builder);

    // List<String> result = query.fetch();

    // return result;
    // }

    // 인물 id로 영화 리스트 불러오기
    @Override
    public List<Movie> getMovieListByPersonId(Long id) {
        QMovie movie = QMovie.movie;
        QMoviePerson moviePerson = QMoviePerson.moviePerson;
        QPerson person = QPerson.person;

        JPQLQuery<Movie> query = from(movie).leftJoin(moviePerson).on(movie.id.eq(moviePerson.movie.id))
                .leftJoin(moviePerson).on(moviePerson.person.id.eq(person.id));

        // 기본 조건: movie.id > 0
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(movie.id.gt(0L));

        builder.and(person.id.eq(id));

        query.where(builder);

        List<Movie> result = query.fetch();

        return result;
    }

    // 영화 상세 정보 불러오기
    @Override
    public Object[] getMovieDetailById(Long id) {
        QMovie movie = QMovie.movie;
        QMoviePerson moviePerson = QMoviePerson.moviePerson;
        QPerson person = QPerson.person;
        QMovieGenre movieGenre = QMovieGenre.movieGenre;
        QGenre genre = QGenre.genre;

        // Fetch Movie
        Movie movieResult = from(movie)
                .where(movie.id.eq(id))
                .fetchOne();

        // Fetch moviePerson
        List<MoviePerson> moviePersonList = from(moviePerson)
                .where(moviePerson.movie.id.eq(id))
                .fetch();

        // Fetch person
        List<Person> personList = from(person)
                .join(moviePerson).on(moviePerson.person.id.eq(person.id))
                .where(moviePerson.movie.id.eq(id))
                .fetch();

        // Fetch Genres
        List<Genre> genreList = from(genre)
                .join(movieGenre).on(movieGenre.genre.id.eq(genre.id))
                .where(movieGenre.movie.id.eq(id))
                .fetch();

        // Combine results into Object[]
        return new Object[] { movieResult, moviePersonList, personList, genreList };
    }

}
