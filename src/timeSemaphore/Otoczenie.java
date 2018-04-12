package timeSemaphore;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.TimeSimEventSemaphore;

import java.util.concurrent.Semaphore;

public class Otoczenie extends BasicSimObj {
    private Zglaszaj zglaszaj;
    private TimeSimEventSemaphore semaphore;

    public Otoczenie(TimeSimEventSemaphore semaphore) throws SimControlException {
    	// Przypisywanie semafora
		this.semaphore = semaphore;
        // Powołanie instancji pierwszego zdarzenia
    	zglaszaj = new Zglaszaj(this, 0.0);
	}

	@Override
	public void reflect(IPublisher publisher, INotificationEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean filter(IPublisher publisher, INotificationEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	public TimeSimEventSemaphore getSemaphore() {
		return semaphore;
	}

	public void setSemaphore(TimeSimEventSemaphore semaphore) {
		this.semaphore = semaphore;
	}
}
