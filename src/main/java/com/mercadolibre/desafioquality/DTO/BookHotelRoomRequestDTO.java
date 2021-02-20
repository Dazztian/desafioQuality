package com.mercadolibre.desafioquality.DTO;

import java.util.Date;
import java.util.List;

public class BookHotelRoomRequestDTO {

    private Date dateFrom;
    private Date dateTo;
    private String destination;
    private String hotelCode;
    private String peopleAmount;
    private String roomType;
    private List<PeopleDTO> peopleList;
    private PaymentMethodDTO paymentMethodDTO;

    public BookHotelRoomRequestDTO(Date dateFrom, Date dateTo, String destination, String hotelCode, String peopleAmount, String roomType, List<PeopleDTO> peopleList, PaymentMethodDTO paymentMethodDTO) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.destination = destination;
        this.hotelCode = hotelCode;
        this.peopleAmount = peopleAmount;
        this.roomType = roomType;
        this.peopleList = peopleList;
        this.paymentMethodDTO = paymentMethodDTO;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public String getPeopleAmount() {
        return peopleAmount;
    }

    public void setPeopleAmount(String peopleAmount) {
        this.peopleAmount = peopleAmount;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public List<PeopleDTO> getPeopleList() {
        return peopleList;
    }

    public void setPeopleList(List<PeopleDTO> peopleList) {
        this.peopleList = peopleList;
    }

    public PaymentMethodDTO getPaymentMethodDTO() {
        return paymentMethodDTO;
    }

    public void setPaymentMethodDTO(PaymentMethodDTO paymentMethodDTO) {
        this.paymentMethodDTO = paymentMethodDTO;
    }
}
