package br.com.dgimenes.jsonlibscomparison.entity.jaxb;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class JAXBDateAdapter extends XmlAdapter<String, Date>{

	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public Date unmarshal(String formattedDate) throws Exception {
		return formatter.parse(formattedDate);
	}

	@Override
	public String marshal(Date date) throws Exception {
		return formatter.format(date);
	}
}
