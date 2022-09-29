package Controller;

import java.util.concurrent.Semaphore;

public class ThreadCozinhando extends Thread {
	int i;
	Semaphore semaforo;

	public ThreadCozinhando(int i, Semaphore semaforo) {
		this.i = i;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		if (i % 2 == 0) {
			cozinhado((int) (Math.random() * 6) + 7);
		} else {
			cozinhado((int) (Math.random() * 5) + 4);
		}
		ePrato();
	}

	private void ePrato() {
		try {
			semaforo.acquire();
			System.out.println("🍽 Pedido #" + getId() + " fazer a entrega.\n");
			sleep(500);
		} catch (InterruptedException e) {
			System.out.println(e);
		} finally {
			System.out.println("*** 🍜Pedido #" + getId() + " Prato entregue *** \n");
			semaforo.release();
		}

	}

	public void cozinhado(int d) {
		int tTotal = (d * 100);
		int eAtual = 0;

		try {
			sleep(tTotal);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		while (eAtual < tTotal) {
			System.out.println("🍽 Pedido #" + getId() + "\n" + (tTotal - eAtual) + "% para finalizar\n");
			eAtual += 100;
		}
		System.out.println("*** 🍜 Pedido #" + getId() + " Prato entregue ***\n");
	}
}
