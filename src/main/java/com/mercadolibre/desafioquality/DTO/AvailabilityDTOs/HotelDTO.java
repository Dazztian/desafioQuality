package com.mercadolibre.desafioquality.DTO.AvailabilityDTOs;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.text.ParseException;
import java.util.Date;

public class HotelDTO {

    private String code;
    private String name;
    private String destination;
    private String roomType;
    private Integer price;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date disponibilityFrom;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date disponibilityUntil;
    private Boolean ocuppied;

    public HotelDTO(String code, String name, String destination, String roomType, Integer price, Date disponibilityFrom, Date disponibilityUntil, Boolean ocuppied) throws ParseException {
        this.code = code;
        this.name = name;
        this.destination = destination;
        this.roomType = roomType;
        this.price = price;
        this.disponibilityFrom = disponibilityFrom;
        this.disponibilityUntil = disponibilityUntil;
        this.ocuppied = ocuppied;
    }

    public HotelDTO() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getDisponibilityFrom() { return disponibilityFrom; }

    public void setDisponibilityFrom(Date disponibilityFrom) {
        this.disponibilityFrom = disponibilityFrom;
    }

    public Date getDisponibilityUntil() {
        return disponibilityUntil;
    }

    public void setDisponibilityUntil(Date disponibilityUntil) {
        this.disponibilityUntil = disponibilityUntil;
    }

    public Boolean getOcuppied() {
        return ocuppied;
    }

    public void setOcuppied(Boolean ocuppied) {
        this.ocuppied = ocuppied;
    }


}
