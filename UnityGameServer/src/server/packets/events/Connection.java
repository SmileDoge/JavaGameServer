package server.packets.events;

import java.net.DatagramSocket;
import java.net.InetAddress;

import server.game.entities.Player;
import server.main.Main;
import server.packets.Packet;
import server.packets.PacketListener;

public class Connection implements PacketListener {

	@Override
	public void process(Packet packet, DatagramSocket socket) {
		String nickname = packet.ReadString();
		InetAddress address = packet.getAddress();
		int port = packet.getPort();
		
		System.out.println(port);
		
		Player ply = new Player(address, port, nickname, port, socket);
		
		Main.connectPlayer(ply);
	}
}
