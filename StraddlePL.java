package data.options.positions;

/**
 * The profit or loss from a straddle is the difference between
 * the initial combined premium of the put and call options and
 * the final combined intrinsic value of the put and call options.
 * 
 * A dealer's p&l is measured by buying options at the market bid
 * price and selling at the market ask price.
 * 
 * A customer's p&l is measured by buying options at the market ask
 * price and selling at the market bid price.
 * 
 * A mid-market p&l is measured by buying and selling options at
 * the midpoint of the bid and ask prices.
 */
public class StraddlePL {

	Straddle initialPosition;
	Straddle finalPosition;
	double shortTermRV;
	double longTermRV;
	
	public StraddlePL(Straddle initialPosition, Straddle finalPosition) {
		this.initialPosition = initialPosition;
		this.finalPosition = finalPosition;
	}
	
	public double getDealerPL() {
		return -initialPosition.getBid() + finalPosition.getAsk();
	}
	
	public double getCustomerPL() {
		return -initialPosition.getAsk() + finalPosition.getBid();
	}
	
	public double getMidPL() {
		return -initialPosition.getMid() + finalPosition.getMid();
	}
	
	public double getDealerReturn() {
		return getDealerPL() / initialPosition.getBid();
	}
	
	public double getCustomerReturn() {
		return getCustomerPL() / initialPosition.getAsk();
	}
	
	public double getMidReturn() {
		return getMidPL() / initialPosition.getMid();
	}
	
	public String toString() {
		return initialPosition.toString() + ",|," + finalPosition.toString() + ",|" + 
				",dealer," + getDealerPL() + "," + getDealerReturn() + 
				",customer," + getCustomerPL() + "," + getCustomerReturn() + 
				",mid," + getMidPL() + "," + getMidReturn() + ",strv," + shortTermRV + ",ltrv," + longTermRV;
	}
}
