package View;

import java.util.concurrent.Semaphore;

import Controller.ThreadCozinhando;

public class MainThreadSemaforo {
	public static void main(String[] args) {

		Semaphore semaforo = new Semaphore(1);

		for (int i = 1; i <= 5; i++) {

			ThreadCozinhando cozinhando = new ThreadCozinhando(i, semaforo);
			cozinhando.start();
		}
	}
}