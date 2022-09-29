package View;

import java.util.concurrent.Semaphore;

import Controller.ThreadBilheteria;

public class MainThreadSemaforo {

	public static void main(String[] args) {

		Semaphore valideiro = new Semaphore(1);

		for (int i = 1; i <= 300; i++) { // Ativa 300 threads!!!!!! (compradores)

			ThreadBilheteria t = new ThreadBilheteria(i, valideiro);
			t.start();
			
		}
	}
}