package it.polito.tdp.bar;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import it.polito.tdp.bar.model.Event;
import it.polito.tdp.bar.model.GroupCostumers;
import it.polito.tdp.bar.model.Simulation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class BarController {
	
	private Simulation simulation;
	
	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
		
		// Aggiungo i tavoli al simulatore
    	simulation.addTable(10);
    	simulation.addTable(10);

    	simulation.addTable(8);
    	simulation.addTable(8);
    	simulation.addTable(8);
    	simulation.addTable(8);

    	simulation.addTable(6);
    	simulation.addTable(6);
    	simulation.addTable(6);
    	simulation.addTable(6);

    	simulation.addTable(4);
    	simulation.addTable(4);
    	simulation.addTable(4);
    	simulation.addTable(4);
    	simulation.addTable(4);
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnSimula;

    @FXML
    void doSimula(ActionEvent event) {
    	
    	this.txtResult.clear();
    	this.simulation.cleanup();
    	
    	// Creo un generatore di numeri casuali
    	Random rn = new Random(42);
    	
    	long lastTimeOfArrival = 0;
    	
    	for (int i = 0; i < 2000; i++) {
    		
    		long timeOfArrival = lastTimeOfArrival + 1 + rn.nextInt(9);
    		long duration = (long) (60 + Math.random() * 60);
    		float tolerance = rn.nextFloat();
			int numberOfPeople =  1 + rn.nextInt(9);
			
			// Genro un nuovo gruppo di clienti
			GroupCostumers customerGroup = new GroupCostumers(timeOfArrival, duration, tolerance, numberOfPeople);
			
			// Creo un nuovo evento e lo inserisco nella coda.
			Event e = new Event(timeOfArrival, Event.eventTypeEnum.ARRIVO_GRUPPO_CLIENTI, customerGroup);
			simulation.addEvent(e);
		}
		
		// Avvio la simulazion
		simulation.simulate();
		
		// Ottengo il risultato
		txtResult.appendText(simulation.getStats().toString());	
    	}
    	

 

    @FXML
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Bar.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Bar.fxml'.";

        this.txtResult.setStyle("-fx-font-family: monospace");
    }
}
