package com.advanced.comidinhasveganas.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_mesas")
public class Mesa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Integer lugares;

  private Boolean isOcupada = false;

  public Mesa() {
  }

  public Mesa(Integer lugares) {
    setLugares(lugares);
  }

  public Long getId() {
    return id;
  }

  public Integer getLugares() {
    return lugares;
  }

  public Boolean getIsOcupada() {
    return isOcupada;
  }

  public void setLugares(Integer lugares) {
    this.lugares = lugares;
  }

  public void setIsOcupada(Boolean isOcupada) {
    this.isOcupada = isOcupada;
  }

  public void ocupar() {
    this.isOcupada = true;
  }

  public void desocupar() {
    this.isOcupada = false;
  }

  public Boolean cabe(Integer quantidadePessoas) {
    return lugares >= quantidadePessoas;
  }

  @Override
  public String toString() {
    return "Mesa [id=" + id + ", lugares=" + lugares + ", isOcupada=" + isOcupada + "]";
  }

}
