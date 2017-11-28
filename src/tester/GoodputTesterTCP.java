package tester;

import java.net.Socket;

public class GoodputTesterTCP extends GoodputTesterAbstract {

	private Socket s;
	
	public GoodputTesterTCP(String ip, int port) throws Exception {
		s = new Socket(ip, port);
	}
	
	@Override
	void sendPackage(byte[] packagerino) {
		try {
			s.getOutputStream().write(packagerino);
			s.getOutputStream().flush();
		} catch (Exception e ) {
		}
	}

	@Override
	void close() {
		try {
			s.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	
}
