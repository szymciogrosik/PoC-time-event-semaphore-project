package timeSemaphore;

/**
 * @author Dariusz Pierzchala
 *
 * Description: Klasa główna. Tworzy dwa SMO, inicjalizuje je.Startuje symulację. Wyświetla statystyki.
 *
 * Wersja testowa.
 */

import dissimlab.simcore.*;
import dissimlab.simcore.SimParameters.SimControlStatus;

public class AppSMO {
	public static void main(String[] args) {
		try {
			SimManager model = SimManager.getInstance();

			// Utworzenie otoczenia
			Otoczenie generatorZgl = new Otoczenie();
			TimeSimEventSemaphore semaphore = new TimeSimEventSemaphore(generatorZgl, "Multisemafor czasowy.");
			generatorZgl.setSemaphore(semaphore);

			SimControlEvent stopEvent = new SimControlEvent(50, SimControlStatus.STOPSIMULATION);
			model.startSimulation();

		} catch (SimControlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
