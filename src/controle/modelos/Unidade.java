package controle.modelos;

import java.util.List;

import com.google.common.collect.ImmutableList;

public enum Unidade {
	KILOS, UNIDADES, CAIXA;
	
	public static List<Unidade> asList(){
		return ImmutableList.of(KILOS, UNIDADES, CAIXA);
	}
}
