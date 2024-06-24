package com.advanced.comidinhasveganas.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_clientes")
public class Cliente implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  private String telefone;

  public Cliente() {
  }

  public Cliente(String nome, String telefone) {
    setNome(nome);
    setTelefone(telefone);
  }

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  @Override
  public String toString() {
    return "Cliente [id=" + id + ", nome=" + nome + ", telefone=" + telefone + "]";
  }

}
