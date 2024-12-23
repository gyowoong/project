package com.example.project.admin.service.test;

import java.util.List;

import com.example.project.admin.Entity.MovieState;
import com.example.project.dto.MovieDetailsDTO;
import com.example.project.dto.MovieDto;
import com.example.project.dto.PageRequestDTO;
import com.example.project.dto.PageResultDTO;
import com.example.project.dto.reserve.TheaterDto;
import com.example.project.admin.dto.test.MovieStateDto;
import com.example.project.admin.dto.test.UserDto;
import com.example.project.entity.Genre;
import com.example.project.entity.Movie;
import com.example.project.entity.test.UserEntity;

public interface UserServie {
    // 회원 정보 리스트
    List<UserEntity> allList(UserDto userDto);

    // 영화 정보 리스트
    // List<MovieDetailsDTO> getMovieDetails();

    // 영화관 정보 리스트
    // List<MovieAddDto> addList();

    // 영화관 지역선택 또는 영화관명 검색으로 리스트 출력
    List<TheaterDto> selectList(String state, String theaterName);

    // 지역 select 리스트
    List<MovieStateDto> getAllStates();

    // 영화관 등록
    Long addMovie(TheaterDto aDto);

    // 영화관 삭제
    void delete(Long theaterId);

    default UserEntity dtoToEntity(UserDto dto) {
        return UserEntity.builder()
                .userid(dto.getUserid())
                .password(dto.getPassword())
                .name(dto.getName())
                .addr(dto.getAddr())
                .telNo(dto.getTelNo())
                .email(dto.getEmail())
                .point(dto.getPoint())
                .reser(dto.isReser())
                .gender(dto.getGender())
                .age(dto.getAge())
                .brith(dto.getBrith())
                .build();
    }

    default UserDto entityToDto(UserEntity entity) {
        return UserDto.builder()
                .userid(entity.getUserid())
                .password(entity.getPassword())
                .name(entity.getName())
                .addr(entity.getAddr())
                .telNo(entity.getTelNo())
                .email(entity.getEmail())
                .point(entity.getPoint())
                .reser(entity.isReser())
                .gender(entity.getGender())
                .age(entity.getAge())
                .brith(entity.getBrith())
                .build();
    }

}
