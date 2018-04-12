package timeSemaphore;

/**
 * @author Dariusz Pierzchala
 *
 * Description: Klasa główna. Tworzy dwa SMO, inicjalizuje je.Startuje symulację. Wyświetla statystyki.
 *
 * Wersja testowa.
 */

import dissimlab.simcore.SimControlEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;
import dissimlab.simcore.SimParameters.SimControlStatus;
import dissimlab.simcore.TimeSimEventSemaphore;

public class AppSMO {
	public static void main(String[] args) {
		try {
			SimManager model = SimManager.getInstance();

			//Semafor
			TimeSimEventSemaphore semaphore = new TimeSimEventSemaphore("Semafor czasowy.", 10, false);

			// Utworzenie otoczenia
			Otoczenie generatorZgl = new Otoczenie(semaphore);

			// Dwa sposoby zaplanowanego końca symulacji
			//model.setEndSimTime(10000);
			// lub
			SimControlEvent stopEvent = new SimControlEvent(50.0, SimControlStatus.STOPSIMULATION);
			// Uruchomienie symulacji za pośrednictwem metody "startSimulation" 
			model.startSimulation();

		} catch (SimControlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
