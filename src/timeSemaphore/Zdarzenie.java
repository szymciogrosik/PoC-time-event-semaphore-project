package timeSemaphore;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;
import dissimlab.simcore.ZdarzenieWybierajace;

public class Zdarzenie extends BasicSimEvent<Otoczenie, Object> {

    private static int nr = 0;
    private int tenNr;

    public Zdarzenie(Otoczenie parent, double dt) throws SimControlException {
        super(parent, parent.getSemaphore());
        this.setTenNr();
        this.setId(this.getTenNr());
        this.setRunTime(SimManager.getInstance().getCommonSimContext().simTime() + dt);

        parent.getSemaphore().sortListDt();

        //Jeżeli zgłoszenie które weszło jest pierwsze
        if(parent.getSemaphore().sizeList() == 1) {
            new ZdarzenieWybierajace(parent, this.getRunTime() - SimManager.getInstance().getCommonSimContext().simTime());
        } else if(parent.getSemaphore().getFirst().getId() == this.getId() && parent.getSemaphore().sizeList() > 1) {
            System.out.println("Przesunięto w czasie zdarzenie otwierające " + SimManager.getInstance().getCommonSimContext().getSimEventCalendar().readFirst().toString() + " do t: " + this.getRunTime());

            SimManager.getInstance()
                    .getCommonSimContext()
                    .getSimEventCalendar()
                    .readObjectById(parent.getSemaphore().getZdarzenieWybierajace().getId())
                    .reschedule(this.getRunTime() - SimManager.getInstance().getCommonSimContext().simTime());
        }
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

    public void setTenNr() {
        this.tenNr = nr++;
    }

    public int getTenNr() {
        return tenNr;
    }
}
