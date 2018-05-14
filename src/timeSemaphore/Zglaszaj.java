package timeSemaphore;

import dissimlab.random.SimGenerator;
import dissimlab.simcore.*;
import dissimlab.simcore.SimParameters.SimDateField;

/**
 * Description: Zdarzenie generatora zgłoszeń. Tworzy obiekt - zgłoszenie co losowy czas.
 * 
 * @author Dariusz Pierzchala

 */
public class Zglaszaj extends BasicSimEvent<Otoczenie, Object>
{
    private SimGenerator generator;
    private Otoczenie parent;

    public Zglaszaj(Otoczenie parent, double delay) throws SimControlException
    {
    	super(parent, delay);
    	generator = new SimGenerator();
    }

	@Override
	protected void stateChange() throws SimControlException {
        parent = getSimObj();

        // Tworze zdarzenie i wysyłam parent (Otoczenie) a tam już jest semafor, oraz dt dla danego zdarzenia.
		double randomDt = generator.normal(5.0, 1.0);

		Zdarzenie zdarzenie = null;

		if(parent.getSemaphore().sizeList() == 0) {
			zdarzenie = new Zdarzenie(parent, 10);
		} else {
			zdarzenie = new Zdarzenie(parent, randomDt);
		}

		System.out.println(simTime()+" - "+simDate(SimDateField.HOUR24)+" - "+simDate(SimDateField.MINUTE)+" - "+simDate(SimDateField.SECOND)+" - "+simDate(SimDateField.MILLISECOND)+": Otoczenie - Utworzono nowe zgl. nr " + zdarzenie.getTenNr() + " Zdarzenie poinno zostać wyjęte zza semafora o: " + zdarzenie.getRunTime());
		System.out.println(simTime()+" - "+simDate(SimDateField.HOUR24)+" - "+simDate(SimDateField.MINUTE)+" - "+simDate(SimDateField.SECOND)+" - "+simDate(SimDateField.MILLISECOND)+": Otoczenie - Dodano zgloszenie za semafor. - Aktualnie w semaforze oczekujących: " + parent.getSemaphore().numberOfBlocked());

		// Wygeneruj czas do kolejnego zgłoszenia
        double odstep = generator.normal(5.0, 1.0);
        setRepetitionPeriod(odstep);
    }

	@Override
	protected void onInterruption() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onTermination() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getEventParams() {
		// TODO Auto-generated method stub
		return null;
	}
}