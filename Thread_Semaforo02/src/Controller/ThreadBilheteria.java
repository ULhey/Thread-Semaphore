package Controller;

import java.util.concurrent.Semaphore;

public class ThreadBilheteria extends Thread {

	static private int ingressos = 100;
	private int idPessoa;
	private Semaphore ingresso;

	public ThreadBilheteria(int idPessoa, Semaphore ingresso) {
		this.idPessoa = idPessoa;
		this.ingresso = ingresso;
	}

	@Override
	public void run() {
		try {
			int Compra = (int) (Math.random() * 3) + 2;
			
			boolean TempLogin = false;
			boolean LimitCompra = false;

			TempLogin = Login();
			if (TempLogin == false) {
				LimitCompra = Compra();
				if (LimitCompra == false) {
					ingresso.acquire();
					Ingresso(Compra);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			ingresso.release();
		}

	}

	private boolean Login() {

		int TempLogin = (int) (Math.random() * 1950) + 51;
		try {
			sleep(TempLogin);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (TempLogin > 1000) {
			System.err.println("*** Usuário #" + idPessoa + " 🏃‍♀️ Timeout ***\nSessão: " + TempLogin + "ms\n");
			return true;
		} else {
			System.out.println("Usuário #" + idPessoa + " 💻 Logou\n");
			return false;
		}

	}

	private boolean Compra() {

		int LimitCompra = (int) (Math.random() * 2000) + 1001;
		try {
			sleep(LimitCompra);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (LimitCompra > 2500) {
			System.out.println("*** Comprador #" + idPessoa + " 🏃‍♀️ Fim de sessão ***\n");
			return true;
		} else {
			System.out.println("Comprador #" + idPessoa + " 🙂 Compra realizada\n");
			return false;
		}

	}

	private void Ingresso(int QuantIngresso) { 
		if (QuantIngresso > ingressos) {
			System.err.println("Comprador #" + idPessoa + " ☹️ Igressos esgotados\n");
			System.exit(0);
		} else {
			ingressos -= QuantIngresso;
			System.out.println("Comprador #" + idPessoa + "\nForam: " + QuantIngresso + " 🎫 ingressos \nRestam: " + ingressos + "\n");
		}
	}
}