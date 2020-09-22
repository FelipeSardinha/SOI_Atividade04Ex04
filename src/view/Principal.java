package view;
import java.util.concurrent.Semaphore;
import controller.Threads;
import controller.Conta;
public class Principal {
	public static void main(String[] args) {
		
		int cod = 0;
		int sld = 0;
		int val = 0;
		int cont = -1;
		Semaphore [] sqDep = new Semaphore[2];
		Conta [] conta = new Conta[20];
		
		for (int i = 0; i < 2; i ++) {
			sqDep[i] = new Semaphore(1);
		}
		
		for (int i = 0; i < 20; i ++) {
			conta[i] = new Conta();
			
			cod = (int)(Math.random() * 300) + 100;
			sld = (int)(Math.random() * 3000) + 1000;
			val = (int)(Math.random()* 500) - 100;
			if (val >= 0 ) {
				cont = 1;
			}
			else {
				cont = 0;
			}
			Thread transacao = new Threads (i, cod, sld, val, cont, sqDep, conta);
			transacao.start();
		}
	}
}