package dissimlab.simcore;

public class EventOpen extends BasicSimEvent<BasicSimObj, Object> {

    private TimeSimEventSemaphore semaphore;

    EventOpen(TimeSimEventSemaphore semaphore, double dt) throws SimControlException {
        super(dt);
        this.semaphore = semaphore;
    }

    @Override
    protected void stateChange() throws SimControlException {
        System.out.println(simTime()+" - "+simDate(SimParameters.SimDateField.HOUR24)+" - "+simDate(SimParameters.SimDateField.MINUTE)+" - "+simDate(SimParameters.SimDateField.SECOND)+" - "+simDate(SimParameters.SimDateField.MILLISECOND)+" Otwarto CALY semafor. Liczba zdgłoszeń przed otwarciem: " +  semaphore.numberOfBlocked() + " ||| " + semaphore.getName());

        semaphore.open();

        System.out.println(simTime()+" - "+simDate(SimParameters.SimDateField.HOUR24)+" - "+simDate(SimParameters.SimDateField.MINUTE)+" - "+simDate(SimParameters.SimDateField.SECOND)+" - "+simDate(SimParameters.SimDateField.MILLISECOND)+" Otwarto CALY semafor. Liczba zdgłoszeń po otwarciu: " +      semaphore.numberOfBlocked() + " ||| " + semaphore.getName());
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
