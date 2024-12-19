package com.example.project.repository.movie;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.project.entity.Movie;
import com.example.project.entity.QMovie;
import com.example.project.entity.QMovieGenre;
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

    @Override
    public Page<Movie> getTotalList(String type, String keyword, String movieList, Long genreId, Pageable pageable) {
        QMovie movie = QMovie.movie;
        QMovieGenre movieGenre = QMovieGenre.movieGenre;

        JPQLQuery<Movie> query = from(movie).leftJoin(movieGenre).on(movie.id.eq(movieGenre.movie.id));
        if (genreId == null) {
            query.distinct();
        }

        // 기본 조건: movie.id > 0
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(movie.id.gt(0L));

        // genreId가 null이 아닌 경우에만 장르 조건 추가
        if (genreId != null) {
            builder.and(movieGenre.genre.id.eq(genreId)); // 장르 ID 조건
        }
        // if ("m".equals(type) && keyword != null && !keyword.isEmpty()) {
        builder.and(movie.title.like("%" + keyword + "%")); // 제목에 keyword가 포함된 영화 검색
        // }
        if ("nowPlaying".equals(movieList)) {
            System.out.println("nowPlaying");
            builder.and(
                    movie.releaseDate.between(
                            LocalDate.now().minusWeeks(3).toString(), LocalDate.now().toString()));
        } else if ("upcoming".equals(movieList)) {
            builder.and(movie.releaseDate.gt(LocalDate.now().toString()));
        }

        query.where(builder); // 조건을 모두 적용

        // Sort
        Sort sort = pageable.getSort();
        sort.stream().forEach(order -> {
            // com.querydsl.core.types.Order
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();
            // PathBuilder : Sort 객체 속성 - bno or title 이런 것들 지정
            PathBuilder<Movie> orderByExpression = new PathBuilder<>(Movie.class, "movie");
            // Sort 객체 사용 불가로 OrderSpecifier() 사용
            // com.querydsl.core.types.OrderSpecifier.OrderSpecifier(Order order, Expression
            // target)
            query.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });

        // 페이지네이션
        query.offset(pageable.getOffset());
        query.limit(pageable.getPageSize());

        // 결과 및 전체 개수 조회
        List<Movie> result = query.fetch(); // Movie 객체만 가져옵니다
        long count = query.fetchCount(); // 전체 데이터 개수

        // 결과를 Movie로 반환
        return new PageImpl<>(result, pageable, count);
    }

}
