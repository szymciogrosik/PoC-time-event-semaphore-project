package timeSemaphore;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;

public class Zdarzenie extends BasicSimEvent<Otoczenie, Object> {

    public Zdarzenie(Otoczenie parent, double dt) throws SimControlException {
        super(dt, parent, parent.getSemaphore());
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
}
