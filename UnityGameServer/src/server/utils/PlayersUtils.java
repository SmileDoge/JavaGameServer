package server.utils;

import java.net.InetAddress;

import server.game.entities.Player;
import server.main.Main;

public class PlayersUtils {

	public static Player GetPlayerFromAddressPort(InetAddress address, int port) {
		for (Player ply : Main.getPlayers()) {
			if ((address.getHostAddress().equals(ply.getAddress().getHostAddress())) && (port == ply.getPort())) {
				return ply;
			}
		}
		return null;
	}
	
	public static Player GetPlayerFromAddress(InetAddress address) {
		for (Player ply : Main.getPlayers()) {
			if (address.getHostAddress().equals(ply.getAddress().getHostAddress())) {
				return ply;
			}
		}
		return null;
	}
}
