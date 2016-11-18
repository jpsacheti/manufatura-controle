package controle.uteis;

import java.math.BigDecimal;

import org.mongodb.morphia.converters.SimpleValueConverter;
import org.mongodb.morphia.converters.TypeConverter;
import org.mongodb.morphia.mapping.MappedField;

public class BigDecimalMorphiaConverter extends TypeConverter implements SimpleValueConverter {

	public BigDecimalMorphiaConverter() {
		super(BigDecimal.class);
	}

	@Override
	public Object encode(Object value, MappedField optionalExtraInfo) {
		BigDecimal val = (BigDecimal) value;
		if (val == null)
			return BigDecimal.ZERO;
		return val.toPlainString();
	}

	@Override
	public Object decode(Class<?> targetClass, Object fromDBObject, MappedField optionalExtraInfo) {
		if (fromDBObject == null)
			return BigDecimal.ZERO;
		BigDecimal dec = new BigDecimal(fromDBObject.toString());
		return dec;
	}

}