package atomic.atomicReference;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.ArrayList;
import java.math.BigInteger;

public class AtomicReferenceEx_SequenceGenerator {
	public static void main(String[] args) {
		SequenceGenerator sg = new SequenceGenerator();
		List<Sequence> sequences = new ArrayList<Sequence>();
		for (int i = 0; i < 10; i++) {
			Sequence seq = new Sequence(i, 3, sg);
			sequences.add(seq);
		}
		System.out.println("\nSequences calculating\n");
		// Waiting for threads to complete.
		int summa;
		do {
			summa = 0;
			for (int i = 0; i < sequences.size(); i++) {
				if (!sequences.get(i).thread.isAlive()) {
					sequences.get(i).printSequence();
					summa++;
				}
			}
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
			}
		} while (summa < sequences.size());

		System.out.println("\n\nThreads work was completed!");
		System.exit(0);
	}
}

class SequenceGenerator {
	private BigInteger MULTIPLIER;
	private AtomicReference<BigInteger> element;

	public SequenceGenerator() {
//		if (MULTIPLIER == null)
		MULTIPLIER = BigInteger.valueOf(2);
		element = new AtomicReference<BigInteger>(BigInteger.ONE);
	}

	public BigInteger next() {
		BigInteger value;
		BigInteger next;
		do {
			value = element.get();
			next = value.multiply(MULTIPLIER);
		} while (!element.compareAndSet(value, next));
		return value;
	}
}

class Sequence implements Runnable {
	Thread thread;
	int id;
	int count;
	boolean printed = false;
	SequenceGenerator sg;
	List<BigInteger> sequence = new ArrayList<BigInteger>();

	Sequence(final int id, final int count, SequenceGenerator sg) {
		thread = new Thread(this);
		this.id = id;
		this.count = count;
		this.sg = sg;

		System.out.println("Thread " + id + " was created!");
		thread.start();
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < count; i++) {
				sequence.add(sg.next());
				Thread.sleep((long) ((Math.random() * 2 + 1) * 30));
			}
		} catch (InterruptedException e) {
			System.out.println("Thread " + id + " was interupted!");
		}
		System.out.println("Thread was " + id + " terminated!");
//      printSequence();
	}

	public void printSequence() {
		if (printed)
			return;
		String tmp = "[";
		for (int i = 0; i < sequence.size(); i++) {
			if (i > 0)
				tmp += ", ";
			String nb = String.valueOf(sequence.get(i));
			while (nb.length() < 9)
				nb = " " + nb;
			tmp += nb;
		}
		tmp += "]";
		System.out.println("Sequence of the thread " + id + " : " + tmp);
		printed = true;
	}
}
