package timeSemaphore;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.TimeSimEventSemaphore;

public class Otoczenie extends BasicSimObj {
    private TimeSimEventSemaphore semaphore;

    public Otoczenie() throws SimControlException {
        // Rozpocznij zgłaszanie Zdarzeń za semafor
    	new Zglaszaj(this, 0.0);
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
