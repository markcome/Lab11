package it.polito.tdp.bar.model;

public class Stats {
	
	private int numero_totale_clienti;
	private int numero_clienti_soddisfatti;
	private int numero_clienti_insoddisfatti;
	private int numero_totale_gruppi = 0;
	private int numero_totale_tavoli = 0;
	private int numero_gruppi_insoddisfatti = 0;
	private int numero_gruppi_soddisfatti = 0;
	
	public void aggiungiCliente(GroupCostumers costumers) {
		
		this.numero_totale_gruppi++;
		this.numero_totale_clienti += costumers.getNumeroDiPersone();
		
		if(costumers.isSoddisfatti()) {
			this.numero_gruppi_soddisfatti++;
			this.numero_clienti_soddisfatti += costumers.getNumeroDiPersone();
		} else {
			this.numero_gruppi_insoddisfatti++;
			this.numero_clienti_insoddisfatti += costumers.getNumeroDiPersone();
		}
	}
	
	@Override
	public String toString() {
		
		String stampa = "Numero totale tavoli: " + this.numero_totale_tavoli + "\n";
		stampa += "\nNumero totale clienti: " + this.numero_totale_clienti + "\n";
		stampa += "Numero clienti soddisfatti: " + this.numero_clienti_soddisfatti + "\n";
		stampa += "Numero clienti insoddisfatti: " + this.numero_clienti_insoddisfatti + "\n";
		stampa += "\nNumero totale gruppi: " + this.numero_totale_gruppi + "\n";
		stampa += "Numero gruppi soddisfatti: " + this.numero_gruppi_soddisfatti + "\n";
		stampa += "Numero gruppi insoddisfatti: " + this.numero_gruppi_insoddisfatti;
		
		return stampa;
	}

	public void setNumero_totale_tavoli(int numero_totale_tavoli) {
		this.numero_totale_tavoli = numero_totale_tavoli;
	}
	
	public void cleanUp() {
		
		this.numero_totale_clienti = 0;
		this.numero_clienti_soddisfatti = 0;
		this.numero_clienti_insoddisfatti = 0;
		
		this.numero_totale_gruppi = 0;
		this.numero_gruppi_soddisfatti = 0;
		this.numero_gruppi_insoddisfatti = 0;
	}
	

}
