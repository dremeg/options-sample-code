package data.options.positions;

/**
 * A call option gives an investor the right to buy a stock at a given 
 * strike price any time before the option expires
 */
public class CallOption extends StockOption {
	
	public CallOption(String id, String ticker, double spotPrice, double strike, double bid, double ask,  int volume, String expirationDate, String quoteDate, int openInterest, double impliedVolatility) {
		super(id, ticker, spotPrice, strike, bid, ask, volume, expirationDate, quoteDate, openInterest, impliedVolatility);
	}
	
	public CallOption(String id, String ticker, double spotPrice, double strike, double bid, double ask,  int volume, int expirationDate, int quoteDate, int openInterest, double impliedVolatility) {
		super(id, ticker, spotPrice, strike, bid, ask, volume, expirationDate, quoteDate, openInterest, impliedVolatility);
	}

	public double getIntrinsicValue() {
		return Math.max(spotPrice - strike, 0);
	}

}
