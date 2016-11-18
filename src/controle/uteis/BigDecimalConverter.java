package controle.uteis;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;

public class BigDecimalConverter {
	public static String bigDecimalToString(BigDecimal numero, Integer casas){
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMinimumFractionDigits(0);
		numberFormat.setMaximumFractionDigits(casas);
		return numberFormat.format(numero.floatValue());
	}
	
	public static BigDecimal stringToBigDecimal(String numero) throws ParseException{
		NumberFormat numberFormat = NumberFormat.getInstance();
		return new BigDecimal(numberFormat.parse(numero).toString());
	}
	
	public static BigDecimal moneyToBigDecimal(String currencyValue) throws ParseException {
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
		return new BigDecimal(numberFormat.parse(currencyValue).toString());
	}

	public static String bigDecimalToMoney(BigDecimal bigDecimal) throws NumberFormatException {
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
		return numberFormat.format(bigDecimal.floatValue());
	}
}
