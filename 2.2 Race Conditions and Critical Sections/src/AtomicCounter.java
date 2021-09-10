import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
	private AtomicInteger atomicInteger = new AtomicInteger();

	public AtomicInteger getAtomicInteger() {
		return atomicInteger;
	}

	public void setAtomicInteger(int value) {
		atomicInteger.set(value);
		System.out.println("AtomicSetter " + atomicInteger);
	}

	public void atomicAdd(int value) {
		System.out.println(atomicInteger.addAndGet(value));
	}
}
