package server.packets;

import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.logging.Logger;

import server.game.Location;
import server.main.Main;

public class Packet {
	
	public ByteBuffer buffer;
	
	private InetAddress address; // used for send
	private int port; // used for send
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	public InetAddress getAddress() {
		return address;
	}
	
	public int getPort() {
		return port;
	}
	
	public Packet() {
		buffer = ByteBuffer.allocate(256).order(ByteOrder.LITTLE_ENDIAN);
	}
	
	public Packet(int packetid, InetAddress address, int port) {
		buffer = ByteBuffer.allocate(256).order(ByteOrder.LITTLE_ENDIAN);
		
		buffer.putInt(packetid);
		
		this.address = address;
		this.port = port;
	}
	
	public Packet(byte[] data, InetAddress address, int port) {
		buffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
		
		this.address = address;
		this.port = port;
	}
	
	public DatagramPacket toDatagram() {
		byte[] bytes = buffer.array();
		return new DatagramPacket(bytes, bytes.length, address, port);
	}
	
	public byte[] toArray() {
		return buffer.array();
	}
	
	public void Write(int data) {
		buffer.putInt(data);
	}
	
	public void Write(Short data) {
		buffer.putShort(data);
	}
	
	public void Write(byte data) {
		buffer.put(data);
	}
	
	public void Write(byte[] data) {
		buffer.put(data);
	}
	
	public void Write(long data) {
		buffer.putLong(data);
	}
	
	public void Write(float data) {
		buffer.putFloat(data);
	}
	
	public void Write(Location data) {
		buffer.putFloat(data.getX());
		buffer.putFloat(data.getY());
		buffer.putFloat(data.getZ());
	}
	
	public void Write(boolean data) {
		buffer.put((byte)(data?1:0));
	}
	
	public void Write(String data)  {
		byte[] str = null;
		try {
			str = data.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		buffer.putInt(str.length);
		buffer.put(str);
	}
	
	public byte ReadByte() {
		return buffer.get();
	}
	
	public Short ReadShort() {
		return buffer.getShort();
	}
	public int ReadInt() {
		return buffer.getInt();
	}
	public long ReadLong() {
		return buffer.getLong();
	}
	public float ReadFloat() {
		return buffer.getFloat();
	}
	public Location ReadLocation() {
		return new Location(buffer.getFloat(),buffer.getFloat(),buffer.getFloat());
	}
	public boolean ReadBool() {
		return buffer.get()!=0;
	}
	public String ReadString() {
		int length = buffer.getInt();
		int offset = buffer.arrayOffset();
		byte[] output = new byte[length];
		buffer.get(output, offset, length);
		try {
			return new String(output, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
