package com.driver.controllers;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Repository
public class HotelManagementRespository {
    // Using hashMap As a Databases
    HashMap<String, Hotel>hotelDB = new HashMap<>();
    HashMap<Integer, User> UserDB = new HashMap<>();
    HashMap<String, List<Facility>> hotelfDB = new HashMap<>(); //Facility in database of hotel

    HashMap<String, Booking> bookDB = new HashMap<>();
    HashMap<Integer,Integer>bookbyaadhar = new HashMap<>();

    public boolean addHotel(Hotel hotel){
        if(hotelDB.containsKey(hotel.getHotelName())){
            return false;
        }
        hotelDB.put(hotel.getHotelName(),hotel);

        List<Facility>ans = hotel.getFacilities();
        hotelfDB.put(hotel.getHotelName(),ans);
        return true;
    }
    public Integer addUser(User user){
        UserDB.put(user.getaadharCardNo(),user);
        return user.getaadharCardNo();
    }
    public String small(String a,String b){
        if (a.compareTo(b) == 0)
            return a;
        else if (a.compareTo(b)==       1) {
            return a;
        }else
            return b;
    }
    public String getHotelwithFacilities(){
        int max = 0;
        String hotelname = " ";
        for (String name :hotelfDB.keySet()){
            int ans = hotelfDB.get(name).size();
            if (ans>max){
                max = ans;
                hotelname = name;
            }
            else if (ans == max){
                return small(name,hotelname);
            }
        }
        return hotelname;

    }
    public int bookARoom(Booking booking){
        Hotel hotel = hotelDB.get(booking.getHotelName());
        int a = hotel.getAvailableRooms();
        int b = booking.getNoOfRooms();
        if (a > b){
            UUID uuid = UUID.randomUUID();
            String id = String.valueOf(uuid);
            int cash = hotel.getPricePerNight()*b;
            booking.setBookingId(id);
            booking.setAmountToBePaid(cash);
            bookDB.put(id,booking);

            bookbyaadhar.put(booking.getBookingAadharCard(),
                    bookbyaadhar.getOrDefault(booking.getBookingId(),0));
            return cash;
        }
        return -1;
    }
    public int getBookings(Integer aadharCard){
        if (bookbyaadhar.containsKey(aadharCard))
            return bookbyaadhar.get(aadharCard);

        return 0;
    }
    public Hotel updateFacilities(List<Facility>newFacilities,String hotelName){
        Hotel hotel = null;
        if (hotelfDB.containsKey(hotelName)){
            List<Facility>ans = hotelfDB.get(hotelName);
            for (Facility name : newFacilities){
                if(ans.contains(name))
                    continue;
                else
                    ans.add(name);
            }
            hotelfDB.put(hotelName,ans);
            hotelDB.get(hotelName).setFacilities(ans);
            hotel = hotelDB.get(hotelName);
        }
        return hotel;
    }
}
