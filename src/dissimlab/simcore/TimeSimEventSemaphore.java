package dissimlab.simcore;

import timeSemaphore.Otoczenie;
import java.util.Comparator;

public class TimeSimEventSemaphore extends SimEventSemaphore {

    private ZdarzenieWybierajace zdarzenieWybierajace;
    private Otoczenie parent;

    public TimeSimEventSemaphore(Otoczenie parent, String name) {
        super.setName(name);
        this.parent = parent;
    }

    public BasicSimEvent<BasicSimObj, Object> getFirst() {
        return this.getSimConditionalStChngList().getFirst();
    }

    public BasicSimEvent<BasicSimObj, Object> removeFirst() {
        return this.getSimConditionalStChngList().removeFirst();
    }

    public int sizeList () {
        return this.getSimConditionalStChngList().size();
    }

    public void sortListDt() {
        this.getSimConditionalStChngList().sort(Comparator.comparingDouble(BasicSimEvent::getRunTime));
    }

    public ZdarzenieWybierajace getZdarzenieWybierajace() {
        return zdarzenieWybierajace;
    }

    public void setZdarzenieWybierajace(ZdarzenieWybierajace zdarzenieWybierajace) {
        this.zdarzenieWybierajace = zdarzenieWybierajace;
    }

    public void notifyAddNewEventToSemaphore(BasicSimEvent<Otoczenie, Object> zdarzenie) throws SimControlException {
        this.sortListDt();
        //Jeżeli zgłoszenie które weszło jest pierwsze
        if(this.sizeList() == 1) {
            new ZdarzenieWybierajace(parent, zdarzenie.getRunTime() - SimManager.getInstance().getCommonSimContext().simTime());
        } else if(parent.getSemaphore().getFirst().getId() == this.getId() && parent.getSemaphore().sizeList() > 1) {
            System.out.println("Przesunięto w czasie zdarzenie otwierające " + SimManager.getInstance().getCommonSimContext().getSimEventCalendar().readFirst().toString() + " do t: " + zdarzenie.getRunTime());

            SimManager.getInstance()
                    .getCommonSimContext()
                    .getSimEventCalendar()
                    .readObjectById(parent.getSemaphore().getZdarzenieWybierajace().getId())
                    .reschedule(zdarzenie.getRunTime() - SimManager.getInstance().getCommonSimContext().simTime());
        }
    }
}
