package timeSemaphore;

import dissimlab.simcore.*;
import dissimlab.simcore.SimParameters.SimControlStatus;

public class AppSMO {
	public static void main(String[] args) {
		try {
			SimManager model = SimManager.getInstance();

			// Utworzenie otoczenia
			Otoczenie otoczenie = new Otoczenie();
			TimeSimEventSemaphore semaphore = new TimeSimEventSemaphore("Multisemafor czasowy.", 25);
			otoczenie.setSemaphore(semaphore);

			SimControlEvent stopEvent = new SimControlEvent(50, SimControlStatus.STOPSIMULATION);
			model.startSimulation();
		} catch (SimControlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
