package org.springframework.samples.petclinic.owner;

import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

public class PetInfoBean {
    private Integer id;
    private String name;
    private LocalDate birthDate;

    private PetType type;

    private Owner owner;
    private int port;

    public PetInfoBean() {

    }

    public PetInfoBean(Pet pet, int port) {
        BeanUtils.copyProperties(pet, this);
        this.port = port;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
