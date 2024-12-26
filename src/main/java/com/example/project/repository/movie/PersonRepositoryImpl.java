package com.example.project.repository.movie;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.project.entity.Movie;
import com.example.project.entity.Person;
import com.example.project.entity.QMovie;
import com.example.project.entity.QMoviePerson;
import com.example.project.entity.QPerson;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class PersonRepositoryImpl extends QuerydslRepositorySupport implements PersonCustomRepository {

    public PersonRepositoryImpl() {
        super(Person.class);
    }

    @Override
    public Page<Person> getTotalList(String type, String keyword, Pageable pageable) {
        QPerson person = QPerson.person;
        QMoviePerson movieperson = QMoviePerson.moviePerson;

        JPQLQuery<Person> query = from(person).leftJoin(movieperson).on(person.id.eq(movieperson.movie.id));

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(person.id.gt(0L));

        // if (type.contains("p") && keyword != null && !keyword.isEmpty()) {
        builder.and(person.name.like("%" + keyword + "%")); // 제목에 keyword가 포함된 인물 검색
        // }

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
        List<Person> result = query.fetch(); // Movie 객체만 가져옵니다
        long count = query.fetchCount(); // 전체 데이터 개수

        // 결과를 Movie로 반환
        return new PageImpl<>(result, pageable, count);
    }

    @Override
    public List<Person> getDirectorListByMovieId(Long id) {
        QPerson person = QPerson.person;
        QMoviePerson moviePerson = QMoviePerson.moviePerson;
        QMovie movie = QMovie.movie;

        JPQLQuery<Person> query = from(person).leftJoin(moviePerson).on(person.id.eq(moviePerson.person.id))
                .leftJoin(moviePerson).on(moviePerson.movie.id.eq(movie.id));

        // 기본 조건: movie.id > 0
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(movie.id.gt(0L));

        builder.and(movie.id.eq(id)).and(moviePerson.role.eq("Director"));

        query.where(builder);

        List<Person> result = query.fetch();

        return result;
    }

}
