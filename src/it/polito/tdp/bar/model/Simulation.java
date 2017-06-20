package it.polito.tdp.bar.model;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class Simulation {

	private Random rd;
	private Stats statistiche;
	private Queue<Event> eventList;
	private Map<Integer, Table> tables;
	
	public Simulation() {
		rd = new Random(42);
		statistiche = new Stats();
		eventList = new PriorityQueue<Event>();
		tables = new HashMap<Integer, Table>();
	}
	
	public void addEvent(Event event) {
		eventList.add(event);
	}
	
	public void simulate() {
		while(!eventList.isEmpty()) {
			this.doSimulationStep();
		}
	}

	private long doSimulationStep() {
		
		if(this.eventList.isEmpty()) {
			return Long.MAX_VALUE;
		}
		
		Event e = this.eventList.remove();
		
		switch (e.eventType) {
		
		case ARRIVO_GRUPPO_CLIENTI:
			
			Table table = this.findAvailableTable(e.getCostumers().getNumeroDiPersone());
			
			if(table != null) {
				
				// Assegno il tavolo ai clienti
				table.setIdCostumers(e.getCostumers().getId());
				table.setLibero(false);
				e.getCostumers().setSoddisfatti(true);
				
				// Creo un nuovo evento per simulare i clienti che escono dal locale
				Event eventCostumersLeave = new Event(e.getTimeStamp() + e.getCostumers().getDurata(),
						Event.eventTypeEnum.PARTENZA_GRUPPO_CLIENTI, e.getCostumers());
				
				this.addEvent(eventCostumersLeave);
				
				System.out.println("Minuto: " + e.getTimeStamp() + " - Tavolo " + table.getIdTable() + " (Capienza: "
						+ table.getNumeroPostiASedere() + ") Occupato da Gruppo  " + e.getCostumers().getId() + " (Gruppo da "
						+ e.getCostumers().getNumeroDiPersone() + " Persone). Se ne vanno tra " + e.getCostumers().getDurata()
						+ " minuti");
			} else {
				
				float tolleranza = e.getCostumers().getTolleranza();
				float probabilita = this.rd.nextFloat();
				
				if(tolleranza >= probabilita) {
					
					// I clienti vengono serviti al bancone
					e.getCostumers().setSoddisfatti(true);
					System.out.println("Minuto: " + e.getTimeStamp() + " - Il gruppo  " + e.getCostumers().getId() + " (Gruppo da "
							+ e.getCostumers().getNumeroDiPersone() + " Persone) e' servito al bancone");
				} else {
					
					// I clienti escono dal locale non soddisfatti
					e.getCostumers().setSoddisfatti(false);
					System.out.println("Minuto: " + e.getTimeStamp() + " - Il gruppo  " + e.getCostumers().getId() + " (Gruppo da "
							+ e.getCostumers().getNumeroDiPersone() + " Persone) se n e' andato insoddisfatto");
				}
			}
			
			statistiche.aggiungiCliente(e.getCostumers());
			break;
		
		case PARTENZA_GRUPPO_CLIENTI:
			
			// I clienti lasciano il locale --> libero il tavolo
			Table freeTable = this.trovaTavolo(e.getCostumers().getId());
			freeTable.setLibero(true);
			freeTable.setIdCostumers(-1);
			
			System.out.println("Minuto: " + e.getTimeStamp() + " - Tavolo " + freeTable.getIdTable() + " (Capienza: "
					+ freeTable.getNumeroPostiASedere() + ") Liberato da gruppo " + e.getCostumers().getId()
					+ ". Ritorna disponibile!");

			break;

		default:
			throw new IllegalArgumentException();

		}
		return e.getTimeStamp();
			
		}
	
	
	/*
	 * UTILITIES
	 */
	
	private Table findAvailableTable(int numeroPersone) {
		
		int postiTavoloMin = Integer.MAX_VALUE;
		Table returnTable = null;
		
		for(Table t: tables.values()) {
			
			if(t.isLibero() && numeroPersone >= 0.5 * t.getNumeroPostiASedere()) {
				
				if(postiTavoloMin > t.getNumeroPostiASedere()) {
					postiTavoloMin = t.getNumeroPostiASedere();
					returnTable = t;
				}
			}
		}
		
		return returnTable;
	}
	
	/**
	 *  Trova il tavolo occupato dai clienti con l'id passato come paramentro
	 * @param id dei clienti
	 * @return tavolo che corrisponde alla ricerca, oppure NULL se non trovato
	 */
	private Table trovaTavolo(int id) {
		
		for (Table t: tables.values()) {
			if(t.getIdCostumers() == id) {
				return t;
			}
		}
		return null;
	}
	
	public void cleanup() {
		eventList.clear();
		statistiche.cleanUp();
	}
	
	public void addTable(int numPosti) {
		Table temp = new Table(numPosti);
		tables.put(temp.getIdTable(), temp);
		statistiche.setNumero_totale_tavoli(tables.size());
	}
	
	public Stats getStats() {
		return this.statistiche;
	}
	
	
}
