package server.main;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import server.game.entities.EntitiesEnum;
import server.game.entities.Entity;
import server.game.entities.Player;
import server.packets.Packet;
import server.packets.PacketsEnum;
import server.packets.events.Connection;
import server.packets.events.Disconnect;
import server.packets.events.Position;
import server.packets.events.UnknownPacket;
import server.sockets.UDPServer;

public class Main {
	
	private static UDPServer server;
	private static List<Player> players;
	private static List<Entity> entities;
	private static long LastTick; 
	private static Logger log = Logger.getLogger(Main.class.getName());
	
	private static double TPS = (1.0/20.0)*1000.0;
	
	public static void main(String[] args) {
		server = new UDPServer();
		server.start();

		server.events.subscribe(PacketsEnum.CLIENT_CONNECTION, new Connection());
		server.events.subscribe(PacketsEnum.CLIENT_DISCONNECTION, new Disconnect());
		server.events.subscribe(PacketsEnum.CLIENT_POSITION, new Position());
		server.events.subscribe(PacketsEnum.UNKNOWN, new UnknownPacket());
		
		players = new ArrayList<Player>();
		entities = new ArrayList<Entity>();
		
		while(true) {
			if ((System.currentTimeMillis() - LastTick) > TPS) {
				LastTick = System.currentTimeMillis();
				Main.tickServer();
			}
		}
	}
	
	
	public static void tickServer() {
		
		for(Player toPlayer : players) {
			for(Player fromPlayer : players) {
				if (toPlayer == fromPlayer)
					continue;
				Packet packet = new Packet(PacketsEnum.SERVER_POSITION.getID(), toPlayer.getAddress(), toPlayer.getPort());
				packet.Write(fromPlayer.getId());
				packet.Write(fromPlayer.getName());
				packet.Write(fromPlayer.getPosition());
				toPlayer.SendPacket(packet);
			}
		}
		
	}

	public static void connectPlayer(Player ply) {
		ply.setId(System.nanoTime());
		for(Player player : players) {
			Packet packet = new Packet(PacketsEnum.SERVER_CONNECTION.getID(), player.getAddress(), player.getPort());
			packet.Write(ply.getId());
			packet.Write(ply.getName());
			player.SendPacket(packet);
		}
		log.info(ply.getName() + " Connected");
		players.add(ply);
		entities.add(ply);
		
		Packet packet = new Packet(PacketsEnum.SERVER_CONNECTION.getID(), ply.getAddress(), ply.getPort());
		packet.Write(ply.getId());
		packet.Write(ply.getName());
		packet.Write(players.size());
		for(Player player : players) {
			if (player == ply)
				continue;
			packet.Write(player.getId());
			packet.Write(player.getName());
			packet.Write(player.getPosition());
		}
		ply.SendPacket(packet);
	}
	
	public static void disconnectPlayer(Player ply, String string) {
		for(Player player : players) {
			if (ply != player) {
				Packet packet = new Packet(PacketsEnum.SERVER_DISCONNECTION.getID(), player.getAddress(), player.getPort());
				packet.Write(ply.getId());
				packet.Write(string);
				player.SendPacket(packet);
			}
		}
		log.info(ply.getName() + " Disconnected");
		players.remove(ply);
		entities.remove(ply);
	}

	public static List<Player> getPlayers() {
		return players;
	}

	public static List<Entity> getEntities() {
		return entities;
	}
}
