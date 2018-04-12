package dissimlab.simcore;

import timeSemaphore.Zdarzenie;

public class TimeSimEventSemaphore extends SimEventSemaphore {

    private double dt;
    private Zdarzenie zdarzenie;

    public TimeSimEventSemaphore(String name, long dt,  boolean isRepeated) throws SimControlException {
        super.setName(name);
        this.dt = dt;
        zdarzenie = new Zdarzenie(this, isRepeated);
    }

    @Override
    public void open() throws SimControlException {
        super.open();
    }

    public int getSizeOfSemafor() {
        return super.getSimConditionalStChngList().size();
    }

    public double getDt() {
        return dt;
    }

    public void setDt(double dt) {
        this.dt = dt;
    }
}
