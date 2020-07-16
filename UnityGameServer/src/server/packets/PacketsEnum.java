package server.packets;

public enum PacketsEnum {
	UNKNOWN(0), 
	// Packets received from client
	
	
	CLIENT_CONNECTION(1),
	CLIENT_DISCONNECTION(2),
	CLIENT_POSITION(3),
	CLIENT_PING(4),
	
	
	// Packets for send to client
	
	
	SERVER_CONNECTION(5),
	SERVER_DISCONNECTION(6),
	SERVER_POSITION(7),
	SERVER_PING(8);
	
	
	// Utils
	
	private int id;
	
	PacketsEnum(int id){
		this.id = id;
	}
	
	public int getID() { return id;}
	
	public static PacketsEnum valueOff(int id) {
		for (PacketsEnum packets : PacketsEnum.values()) {
			if (packets.getID() == id) {
				return packets;
			}
		}
		return PacketsEnum.UNKNOWN;
	}
}
