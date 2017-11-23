package data.options.positions;

/**
 * Base class for any daily price quotation for a
 * financial instrument.
 */
public class DailyQuote implements Comparable<DailyQuote> {

	String ticker;
	int quoteDate; // YYYYMMDD
	
	public DailyQuote(String ticker, int quoteDate) {
		super();
		this.ticker = ticker;
		this.quoteDate = quoteDate;
	}
	
	public DailyQuote(String ticker, String quoteDate) {
		super();
		this.ticker = ticker;
		this.quoteDate = parseDate(quoteDate);
	}
	
	/**
	 * Always sort by quote date.
	 */
	@Override
	public int compareTo(DailyQuote o) {
		return Integer.compare(quoteDate, o.quoteDate);
	}
	
	public int parseDate(String date) {
		
		try {
			String[] tokens = date.split("/");
			int n = Integer.parseInt(tokens[2]) * 10000;
			n += Integer.parseInt(tokens[0]) * 100;
			n += Integer.parseInt(tokens[1]);	
			return n;
		} catch (Exception e) {
			System.out.println("Cannot parse date: " + date);
			return 0;
		}
	}
}
