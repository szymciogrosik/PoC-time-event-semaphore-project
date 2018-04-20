package timeSemaphore;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.TimeSimEventSemaphore;

public class ZdarzenieOtwierajace extends BasicSimEvent<Otoczenie, Zgloszenie> {

    private TimeSimEventSemaphore semaphore;
    private boolean isRepeatable;

    public ZdarzenieOtwierajace(TimeSimEventSemaphore semaphore, boolean isRepeated) throws SimControlException {
        super(semaphore.getDt());
        this.semaphore = semaphore;
        this.isRepeatable = isRepeated;
    }

    @Override
    protected void stateChange() throws SimControlException {

        System.out.println("Otwarto semafor. Liczba zdgłoszeń przed otwarciem: " +  semaphore.numberOfBlocked() + " ||| " + semaphore.getName());
        semaphore.open();
        System.out.println("Otwarto semafor. Liczba zdgłoszeń po otwarciu: " +      semaphore.numberOfBlocked() + " ||| " + semaphore.getName());

        if(isRepeatable)
            setRepetitionPeriod(semaphore.getDt());
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
