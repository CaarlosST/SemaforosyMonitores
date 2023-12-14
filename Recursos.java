import java.util.concurrent.Semaphore;

public class Recursos {

	public int recursos = 5;

	private Semaphore hayRecursoLibre = new Semaphore(1, true);
	private Semaphore hayRecursoOcupado = new Semaphore(0, true);

	public Semaphore getRecursosLibres() {
		return hayRecursoLibre;
	}

	public Semaphore getRecursosOcupados() {
		return hayRecursoOcupado;
	}

	public void reservar(int n, int proceso) {
		try {
			hayRecursoLibre.acquire();
			recursos = recursos - n;
			System.out.println("Proceso " + proceso + " ha reservado " + recursos + " recursos .");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		hayRecursoOcupado.release();

	}

	public void liberar(int n , int proceso) {

		try {

			hayRecursoOcupado.acquire();
			System.out.println("Numero de recursos liberados " + recursos + " del proceso "+proceso +" .");
			recursos = recursos + n;

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		hayRecursoLibre.release();
	}
}
