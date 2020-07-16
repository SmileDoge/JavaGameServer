package server.game;

public class Location {
	private float x;
	private float y;
	private float z;
	
	private World world;
	
	public Location(float x,float y,float z) {
		this.x = (x);
		this.y = (y);
		this.z = (z);
		
		this.world = (new World());
	}
	
	public Location(float x,float y,float z, World world) {
		this.x = (x);
		this.y = (y);
		this.z = (z);
		
		this.world = (world);
	}
	
	public Location() {
		this.x = (0f);
		this.y = (0f);
		this.z = (0f);
		
		this.world = (new World());
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
}
