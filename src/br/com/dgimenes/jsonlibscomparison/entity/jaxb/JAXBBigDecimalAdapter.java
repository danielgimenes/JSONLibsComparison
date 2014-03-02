package br.com.dgimenes.jsonlibscomparison.entity.jaxb;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class JAXBBigDecimalAdapter extends XmlAdapter<String, BigDecimal> {

	private DecimalFormat formatter = new DecimalFormat("U$ 0.00");

	@Override
	public BigDecimal unmarshal(String formattedDecimal) throws Exception {
		return new BigDecimal(formatter.parse(formattedDecimal).doubleValue());
	}

	@Override
	public String marshal(BigDecimal decimal) throws Exception {
		return formatter.format(decimal);
	}
}
