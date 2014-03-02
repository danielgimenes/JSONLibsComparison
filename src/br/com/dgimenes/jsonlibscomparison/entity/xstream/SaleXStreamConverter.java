package br.com.dgimenes.jsonlibscomparison.entity.xstream;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.path.PathTrackingWriter;

public class SaleXStreamConverter implements Converter {
	@Override
	public boolean canConvert(@SuppressWarnings("rawtypes") Class clazz) {
		return (clazz.getName().equals(SaleXStream.class.getName()));
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader arg0, UnmarshallingContext arg1) {
		// we're not going to use this
		return null;
	}

	@Override
	public void marshal(Object obj, HierarchicalStreamWriter genericWriter, MarshallingContext context) {
		PathTrackingWriter writer = (PathTrackingWriter) genericWriter;
		SaleXStream sale = (SaleXStream) obj;
		writer.startNode("id", Integer.class);
		writer.setValue(sale.getId().toString());
		writer.endNode();
		writer.startNode("title");
		writer.setValue(sale.getTitle());
		writer.endNode();
		writer.startNode("price");
		writer.setValue(new DecimalFormat("U$ 0.00").format(sale.getPrice()));
		writer.endNode();
		writer.startNode("deadline");
		writer.setValue(new SimpleDateFormat("dd/MM/yyyy").format(sale.getDeadline()));
		writer.endNode();
		writer.startNode("productName");
		writer.setValue(sale.getProductName());
		writer.endNode();
	}
}