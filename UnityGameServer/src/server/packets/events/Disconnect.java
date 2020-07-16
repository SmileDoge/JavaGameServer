package server.packets.events;

import java.net.DatagramSocket;

import server.game.entities.Player;
import server.main.Main;
import server.packets.Packet;
import server.packets.PacketListener;
import server.utils.PlayersUtils;

public class Disconnect implements PacketListener {

	@Override
	public void process(Packet packet, DatagramSocket socket) {
		Player ply = PlayersUtils.GetPlayerFromAddressPort(packet.getAddress(),packet.getPort());
		Main.disconnectPlayer(ply, "Disconnect by user");
	}

}
