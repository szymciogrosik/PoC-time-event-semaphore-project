package timeSemaphore;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;

/**
 * Description: Klasa zgloszenia obsługiwanego w gnieździe obsługi.
 * 
 * @author Dariusz Pierzchala
 */

public class Zgloszenie extends BasicSimObj
{
    private static int nr = 0;
    private int tenNr;

	public Zgloszenie() {
        setTenNr();
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

	public void setTenNr() {
		this.tenNr = nr++;
	}

	public int getTenNr() {
		return tenNr;
	}
}