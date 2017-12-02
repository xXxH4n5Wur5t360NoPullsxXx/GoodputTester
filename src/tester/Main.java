package tester;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class Main {

	public static void main(String[] args) throws Exception {
		
		String mode = args[0];
		String packageType = args[1];
		int port = Integer.parseInt(args[2]);
		
		if ("client".equals(mode)) {
			int n = Integer.parseInt(args[3]);
			int k = Integer.parseInt(args[4]);
			String ip = args[5];
			client(ip, port, packageType, n , k);
		} else {
			server(port, packageType);
		}
	}
	
	private static void client(String ip, int port, String packageType, int n, int k) throws Exception {
		GoodputTesterAbstract gp;
		
		byte[] packet = new byte[1400];

		for (int i = 0; i < 1400; i++) {
			packet[i] = (byte) LocalDateTime.now().getNano();
		}
		
		switch (packageType) {
			case "tcp":
				gp = new GoodputTesterTCP(ip, port);
				break;
			case "udp":
				gp = new GoodputTesterUDP(ip, port);
				break;
			default:
				throw new Exception("Herpederp falsche arguments lol");
		}

		final AtomicLong packageCount = new AtomicLong(0);

		Thread t = new Thread(() -> {
			while (!Thread.currentThread().isInterrupted()) {
				for (int i = 0; i < n; i++) {
					gp.sendPackage(packet);
					packageCount.incrementAndGet();
				}
				try {
					Thread.sleep(k);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		});

		t.start();

		Thread.sleep(30000);
		t.interrupt();

		Thread.sleep(1000);

		System.out.printf("Send %d packages in 30 seconds. This conforms %d bytes. Therefore a speed of %d B/s " +
				"was achieved%n", packageCount.get(), packageCount.get() * 1400, packageCount.get() * 1400 / 30);
	}
	
	private static void server(int port, String packageType) throws Exception {
		GoodputServerAbstract gp;

		switch (packageType.toLowerCase()) {
			case "tcp":
				gp = new GoodputServerTCP(port);
				break;
			case "udp":
				gp = new GoodputServerUDP(port);
				break;
			default:
				throw new Exception("Unknown protocol");
		}

		gp.start();

		gp.close();
	}
	
}
