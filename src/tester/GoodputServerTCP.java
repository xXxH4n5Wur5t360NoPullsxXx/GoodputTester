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
		int terminator = 0;
		try {
			while (terminator != -1) {
				terminator = ss.getInputStream().read(packet);
				packagecount += terminator;
			}
		} catch (Exception e) {}

		long end = System.currentTimeMillis();
		packagecount++;
		packagecount /= 1400;

		this.print(packagecount, packagecount * 1400, end - start);
		
		ss.close();
	}

	@Override
	public void close() throws Exception {
		s.close();
	}

}
