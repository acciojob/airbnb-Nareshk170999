package com.driver.controllers;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import io.swagger.models.License;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelManagementService {
    HotelManagementRespository hotelManagementRespository = new HotelManagementRespository();

  public String addHotel(Hotel hotel){
      if (hotel.getHotelName()==null || hotel == null)
          return "FAILURE";
      if (hotelManagementRespository.addHotel(hotel))
          return "SUCCESS";


          return "FAILURE";
  }

  public Integer addUser(User user){
      return hotelManagementRespository.addUser(user);
  }
  public String getHotelWithMostFacilities(){
      return hotelManagementRespository.getHotelwithFacilities();
  }
  public int bookARoom(Booking booking){
      return hotelManagementRespository.bookARoom(booking);
  }
  public  int getBookings(Integer aadharCard){
      return hotelManagementRespository.getBookings(aadharCard);
  }
  public Hotel updateFacilities(List<Facility> newFacilities, String hotelName){
      return hotelManagementRespository.updateFacilities(newFacilities,hotelName);
  }
}
