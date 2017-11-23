package data.options.positions;

/**
 * Represents the closing price of a stock.
 * 
 * The values for short and long term realized volatility (and
 * the ratio of short to long term realized volatility) are
 * calculated as: vol = std ( ln ( p[n+1] / p[n] ) ) * sqrt(252).
 */
public class StockPrice extends DailyQuote {
	
	double open;
	double high;
	double low;
	double close;
	int volume;
	double shortTermRv = Double.NaN;
	double longTermRv = Double.NaN;
	double slVolRatio = Double.NaN;
	
	public StockPrice(String ticker, String quoteDate, double open, double high, double low, double close, int volume) {
		super(ticker, quoteDate);
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
	}
	
	public String toString() {	
		return ticker + "," + quoteDate + "," + open + "," + high + "," + low + "," + close + "," + volume + "," + shortTermRv + "," + longTermRv + "," + slVolRatio;
	}
	
}
