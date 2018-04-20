package dissimlab.simcore;

import timeSemaphore.Otoczenie;

public class TimeSimEventSemaphore extends SimEventSemaphore {

    public TimeSimEventSemaphore(Otoczenie parent, String name, long dt, boolean isRepeated) throws SimControlException {
        super.setName(name);
        if(isRepeated)
            new ZdarzenieOtwierajace(parent, this, dt);
        else
            new ZdarzenieOtwierajace(this, dt);
    }

    @Override
    public void open() {
        while (!super.getSimConditionalStChngList().isEmpty()){
            this.removeFirstState();
        }
    }
}
