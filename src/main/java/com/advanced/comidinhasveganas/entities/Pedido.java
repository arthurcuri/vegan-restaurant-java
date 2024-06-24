package com.advanced.comidinhasveganas.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pedidos")
public class Pedido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany
  @JoinColumn(name = "pedido_id")
  private List<ItemPedido> itens = new ArrayList<>();

  private String tipoPedido;

  private double precoTotal;

  public Pedido() {
  }

  public Pedido(String tipoPedido) {
    setTipoPedido(tipoPedido);
  }

  public Long getId() {
    return id;
  }

  public List<ItemPedido> getItens() {
    return itens;
  }

  public String getTipoPedido() {
    return tipoPedido;
  }

  public void setTipoPedido(String tipoPedido) {
    this.tipoPedido = tipoPedido;
  }

  public double getPrecoTotal() {
    return precoTotal;
  }

  public void addItem(ItemPedido item) {
    itens.add(item);
  }

  public void addItens(List<ItemPedido> itens) {
    this.itens.addAll(itens);
  }

  public void setPrecoTotal() {
    if (tipoPedido.equalsIgnoreCase("normal")) {
      precoTotal = itens.stream().mapToDouble(ItemPedido::getSubTotal).sum();
    } else if (tipoPedido.equalsIgnoreCase("fechado")) {
      precoTotal = 32.0;
    } else {
      throw new IllegalArgumentException("Tipo de pedido desconhecido.");
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (ItemPedido item : itens) {
      sb.append(item.toString()).append("\n");
    }
    sb.append("Preço total: R$ ").append(getPrecoTotal());
    return sb.toString();
  }
}
