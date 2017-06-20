package it.polito.tdp.bar.model;

public class GroupCostumers {
	
	static private int idCostumers = 0;
	private int id;
	private long timeArrival;
	private long durata;
	private float tolleranza;
	private int numeroDiPersone;
	private boolean soddisfatti;
	
	public GroupCostumers(long timeArrival, long durata, float tolleranza, int numeroDiPersone) {
		this.id = ++idCostumers;
		this.timeArrival = timeArrival;
		this.durata = durata;
		this.tolleranza = tolleranza;
		this.numeroDiPersone = numeroDiPersone;
		this.soddisfatti = false;
	}

	public boolean isSoddisfatti() {
		return soddisfatti;
	}

	public void setSoddisfatti(boolean soddisfatti) {
		this.soddisfatti = soddisfatti;
	}

	public static int getIdCostumers() {
		return idCostumers;
	}

	public int getId() {
		return id;
	}

	public long getTimeArrival() {
		return timeArrival;
	}

	public long getDurata() {
		return durata;
	}

	public float getTolleranza() {
		return tolleranza;
	}

	public int getNumeroDiPersone() {
		return numeroDiPersone;
	}

	@Override
	public String toString() {
		return String.format("Costumers id: %s", id);
	}

	
}
