package dissimlab.simcore;

import java.util.Comparator;

public class TimeSimEventSemaphore extends SimEventSemaphore {

    private EventSelectAndFree eventSelectAndFree;

    public TimeSimEventSemaphore(String name) {
        super.setName(name);
    }

    BasicSimEvent<BasicSimObj, Object> getFirst() {
        return this.getSimConditionalStChngList().getFirst();
    }

    BasicSimEvent<BasicSimObj, Object> removeFirst() {
        return this.getSimConditionalStChngList().removeFirst();
    }

    public int sizeList () {
        return this.getSimConditionalStChngList().size();
    }

    private void sortListDt() {
        this.getSimConditionalStChngList().sort(Comparator.comparingDouble(BasicSimEvent::getRunTime));
    }

    private EventSelectAndFree getEventSelectAndFree() {
        return eventSelectAndFree;
    }

    void setEventSelectAndFree(EventSelectAndFree eventSelectAndFree) {
        this.eventSelectAndFree = eventSelectAndFree;
    }

    public void notifyAddNewEventToSemaphore(BasicSimEvent<BasicSimObj, Object> event) throws SimControlException {
        this.sortListDt();

        //Jeżeli zgłoszenie które weszło jest jedyne w kolejce
        if(this.sizeList() == 1) {
            new EventSelectAndFree(this, event.getRunTime() - SimManager.getInstance().getCommonSimContext().simTime());
        } else if(this.sizeList() > 1 && this.getFirst().getId() == event.getId()) {
            System.out.println("Przesunięto w czasie zdarzenie otwierające " + "do t: " + event.getRunTime());

            SimManager.getInstance()
                    .getCommonSimContext()
                    .getSimEventCalendar()
                    .readObjectById(this.getEventSelectAndFree().getId())
                    .reschedule(event.getRunTime() - SimManager.getInstance().getCommonSimContext().simTime());
        }
    }
}
