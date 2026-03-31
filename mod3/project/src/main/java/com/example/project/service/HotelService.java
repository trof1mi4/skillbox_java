package com.example.project.service;

import com.example.project.exception.NotFoundException;
import com.example.project.model.Hotel;
import com.example.project.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {
    
    private final HotelRepository hotelRepository;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel getHotel(Long id) {
        return hotelRepository.findById(id).orElseThrow(() -> new NotFoundException("Отель не найден"));
    }

    public void saveHotel(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    public void deleteHotel(Long id) {
        if (!hotelRepository.existsById(id)) {
            throw new NotFoundException("Отель не найден");
        }
        hotelRepository.deleteById(id);
    }
} 