package dissimlab.simcore;

public class EventSelectAndFree extends BasicSimEvent<BasicSimObj, Object> {

    private TimeSimEventSemaphore semaphore;

    EventSelectAndFree(TimeSimEventSemaphore semaphore, double dt) throws SimControlException {
        super(dt);
        this.setId();
        this.semaphore = semaphore;
        semaphore.setEventSelectAndFree(this);
    }

    @Override
    protected void stateChange() throws SimControlException {
        BasicSimEvent<BasicSimObj, Object> removeObj = semaphore.removeFirst();
        System.out.println(simTime()+" - "+simDate(SimParameters.SimDateField.HOUR24)+" - "+simDate(SimParameters.SimDateField.MINUTE)+" - "+simDate(SimParameters.SimDateField.SECOND)+" - "+simDate(SimParameters.SimDateField.MILLISECOND)+": Otwarto semafor." + " Zwolnionego zdarzenie o id: " + removeObj.getId() + ". Zdarzenie miało być zwolnione w chwili: " +  removeObj.getRunTime());

        if(semaphore.sizeList() > 0) {
            double newRunTime = semaphore.getFirst().getRunTime() - SimManager.getInstance().getCommonSimContext().simTime();
            new EventSelectAndFree(semaphore, newRunTime);
        }
    }

    @Override
    protected void onTermination() {

    }

    @Override
    protected void onInterruption() {

    }

    @Override
    public Object getEventParams() {
        return null;
    }
}
