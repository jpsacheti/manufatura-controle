package controle.uteis;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Text;

public class MascaraDatas implements FocusListener {

	private static final MascaraDatas formatarDatas = new MascaraDatas();
	private static SimpleDateFormat formatoUsuario = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat ddMMyy = new SimpleDateFormat("ddMMyy");
	private static SimpleDateFormat ddMMyyyy = new SimpleDateFormat("ddMMyyyy");

	private MascaraDatas(){}
	
	public static MascaraDatas getFormatarDatas() {
		return formatarDatas;
	}

	public void focusGained(FocusEvent e) {
		try {
			Text t = (Text) e.getSource();
			t.selectAll();
		} catch (Exception exe) {

		}
	}

	public void focusLost(FocusEvent e) {
		Text t = (Text) e.getSource();
		String texto = extractNumbersFromText(t.getText());
		Date data = null;
		int size = texto.length();
		if (size == 6) {
			data = converterTextoParaData(texto, ddMMyy);
		} else if (size == 8) {
			data = converterTextoParaData(texto, ddMMyyyy);
		}
		t.setText(data == null ? "" : formatoUsuario.format(data));
	}

	private String extractNumbersFromText(String text) {
		StringBuilder sb = new StringBuilder();
		for(Character c : text.toCharArray()){
			if(Character.isDigit(c)){
				sb.append(c);
			}
		} 
		
		return sb.toString();
	}

	private Date converterTextoParaData(String data, SimpleDateFormat s) {
		try {
			return s.parse(data);
		} catch (Exception e) {
			return null;
		}
	}

}
