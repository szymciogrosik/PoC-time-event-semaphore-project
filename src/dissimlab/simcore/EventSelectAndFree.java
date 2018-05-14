package dissimlab.simcore;

import timeSemaphore.Otoczenie;

public class EventSelectAndFree extends BasicSimEvent<Otoczenie, Object> {

    private Otoczenie otoczenie;
    private static int nr = 0;
    private int tenNr;

    EventSelectAndFree(Otoczenie otoczenie, double dt) throws SimControlException {
        super(dt);
        this.otoczenie = otoczenie;
        this.setTenNr();
        this.setId(this.getTenNr());
        otoczenie.getSemaphore().setEventSelectAndFree(this);
    }

    @Override
    protected void stateChange() throws SimControlException {
        BasicSimEvent<BasicSimObj, Object> removeObj = otoczenie.getSemaphore().removeFirst();
        System.out.println(simTime()+" - "+simDate(SimParameters.SimDateField.HOUR24)+" - "+simDate(SimParameters.SimDateField.MINUTE)+" - "+simDate(SimParameters.SimDateField.SECOND)+" - "+simDate(SimParameters.SimDateField.MILLISECOND)+": Otwarto semafor." + " Zwolnionego zdarzenie o id: " + removeObj.getId() + ". Zdarzenie miało być zwolnione w chwili: " +  removeObj.getRunTime());

        if(otoczenie.getSemaphore().sizeList() > 0) {
            double newRunTime = otoczenie.getSemaphore().getFirst().getRunTime() - SimManager.getInstance().getCommonSimContext().simTime();
            new EventSelectAndFree(otoczenie, newRunTime);
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

    private void setTenNr() {
        this.tenNr = nr++;
    }

    public int getTenNr() {
        return tenNr;
    }
}
