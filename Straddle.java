package data.options.positions;

/**
 * Straddle:
 * 
 * A straddle is a combination of a put option and a call option. The investor buys or sells a 
 * call and a put with the same strike price and expiration date. This combination makes the 
 * trade non-directional, meaning it does not matter whether the underlying stock becomes more 
 * or less valuable so long as it does become more or less valuable.
 * 
 * Long or Short Straddle:
 * 
 * When an investor buys the two options in a straddle, that position is a “long straddle”. 
 * When an investor sells the two options in a straddle, that position is a “short straddle”. 
 * A long straddle is a bet that volatility will increase, because volatile prices would 
 * cause either the call or the put to become more valuable. A short straddle is a bet that 
 * volatility will decrease, because the initial combined option premium would exceed the final 
 * combined intrinsic value of the two options.
 */
public class Straddle {
	
	public Straddle(PutOption put, CallOption call) {
		this.put = put;
		this.call = call;
	}

	PutOption put;
	CallOption call;
	
	public double getIntrinsicValue () {
		return put.getIntrinsicValue() + call.getIntrinsicValue();
	}
	
	public double getBid() {
		return put.bid + call.bid;
	}
	
	public double getAsk() {
		return put.ask + call.ask;
	}
	
	public double getMid() {
		return (getBid() + getAsk()) / 2.0;
	}
	
	public String toString() {
		return put.toString() + ",|," + call.toString();
	}
	
	public double getMidPercentSpot() {
		return (put.bid + call.bid) / put.spotPrice;
	}
}
