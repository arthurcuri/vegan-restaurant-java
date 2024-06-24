package com.advanced.comidinhasveganas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_itens_pedidos")
public class ItemPedido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "item_cardapio_id")
  @JsonIgnore
  private ItemCardapio itemCardapio;

  private Integer quantidade;

  public ItemPedido() {
  }

  public ItemPedido(ItemCardapio itemCardapio, Integer quantidade) {
    this.itemCardapio = itemCardapio;
    this.quantidade = quantidade;
  }

  public Long getId() {
    return id;
  }

  public ItemCardapio getItemCardapio() {
    return itemCardapio;
  }

  public void setItemCardapio(ItemCardapio itemCardapio) {
    this.itemCardapio = itemCardapio;
  }

  public Integer getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
  }

  @JsonIgnore
  public Double getSubTotal() {
    return itemCardapio.getPreco() * quantidade;
  }

  @Override
  public String toString() {
    return "ItemPedido [id=" + id + ", itemCardapio=" + itemCardapio + ", quantidade=" + quantidade + "]";
  }
}
