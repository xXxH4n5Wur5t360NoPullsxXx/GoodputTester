package tester;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;

public class GoodputServerUDP extends GoodputServerAbstract {

	DatagramSocket s;

	public GoodputServerUDP(int port) throws Exception {
		s = new DatagramSocket(port);
	}

	@Override
	public void start() throws Exception {
		long packagecount = 1;
		DatagramPacket p = new DatagramPacket(new byte[1400], 1400);
		s.receive(p);
		long start = System.currentTimeMillis();
		try {
			s.setSoTimeout(10000);
			while (true) {
				p = new DatagramPacket(new byte[1400], 1400);
				s.receive(p);
				packagecount++;
			}
		} catch (SocketTimeoutException e) {

		}

		long end = System.currentTimeMillis();


		this.print(packagecount, packagecount * 1400, end - start);
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub

	}

}
