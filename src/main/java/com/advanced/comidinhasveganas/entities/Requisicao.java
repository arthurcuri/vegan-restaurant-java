package com.advanced.comidinhasveganas.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_requisicoes")
public class Requisicao {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;

  private Integer quantidadePessoas;

  @ManyToOne
  @JoinColumn(name = "mesa_id")
  private Mesa mesa = null;

  private Boolean isAtendida = false;

  private Boolean isFinalizada = false;

  private LocalDateTime dataHoraInicio = null;

  private LocalDateTime dataHoraFim = null;

  private Double totalConta = 0.0;

  private Double totalPorPessoa = 0.0;

  @OneToMany
  @JoinColumn(name = "requisicao_id")
  private List<Pedido> pedidos;

  public Requisicao() {
  }

  public Requisicao(Cliente cliente, Integer quantidadePessoas) {
    setCliente(cliente);
    setQuantidadePessoas(quantidadePessoas);
  }

  public Long getId() {
    return id;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public Integer getQuantidadePessoas() {
    return quantidadePessoas;
  }

  public void setQuantidadePessoas(Integer quantidadePessoas) {
    this.quantidadePessoas = quantidadePessoas;
  }

  public Mesa getMesa() {
    return mesa;
  }

  public void setMesa(Mesa mesa) {
    this.mesa = mesa;
  }

  public Boolean getIsAtendida() {
    return isAtendida;
  }

  public void setIsAtendida(Boolean isAtendida) {
    this.isAtendida = isAtendida;
  }

  public void setAtendida() {
    this.isAtendida = true;
  }

  public Boolean getIsFinalizada() {
    return isFinalizada;
  }

  public void setIsFinalizada(Boolean isFinalizada) {
    this.isFinalizada = isFinalizada;
  }

  public void setFinalizada() {
    this.isFinalizada = true;
  }

  public LocalDateTime getDataHoraInicio() {
    return dataHoraInicio;
  }

  public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
    this.dataHoraInicio = dataHoraInicio;
  }

  public void setDataHoraInicioLocal() {
    this.dataHoraInicio = LocalDateTime.now();
  }

  public LocalDateTime getDataHoraFim() {
    return dataHoraFim;
  }

  public void setDataHoraFim(LocalDateTime dataHoraFim) {
    this.dataHoraFim = dataHoraFim;
  }

  public void setDataHoraFimLocal() {
    this.dataHoraFim = LocalDateTime.now();
  }

  public Double getTotalConta() {
    return totalConta;
  }

  public void setTotalConta(Double totalConta) {
    this.totalConta = totalConta;
  }

  public Double getTotalPorPessoa() {
    return totalPorPessoa;
  }

  public void setTotalPorPessoa(Double totalPorPessoa) {
    this.totalPorPessoa = totalPorPessoa;
  }

  public List<Pedido> getPedidos() {
    return pedidos;
  }

  public void setPedidos(List<Pedido> pedidos) {
    this.pedidos = pedidos;
  }

  public void addPedido(Pedido pedido) {
    pedidos.add(pedido);
  }

  public void removePedido(Pedido pedido) {
    pedidos.remove(pedido);
  }

  public void iniciarRequisicao(Mesa mesa) {
    setMesa(mesa);
    setDataHoraInicioLocal();
    setAtendida();
    mesa.ocupar();
  }

  public void finalizarRequisicao() {
    setDataHoraFimLocal();
    setFinalizada();
    mesa.desocupar();
    calcularTotais();
  }

  public void calcularTotais() {
    Double tc = pedidos.stream().mapToDouble(Pedido::getPrecoTotal).sum() * 1.1;

    Double tp = tc / getQuantidadePessoas();

    setTotalConta(tc);
    setTotalPorPessoa(tp);
  }

  public void cancelarRequisicao() {
    isFinalizada = true;
  }

  @Override
  public String toString() {
    return String.format(
        "Requisicao [id=%d, cliente=%s, quantidadePessoas=%d, mesa=%s, isAtendida=%s, isFinalizada=%s, dataHoraInicio=%s, dataHoraFim=%s, totalConta=%.2f, totalPorPessoa=%.2f, pedidos=%s]",
        id, cliente, quantidadePessoas, mesa, isAtendida, isFinalizada, dataHoraInicio, dataHoraFim, totalConta,
        totalPorPessoa, pedidos);
  }

}
