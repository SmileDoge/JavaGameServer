package server.packets;

import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PacketManager {
	Map<PacketsEnum, List<PacketListener>> listeners = new HashMap<>();
	
	public PacketManager() {
        for (PacketsEnum operation : PacketsEnum.values()) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }
	
	public void subscribe(PacketsEnum eventType, PacketListener listener) {
        List<PacketListener> users = listeners.get(eventType);
        users.add(listener);
    }

    public void unsubscribe(PacketsEnum eventType, PacketListener listener) {
        List<PacketListener> users = listeners.get(eventType);
        users.remove(listener);
    }
    
    public void notify(PacketsEnum eventType, Packet packet, DatagramSocket socket) {
        List<PacketListener> users = listeners.get(eventType);
        for (PacketListener listener : users) {
            listener.process(packet, socket);
        }
    }
    
}
