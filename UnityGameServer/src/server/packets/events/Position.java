package server.packets.events;

import java.net.DatagramSocket;

import server.game.Location;
import server.game.entities.Player;
import server.packets.Packet;
import server.packets.PacketListener;
import server.utils.PlayersUtils;

public class Position implements PacketListener {

	@Override
	public void process(Packet packet, DatagramSocket socket) {
		Player ply = PlayersUtils.GetPlayerFromAddressPort(packet.getAddress(),packet.getPort());
		ply.setPosition(new Location(packet.ReadFloat(),packet.ReadFloat(),packet.ReadFloat()));
	}
	
}
