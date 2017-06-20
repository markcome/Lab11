package it.polito.tdp.bar.model;

public class Event implements Comparable<Event>{
	
	public enum eventTypeEnum {
		ARRIVO_GRUPPO_CLIENTI, PARTENZA_GRUPPO_CLIENTI;
	}
	
	public eventTypeEnum eventType;
	
	private long timeStamp;
	private GroupCostumers costumers;
	
	public Event(long timeStamp, eventTypeEnum eventType, GroupCostumers costumers) {
		this.timeStamp = timeStamp;
		this.eventType = eventType;
		this.costumers = costumers;
	}

	public eventTypeEnum getEventType() {
		return eventType;
	}

	public long getTimeStamp() {
		return timeStamp;
	}
	
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public GroupCostumers getCostumers() {
		return costumers;
	}

	@Override
	public int compareTo(Event o) {
		return Long.compare(this.timeStamp, o.getTimeStamp());
	}

	
}
