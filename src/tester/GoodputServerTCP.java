package tester;

import java.net.ServerSocket;
import java.net.Socket;

public class GoodputServerTCP extends GoodputServerAbstract {

	private final ServerSocket s;

	GoodputServerTCP(int port) throws Exception {
		s = new ServerSocket(port);
	}

	@Override
	public void start()throws Exception {
		byte[] packet = new byte[1400];

		long packagecount = 0;
		Socket ss = s.accept();

		ss.setSoTimeout(10000);
		packagecount += ss.getInputStream().read(packet);
		long start = System.currentTimeMillis();

		try {
			while (true) {
				packagecount += ss.getInputStream().read(packet);
			}
		} catch (Exception e) {}

		long end = System.currentTimeMillis();

		packagecount /= 1400;

		this.print(packagecount, packagecount * 1400, end - start);
	}

	@Override
	public void close() throws Exception {
		ss.close();
		s.close();
	}

}
