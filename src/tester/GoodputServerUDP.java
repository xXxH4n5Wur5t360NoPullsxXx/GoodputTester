package tester;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;

public class GoodputServerUDP extends GoodputServerAbstract {

	private DatagramSocket s;

	GoodputServerUDP(int port) throws Exception {
		s = new DatagramSocket(port);
	}

	@Override
	public void start() throws Exception {
		long packagecount = 1;
		s.setSoTimeout(10000);
		DatagramPacket p = new DatagramPacket(new byte[1400], 1400);
		s.receive(p);
		long start = System.currentTimeMillis();
		try {
			while (true) {
				p = new DatagramPacket(new byte[1400], 1400);
				s.receive(p);
				packagecount++;
			}
		} catch (SocketTimeoutException e) {}

		long end = System.currentTimeMillis() - 10000;


		this.print(packagecount, packagecount * 1400, end - start);
	}

	@Override
	public void close() throws Exception {
		s.close();
	}

}
