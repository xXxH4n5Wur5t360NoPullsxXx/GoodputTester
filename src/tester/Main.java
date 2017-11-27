package tester;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

	public static void main(String[] args) throws Exception {
		
		String mode = args[0];
		String packageType = args[1];
		int port = Integer.parseInt(args[2]);
	
		String ip = null;
		
		if ("client".equals(mode)) {
			ip = args[3];
			client(ip, port, packageType);
		} else {
			server(port, packageType);
		}
		
		
		
	}
	
	private static void client(String ip, int port, String packageType) throws Exception {
		GoodputtesterAbstract gp;
		
		byte[] packet = new byte[1400];

		for (int i = 0; i < 1400; i++) {
			packet[i] = (byte) LocalDateTime.now().getNano();
		}
		
		switch (packageType) {
			case "tcp":
				gp = new GoodputTesterTCP(ip, 8085);
				break;
			case "upd":
				gp = new GoodputTesterUDP(ip, 8085);
				break;
			default:
				throw new Exception("Herpederp falsche arguments lol");
		}

		Timer t = new Timer();
		
		TimerTask tk = new TimerTask() {
			int i = 1;
			@Override
			public void run() {
				if (i > 1400) {
					t.cancel();
					return;
				}
				Integer.
				gp.sendPackage(packet);
				i++;
				
			}
		};
		
		t.schedule(tk, 0, 100);
	}
	
	private static void server(int port, String packageType) {
		
	}
	
}
