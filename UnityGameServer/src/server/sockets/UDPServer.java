package server.sockets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import server.packets.Packet;
import server.packets.PacketManager;
import server.packets.PacketsEnum;

public class UDPServer extends Thread {
	
	public PacketManager events;
	
	private DatagramSocket socket;
    private boolean running;
    private byte[]buf = new byte[2048];
    
	
	public UDPServer() {
		this.events = new PacketManager();
		
		try {
			socket = new DatagramSocket(7777);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void SendData(Packet data) {
		try {
			socket.send(data.toDatagram());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		running = true;
		
		System.out.println("Server started");
		
		try {
		
			while(running) {
				
				DatagramPacket datagram = new DatagramPacket(buf, buf.length);
				socket.receive(datagram);
				
				InetAddress address = datagram.getAddress();
	            int port = datagram.getPort();
	            
	            datagram = new DatagramPacket(buf, buf.length, address, port);
	            Packet packet = new Packet(datagram.getData(), address, port);
	            int packetid = packet.ReadInt();
	            
	            events.notify(PacketsEnum.valueOff(packetid), packet, socket);
	            
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
