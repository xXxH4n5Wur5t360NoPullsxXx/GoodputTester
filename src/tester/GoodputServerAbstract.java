package tester;

public abstract class GoodputServerAbstract {
	public abstract void start() throws Exception;
	public abstract void close() throws Exception;
	protected void print(long packages, long bytes, long duration) {
		System.out.printf("Received %d packages. That corresponds to %d bytes of data. The transmisson took %s ms " +
						"and results in a transmission speed of %d B/s%n",
				packages, packages * 1400, duration, packages * 1400 / (duration / 1000));
	}
}
