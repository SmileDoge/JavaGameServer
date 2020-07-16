package server.game.entities;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;

import server.packets.Packet;

public class Player extends Entity {
	
	private int port;
	private InetAddress address;
	
	public Player(InetAddress address, int port, String nickname, int id, DatagramSocket socket) {
		super(socket,nickname,id);
		this.address = address;
		this.port = port;
		
		this.type = EntitiesEnum.PLAYER;
		
		this.name = nickname;
	}

	public int getPort() {
		return port;
	}

	public InetAddress getAddress() {
		return address;
	}
	
	public void SendPacket(Packet packet) {
		try {
			socket.send(packet.toDatagram());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
