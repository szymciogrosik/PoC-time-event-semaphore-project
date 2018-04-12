package timeSemaphore;

import dissimlab.random.SimGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimParameters.SimDateField;
import dissimlab.simcore.TimeSimEventSemaphore;

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

	public Zglaszaj(Otoczenie parent, Zgloszenie zgl, TimeSimEventSemaphore semaphore) throws SimControlException
	{
		super(parent, semaphore, zgl);
		generator = new SimGenerator();
	}
    
	@Override
	protected void onInterruption() throws SimControlException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onTermination() throws SimControlException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void stateChange() throws SimControlException {
        parent = getSimObj();

        Zgloszenie zgl = new Zgloszenie(simTime());
        System.out.println(simTime()+" - "+simDate(SimDateField.HOUR24)+" - "+simDate(SimDateField.MINUTE)+" - "+simDate(SimDateField.SECOND)+" - "+simDate(SimDateField.MILLISECOND)+": Otoczenie - Utworzono nowe zgl. nr: " + zgl.getTenNr());

		new Zglaszaj(parent, zgl, parent.getSemaphore());
		System.out.println(simTime()+" - "+simDate(SimDateField.HOUR24)+" - "+simDate(SimDateField.MINUTE)+" - "+simDate(SimDateField.SECOND)+" - "+simDate(SimDateField.MILLISECOND)+": Otoczenie - Dodano zgloszenie za semafor. - Aktualnie w semaforze oczekujących: " + parent.getSemaphore().getSizeOfSemafor());

		// Wygeneruj czas do kolejnego zgłoszenia
        double odstep = generator.normal(5.0, 1.0);
        setRepetitionPeriod(odstep);
        //alternatywnie: parent.zglaszaj = new Zglaszaj(parent, odstep);
	}

	@Override
	public Object getEventParams() {
		// TODO Auto-generated method stub
		return null;
	}
}