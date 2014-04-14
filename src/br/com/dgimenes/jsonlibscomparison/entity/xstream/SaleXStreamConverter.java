/*
 * The MIT License (MIT)
 * Copyright (c) 2014 Daniel Costa Gimenes
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this 
 * software and associated documentation files (the "Software"), to deal in the Software 
 * without restriction, including without limitation the rights to use, copy, modify, 
 * merge, publish, distribute, sublicense, and/or sell copies of the Software, and to 
 * permit persons to whom the Software is furnished to do so, subject to the following 
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR 
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE 
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR 
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER 
 * DEALINGS IN THE SOFTWARE.
 */
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