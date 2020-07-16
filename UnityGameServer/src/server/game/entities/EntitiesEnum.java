package server.game.entities;

public enum EntitiesEnum {
	
	UNKNOWN(0),
	BASE_ENTITY(1),
	PLAYER(2);
	
	// Utils
	
	private int id;
	
	EntitiesEnum(int id){
		this.id = id;
	}
	
	public int getID() { return id;}
	
	public static EntitiesEnum valueOff(int id) {
		for (EntitiesEnum packets : EntitiesEnum.values()) {
			if (packets.getID() == id) {
				return packets;
			}
		}
		return EntitiesEnum.UNKNOWN;
	}
}
