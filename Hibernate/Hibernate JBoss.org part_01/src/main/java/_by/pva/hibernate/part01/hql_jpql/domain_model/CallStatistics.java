package _by.pva.hibernate.part01.hql_jpql.domain_model;

public class CallStatistics {
	
	private final long count;
	private final long total;
	private final int min;
	private final int max;
	private final double avg;

	public CallStatistics(long count, long total, int min, int max, double avg) {
		this.count = count;
		this.total = total;
		this.min = min;
		this.max = max;
		this.avg = avg;
	}

	public long getCount() {
		return count;
	}

	public long getTotal() {
		return total;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public double getAvg() {
		return avg;
	}
}
