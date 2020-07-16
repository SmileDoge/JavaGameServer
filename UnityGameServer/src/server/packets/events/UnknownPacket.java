package server.packets.events;

import java.net.DatagramSocket;

import server.packets.Packet;
import server.packets.PacketListener;

public class UnknownPacket implements PacketListener {

	@Override
	public void process(Packet packet, DatagramSocket socket) {
		System.out.println("Unknown packet received from "+ packet.getAddress()+":"+packet.getPort());
	}

}
