package com.example.project.admin.service.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.project.admin.Entity.MovieAdd;
import com.example.project.admin.dto.test.MovieAddDto;
import com.example.project.admin.dto.test.UserDto;
import com.example.project.admin.repository.AdminMovieRepository;
import com.example.project.admin.repository.MovieAddRepository;
import com.example.project.dto.MovieDetailsDTO;
import com.example.project.entity.Movie;
import com.example.project.entity.test.UserEntity;
import com.example.project.repository.test.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Service
public class UserServiecImpl implements UserServie{

    private final UserRepository userRepository;
    private final AdminMovieRepository adminMovieRepository;
    private final MovieAddRepository movieAddRepository;


    @Override
    public List<UserEntity> allList(UserDto userDto) {
        List<UserEntity> list = userRepository.findAll();
     return list;
    }

    // @Transactional
    // @Override
    // public List<MovieDto> getMovieDetailsWithGenres(Long id) {
    //     // JPQL 쿼리 호출
    //     List<Object[]> results = movieRepository.getMovieDetailsWithGenres(id);

    //     // 결과를 저장할 리스트
    //     List<MovieDto> details = new ArrayList<>();

    //     for (Object[] result : results) {
    //         Movie movie = (Movie) result[0];       // Movie 엔티티
    //         Genre genre = (Genre) result[1];       // Genre 엔티티

    //         // details.add(new MovieDetailsDTO(movie.getTitle(), movie.getReleaseDate(), genre.getName()));
    //     }

    //     return details; // 리스트 반환
    // }

    @Transactional
    @Override
    public List<MovieDetailsDTO> getMovieDetails() {
        List<Object[]> results = adminMovieRepository.getMovieDetails();
        List<MovieDetailsDTO> movieDetailsList = new ArrayList<>();

        for (Object[] result : results) {
            String title = (String) result[0];
            String genres = (String) result[1];
            String releaseDate = (String) result[2];
            
            movieDetailsList.add(new MovieDetailsDTO(title, releaseDate,genres));
        }

        return movieDetailsList;
    }

    @Override
    public List<MovieAddDto> addList() {
        List<Object[]> addList = movieAddRepository.findByAddList();
        List<MovieAddDto> movieAdds = new ArrayList<>();
        
        for (Object[] objects : addList) {
            String state = (String) objects[0];
            String name = (String) objects[1];
            String add = (String) objects[2];
            log.info("add :",add);
            movieAdds.add(new MovieAddDto(name, add, state));
        }
        log.info("영화관 상세 " ,movieAdds);

        return movieAdds;
    }

    @Override
    public List<MovieAddDto> selectList(String state, String name) {
        List<Object[]> addList = movieAddRepository.stateAndName(state,name);
        List<MovieAddDto> movieAdds = new ArrayList<>();

        
        for (Object[] objects : addList) {
            String tstate = (String) objects[0];
            String tname = (String) objects[1];
            String add = (String) objects[2];
            String manager = (String) objects[3];
           
            movieAdds.add(new MovieAddDto(tname, add,manager, tstate));
            
        }
        

        return movieAdds;
    }

    @Override
    public List<String> getAllStates() {
        return movieAddRepository.findAllStates();
    }
    
}

    
    

