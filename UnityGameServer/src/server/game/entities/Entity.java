package server.game.entities;

import java.net.DatagramSocket;

import server.game.Location;

public abstract class Entity {
	
	protected Location loc = new Location(0.0f,0.0f,0.0f);
	protected String name;
	private long id;
	
	protected short anim;
	protected DatagramSocket socket;
	
	protected EntitiesEnum type;
	
	public Entity(DatagramSocket socket, String name, int id) {
		this.socket = socket;
		this.name = name;
		this.id = id;
	}
	
	public void setPosition(Location to) {
		this.loc = to;
	}
	
	public Location getPosition() {
		return loc;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
}
