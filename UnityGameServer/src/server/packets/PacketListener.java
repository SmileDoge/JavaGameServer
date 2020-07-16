package server.packets;

import java.net.DatagramSocket;

public interface PacketListener {
	void process(Packet packet, DatagramSocket socket);
}
