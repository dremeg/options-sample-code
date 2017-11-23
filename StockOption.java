package data.options.positions;

import java.util.Calendar;

/**
 * Base class for stock options (call options or put options).
 * All options have strike prices and expiration dates, and
 * associated trading data (price, volume, open interest, etc.)
 * 
 * Options also have implied volatility, a measure of the future
 * price movements (regardless of direction) that are implied by
 * the price and terms of the option.
 */
abstract public class StockOption extends DailyQuote {
	
	String stockSymbol;
	double strike;
	int expirationDate;
	double spotPrice; //spot price of underlying asset
	double bid;
	double ask;
	double mid;
	int volume;
	int openInterest;
	double impliedVolatility;

	public StockOption(String id, String ticker, double spotPrice, double strike, double bid, double ask, int volume, String expirationDate, String quoteDate, int openInterest, double impliedVolatility) {
		super(id, quoteDate);
		this.stockSymbol = ticker;
		this.spotPrice = spotPrice;
		this.strike = strike;
		this.expirationDate = parseDate(expirationDate);
		this.ask = ask;
		this.bid = bid;
		this.mid = (bid + ask) / 2.0;
		this.volume = volume;
		this.openInterest = openInterest;
		this.impliedVolatility = impliedVolatility;
	}

	public StockOption(String id, String ticker, double spotPrice, double strike, double bid, double ask, int volume, int expirationDate, int quoteDate, int openInterest, double impliedVolatility) {
		super(id, quoteDate);
		this.stockSymbol = ticker;
		this.spotPrice = spotPrice;
		this.strike = strike;
		this.expirationDate = expirationDate;
		this.ask = ask;
		this.bid = bid;
		this.mid = (bid + ask) / 2.0;
		this.volume = volume;
		this.openInterest = openInterest;
		this.impliedVolatility = impliedVolatility;
	}

	public boolean isNearTheMoney() {
		return Math.abs((spotPrice - strike)) <= 0.05 * spotPrice;
	}

	public boolean isActive() {
		return volume > 0;
	}

	public boolean hasOpenInterest() {
		return openInterest > 0;
	}

	abstract public double getIntrinsicValue();
	 
	public int getDaysRemaining() {
		 Calendar start = Calendar.getInstance();
		 int year = quoteDate / 10000;
		 int month = (quoteDate / 100) - year * 100;
		 int day = quoteDate - (year * 10000) - (month * 100);
		 start.set(Calendar.YEAR, year);
		 start.set(Calendar.MONTH, month - 1);
		 start.set(Calendar.DAY_OF_MONTH, day);
		 start.set(Calendar.HOUR, 0);
		 start.set(Calendar.MINUTE, 0);
		 start.set(Calendar.SECOND, 0);
		 
		 Calendar end = Calendar.getInstance();
		 year = expirationDate / 10000;
		 month = (expirationDate / 100) - year * 100;
		 day = expirationDate - (year * 10000) - (month * 100);
		 end.set(Calendar.YEAR, year);
		 end.set(Calendar.MONTH, month - 1);
		 end.set(Calendar.DAY_OF_MONTH, day); 
		 end.set(Calendar.HOUR, 0);
		 end.set(Calendar.MINUTE, 0);
		 end.set(Calendar.SECOND, 0);
		 
		 long timespan = end.getTimeInMillis() - start.getTimeInMillis();
		 
		 int days = (int) (timespan / (1000 * 60 * 60 * 24));
		 
		 return days;
	}
	 
	public static final StockOption getOption(String line) {
		try {
			String[] tokens = line.split(",");
			if (tokens.length > 12) {
				if (tokens[5].equals("call")) {
					return new CallOption(tokens[3], tokens[0], Double.parseDouble(tokens[1]), Double.parseDouble(tokens[8]), Double.parseDouble(tokens[10]), Double.parseDouble(tokens[11]), Integer.parseInt(tokens[12]), tokens[6], tokens[7], Integer.parseInt(tokens[13]), Double.parseDouble(tokens[14]));
				} else {
					return new PutOption(tokens[3], tokens[0], Double.parseDouble(tokens[1]), Double.parseDouble(tokens[8]), Double.parseDouble(tokens[10]), Double.parseDouble(tokens[11]), Integer.parseInt(tokens[12]), tokens[6], tokens[7], Integer.parseInt(tokens[13]), Double.parseDouble(tokens[14]));
				}
			} else {
				if (tokens[1].equals("CallOption")) {	
					return new CallOption(tokens[4], tokens[2], Double.parseDouble(tokens[3]), Double.parseDouble(tokens[5]), Double.parseDouble(tokens[7]), Double.parseDouble(tokens[8]), Integer.parseInt(tokens[9]), Integer.parseInt(tokens[6]), Integer.parseInt(tokens[0]), Integer.parseInt(tokens[10]), Double.parseDouble(tokens[11]));
				} else {
					return new PutOption(tokens[4], tokens[2], Double.parseDouble(tokens[3]), Double.parseDouble(tokens[5]), Double.parseDouble(tokens[7]), Double.parseDouble(tokens[8]), Integer.parseInt(tokens[9]), Integer.parseInt(tokens[6]), Integer.parseInt(tokens[0]), Integer.parseInt(tokens[10]), Double.parseDouble(tokens[11]));					
				}
			}
		} catch (Exception e) {
			return null;
		}
	}
	 
	 public static String header() {
		 return "quoteDate,type,stockSymbol,spotPrice,ticker,strike,expirationDate,bid,ask,volume,openInterest,impliedVol";
	 }
	 
	 public String toString() {
		 return quoteDate + "," + 
				getClass().getSimpleName() + "," + 
				stockSymbol + "," + 
				spotPrice + "," +
				ticker + "," + 
				strike + "," + 
				expirationDate + "," +
				bid + "," +
				ask + "," +
				volume + "," +
				openInterest + "," +
				impliedVolatility;
	 }
}
