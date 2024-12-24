package com.example.project.service.reservation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.project.dto.reserve.ReserveDto;
import com.example.project.repository.reserve.ReserveRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReserveServiceImpl implements ReserveService {
    private final ReserveRepository reserveRepository;

    @Override
    public Long createReserve(ReserveDto reserveDto) {
        return reserveRepository.save(dtoToEntity(reserveDto)).getReserveId();
    }

    @Override
    public ReserveDto getReserve(Long reserveId) {
        return entityToDto(reserveRepository.findById(reserveId).get());
    }

    @Override
    public List<ReserveDto> getAllReserves() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllReserves'");
    }

    @Override
    public ReserveDto updateReserve(Long reserveId, ReserveDto reserveDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateReserve'");
    }

    @Override
    public void deleteReserve(Long reserveId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteReserve'");
    }

}
