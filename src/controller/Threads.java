package controller;
import java.util.concurrent.Semaphore;
public class Threads extends Thread{
	private int sld;
	private int cod;
	private int val;
	private int cont;
	private Semaphore [] sqDep;
	private Conta conta[];
	private int pos;
	private static int transac;
	
	public Threads(int pos, int cod, int sld, int val, int cont, Semaphore sqDep[], Conta [] conta) {
		this.cod = cod;
		this.sld = sld;
		this.val = val;
		this.cont = cont;
		this.sqDep = sqDep;
		this.conta = conta;
		this.pos = pos;
	}
	
	public void run() {
		try {
			sqDep[cont].acquire();
			fazTransacao();
			transac ++;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			mostraResultado();
			sqDep[cont].release();	
		}
	}
	
	public void fazTransacao() {
		conta[pos].setConta(cod);
		conta[pos].setValor(val);
		conta[pos].setSaldo(sld);
		if (conta[pos].getValor() >= 0) {
			conta[pos].setSaldo(conta[pos].getValor() + conta[pos].getSaldo());
		}
		else {
			conta[pos].setSaldo(conta[pos].getSaldo() - conta[pos].getValor());	
		}
	}
	
	public void mostraResultado() {
		if (transac == 20) {
			for (int i = 0; i < 20; i ++) {
				if (conta[i].getValor() >= 0) {
				System.out.println("Transação realizada: Depósito");
				System.out.println("Conta: " + conta[i].getConta() + " | Saldo anterior R$ " + (conta[i].getSaldo() - conta[i].getValor()) + 
						" | Valor do depósito R$ " + conta[i].getValor() + " | Saldo atual R$ " + conta[i].getSaldo() + "\n");
				}
				else {
					conta[i].setValor(conta[i].getValor() * -1);
					System.out.println("Transação realizada: Saque");
					System.out.println("Conta: " + conta[i].getConta() + " | Saldo anterior R$ " + (conta[i].getSaldo() + conta[i].getValor()) + 
						" | Valor do saque R$ " + conta[i].getValor() + " | Saldo atual R$" + conta[i].getSaldo() +"\n");
				}
			}			
		}
	}
}