package controller;
public class Conta {
	private int cod;
	private double val;
	private double sld;
	
	public int getConta() {
		return cod;
	}
	public void setConta(int conta) {
		this.cod = conta;
	}
	public double getValor() {
		return val;
	}
	public void setValor(double valor) {
		this.val = valor;
	}
	public double getSaldo() {
		return sld;
	}
	public void setSaldo(double saldo) {
		this.sld = saldo;
	}
}