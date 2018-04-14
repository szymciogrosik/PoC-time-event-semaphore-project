package dissimlab.simcore;

import timeSemaphore.ZdarzenieOtwierajace;

public class TimeSimEventSemaphore extends SimEventSemaphore {

    private double dt;
    private ZdarzenieOtwierajace zdarzenieOtwierajace;

    public TimeSimEventSemaphore(String name, long dt,  boolean isRepeated) throws SimControlException {
        super.setName(name);
        this.dt = dt;
        zdarzenieOtwierajace = new ZdarzenieOtwierajace(this, isRepeated);
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
