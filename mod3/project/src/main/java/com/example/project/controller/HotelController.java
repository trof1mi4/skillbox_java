package com.example.project.controller;

import com.example.project.model.Hotel;
import com.example.project.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("")
    public String getAllHotels(Model model) {
        model.addAttribute("hotels", hotelService.getAllHotels());
        model.addAttribute("newHotel", new Hotel());
        return "hotels";
    }

    @PostMapping("/api/v1/hotel")
    public String createHotel(@ModelAttribute("newHotel") Hotel hotel) {
        hotelService.saveHotel(hotel);
        return "redirect:/";
    }

    @GetMapping("/api/v1/hotel/{id}")
    public String getHotel(@PathVariable Long id, Model model) {
        Hotel hotel = hotelService.getHotel(id);
        model.addAttribute("hotel", hotel);
        return "hotel";
    }

    @ResponseBody
    @PutMapping("/api/v1/hotel/{id}")
    public void editHotel(@PathVariable Long id, @RequestBody Hotel hotel) {
        hotel.setId(id);
        hotelService.saveHotel(hotel);
    }

    @DeleteMapping("/api/v1/hotel/{id}")
    public String deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return "redirect:/";
    }
}
