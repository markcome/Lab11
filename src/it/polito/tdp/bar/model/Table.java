package it.polito.tdp.bar.model;

public class Table {

	static private int totaleTavoli = 0;
	private int idCostumers;
	private int idTable;
	private int numeroPostiASedere;
	private boolean libero;
	
	public Table (int numeroPostiASedere){
		this.libero = true;
		this.numeroPostiASedere = numeroPostiASedere;
		this.idTable = ++this.totaleTavoli;
		this.idCostumers = -1;
	}

	public int getIdCostumers() {
		return idCostumers;
	}

	public void setIdCostumers(int idCostumers) {
		this.idCostumers = idCostumers;
	}

	public boolean isLibero() {
		return libero;
	}

	public void setLibero(boolean libero) {
		this.libero = libero;
	}

	public static int getTotaleTavoli() {
		return totaleTavoli;
	}

	public int getIdTable() {
		return idTable;
	}

	public int getNumeroPostiASedere() {
		return numeroPostiASedere;
	}
	
	

}
