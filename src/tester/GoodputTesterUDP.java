package tester;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.atomic.AtomicLong;

public class GoodputTesterUDP extends GoodputTesterAbstract {

	private final DatagramSocket ds;
	
	private AtomicLong runtime = new AtomicLong(0);
	
	public GoodputTesterUDP(String ip, int port) throws Exception {
		ds = new DatagramSocket();
		ds.connect(InetAddress.getByName(ip), port);
	}
	
	@Override
	void sendPackage(byte[] packagerino) {
		try {
			DatagramPacket dp = new DatagramPacket(packagerino, packagerino.length);
			ds.send(dp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	void close() {
		ds.close();
	}
}
