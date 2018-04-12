package timeSemaphore;

import dissimlab.random.SimGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimParameters;
import dissimlab.simcore.TimeSimEventSemaphore;

public class Zdarzenie extends BasicSimEvent<Otoczenie, Zgloszenie> {

    private TimeSimEventSemaphore semaphore;
    private boolean isReppeated;

    public Zdarzenie(TimeSimEventSemaphore semaphore, boolean isRepeated) throws SimControlException {
        super(semaphore.getDt());
        this.semaphore = semaphore;
        this.isReppeated = isRepeated;
    }

    @Override
    protected void stateChange() throws SimControlException {

        System.out.println("Otwarto semafor. Liczba zdgłoszeń przed otwarciem: " +  semaphore.getSizeOfSemafor() + " ||| " + semaphore.getName());
        semaphore.open();
        System.out.println("Otwarto semafor. Liczba zdgłoszeń po otwarciu: " +      semaphore.getSizeOfSemafor() + " ||| " + semaphore.getName());

        if(isReppeated)
            setRepetitionPeriod(semaphore.getDt());
    }

    @Override
    protected void onTermination() throws SimControlException { }

    @Override
    protected void onInterruption() throws SimControlException { }

    @Override
    public Object getEventParams() {
        return null;
    }
}
