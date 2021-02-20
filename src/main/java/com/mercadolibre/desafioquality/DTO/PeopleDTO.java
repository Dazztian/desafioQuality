package com.mercadolibre.desafioquality.DTO;

public class PeopleDTO {

    private String dni;
    private String name;
    private String lastname;
    private String birthdate;
    private String email;

    public PeopleDTO(String dni, String name, String lastname, String birthdate, String email) {
        this.dni = dni;
        this.name = name;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
