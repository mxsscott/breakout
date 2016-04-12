package cx.mscott.breakout.objects;

import cx.mscott.breakout.Physics;

public class BounceResult implements Comparable<BounceResult> {
	private Physics.Result result;
	private Bounceable object;
	
	public BounceResult(Physics.Result result, Bounceable object) {
		this.result = result;
		this.object = object;
	}
	
	public Physics.Result getResult() {
		return result;
	}
	
	public Bounceable getObject() {
		return object;
	}

	@Override
	public int compareTo(BounceResult o) {
		return result.compareTo(o.result);
	}
}
