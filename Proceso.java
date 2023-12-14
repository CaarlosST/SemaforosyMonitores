import java.util.Random;

public class Proceso extends Thread {
	private Recursos recursos;
	private int id;

	Proceso(Recursos recursos, int id) {
		this.recursos = recursos;
		this.id = id;
	}

	public int rmdRecursos() {
		Random rmd = new Random();
		int rec = rmd.nextInt(4);
		return rec;
	}

	public void run() {
		while (true) {
			rmdRecursos()
			recursos.reservar(rmdRecursos(), id);
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			recursos.liberar(rmdRecursos(),id);
		}
	}

	public static void main(String[] args) {
		Recursos recursos = new Recursos();
		int numeroDeProces = 3 ;
		for (int i = 0; i < numeroDeProces; i++) {
			new Proceso(recursos, (i + 1)).start(); // Inicia el hilo Proceso con la instancia de Recursos
		}
	}
}
