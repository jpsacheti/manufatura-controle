package controle.uteis;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalConverter {
	public static String bigDecimalToString(BigDecimal numero, Integer casas){
		return numero.setScale(4, RoundingMode.HALF_UP).toString();
	}
	
	public static BigDecimal stringToBigDecimal(String numero){
		return new BigDecimal(numero.replace(",", "."));
	}
}
