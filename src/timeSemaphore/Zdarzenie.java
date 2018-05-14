package timeSemaphore;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;

public class Zdarzenie extends BasicSimEvent<Otoczenie, Object> {

    private static int nr = 0;
    private int tenNr;

    public Zdarzenie(Otoczenie parent, double dt) throws SimControlException {
        super(parent, parent.getSemaphore());
        this.setTenNr();
        this.setId(this.getTenNr());
        this.setRunTime(SimManager.getInstance().getCommonSimContext().simTime() + dt);
        parent.getSemaphore().notifyAddNewEventToSemaphore(this);
    }

    @Override
    protected void stateChange() { }

    @Override
    protected void onTermination() { }

    @Override
    protected void onInterruption() { }

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
