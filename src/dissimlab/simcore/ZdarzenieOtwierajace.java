package dissimlab.simcore;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.TimeSimEventSemaphore;
import timeSemaphore.Otoczenie;
import timeSemaphore.Zgloszenie;

public class ZdarzenieOtwierajace extends BasicSimEvent<Otoczenie, Zgloszenie> {

    private TimeSimEventSemaphore semaphore;

    ZdarzenieOtwierajace(TimeSimEventSemaphore semaphore, double dt) throws SimControlException {
        super(dt);
        this.semaphore = semaphore;
    }

    ZdarzenieOtwierajace(Otoczenie parent, TimeSimEventSemaphore semaphore, double dt) throws SimControlException {
        super(parent, null, dt);
        this.semaphore = semaphore;
    }

    @Override
    protected void stateChange() throws SimControlException {
        System.out.println("Otwarto semafor. Liczba zdgłoszeń przed otwarciem: " +  semaphore.numberOfBlocked() + " ||| " + semaphore.getName());

        semaphore.open();

        System.out.println("Otwarto semafor. Liczba zdgłoszeń po otwarciu: " +      semaphore.numberOfBlocked() + " ||| " + semaphore.getName());
    }



    @Override
    protected void onTermination() { }

    @Override
    protected void onInterruption() { }

    @Override
    public Object getEventParams() {
        return null;
    }
}
