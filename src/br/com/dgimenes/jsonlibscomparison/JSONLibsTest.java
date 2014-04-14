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
package br.com.dgimenes.jsonlibscomparison;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.dgimenes.jsonlibscomparison.entity.jaxb.SaleJAXB;
import br.com.dgimenes.jsonlibscomparison.entity.jaxb.SalesJAXB;
import br.com.dgimenes.jsonlibscomparison.entity.simple.Sale;
import br.com.dgimenes.jsonlibscomparison.entity.simple.Sales;
import br.com.dgimenes.jsonlibscomparison.entity.xstream.SaleXStream;
import br.com.dgimenes.jsonlibscomparison.entity.xstream.SalesXStream;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

public class JSONLibsTest {
	public static void main(String[] args) throws JSONException, JsonGenerationException, JsonMappingException, IOException {
		// initialize entities
		Sales sales = createSales();
		SalesJAXB salesAnnotatedWithJAXB = createSalesAnnotatedWithJAXB(sales);
		SalesXStream salesAnnotatedWithXStream = createSalesAnnotatedWithXStream(sales);

		// JSON.org
		String jsonResultUsingJSONORG = null;
		jsonResultUsingJSONORG = normalizeOutput(encodeWithJSONORG(sales));

		// JACKSON (with JAX-B)
		String jsonResultUsingJACKSONJAXB = null;
		jsonResultUsingJACKSONJAXB = normalizeOutput(encodeWithJACKSONJAXB(salesAnnotatedWithJAXB));

		// XSTREAM
		String jsonResultUsingXSTREAM = null;
		jsonResultUsingXSTREAM = normalizeOutput(encodeWithXStream(salesAnnotatedWithXStream));

		// GSON
		String jsonResultUsingGSON = null;
		jsonResultUsingGSON = normalizeOutput(encodeWithGSON(sales));

		System.out.println("JSON.ORG\n" + jsonResultUsingJSONORG + "\n");
		System.out.println("JACKSON\n" + jsonResultUsingJACKSONJAXB + "\n");
		System.out.println("GSON\n" + jsonResultUsingGSON + "\n");
		System.out.println("XSTREAM\n" + jsonResultUsingXSTREAM + "\n");

		// Testing results
		if (jsonResultUsingJSONORG.equals(jsonResultUsingJACKSONJAXB) && jsonResultUsingJSONORG.equals(jsonResultUsingXSTREAM)
				&& jsonResultUsingJSONORG.equals(jsonResultUsingGSON)) {
			System.out.println("TEST OK. All outputs are equal!");
		} else {
			System.err.println("TEST ERROR. Outputs are different!");
		}
	}

	private static String normalizeOutput(String output) {
		return output.replaceAll("\n", "").replaceAll("  ", "").replaceAll("\": ", "\":").trim();
	}

	private static Sales createSales() {
		Calendar cal = Calendar.getInstance();
		List<Sale> saleList = new ArrayList<Sale>();
		cal.set(2014, 03, 15);
		saleList.add(new Sale(1, "Prepare for SCJP! Super discount on Java book 'SCJP Sun Certified Programmer for Java 6 Exam 310-065'",
				"SCJP Sun Certified Programmer for Java 6 Exam 310-065", new BigDecimal(25.20D), cal.getTime()));
		cal.set(2014, 03, 05);
		saleList.add(new Sale(2, "Be a better programmer! 'The Clean Coder' book for half the price!",
				"The Clean Coder: A Code of Conduct for Professional Programmers", new BigDecimal(15.50D), cal.getTime()));
		cal.set(2014, 03, 30);
		saleList.add(new Sale(3, "Now you can learn Test Driven Development! 'Test Driven Development: By Example' book with 25% off!",
				"Test Driven Development: By Example", new BigDecimal(30.00D), cal.getTime()));
		return new Sales(saleList);
	}

	private static SalesJAXB createSalesAnnotatedWithJAXB(Sales sales) {
		List<SaleJAXB> saleList = new ArrayList<SaleJAXB>();
		for (Sale sale : sales.getSales()) {
			saleList.add(new SaleJAXB(sale.getId(), sale.getTitle(), sale.getProductName(), sale.getPrice(), sale.getDeadline()));
		}
		return new SalesJAXB(saleList);
	}

	private static SalesXStream createSalesAnnotatedWithXStream(Sales sales) {
		List<SaleXStream> saleList = new ArrayList<SaleXStream>();
		for (Sale sale : sales.getSales()) {
			saleList.add(new SaleXStream(sale.getId(), sale.getTitle(), sale.getProductName(), sale.getPrice(), sale.getDeadline()));
		}
		return new SalesXStream(saleList);
	}

	// JSON.org
	private static String encodeWithJSONORG(Sales sales) throws JSONException {
		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		jsonObj.put("sales", jsonArray);
		for (Sale sale : sales.getSales()) {
			JSONObject jsonSaleObj = new JSONObject();
			jsonSaleObj.put("id", sale.getId());
			jsonSaleObj.put("title", sale.getTitle());
			jsonSaleObj.put("productName", sale.getProductName());
			jsonSaleObj.put("price", new DecimalFormat("U$ 0.00").format(sale.getPrice()));
			jsonSaleObj.put("deadline", new SimpleDateFormat("dd/MM/yyyy").format(sale.getDeadline()));
			jsonArray.put(jsonSaleObj);
		}
		return jsonObj.toString();
	}

	// JACKSON (with JAX-B)
	private static String encodeWithJACKSONJAXB(SalesJAXB salesAnnotatedWithJAXB) throws JsonGenerationException, JsonMappingException, IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("deprecation")
		AnnotationIntrospector introspector = new JaxbAnnotationIntrospector();
		// enables "JAXB annotations only" mode
		mapper.setAnnotationIntrospector(introspector);
		mapper.writeValue(outputStream, salesAnnotatedWithJAXB);
		return outputStream.toString();
	}

	// XSTREAM
	private static String encodeWithXStream(SalesXStream salesAnnotatedWithXStream) {
		XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
		xstream.processAnnotations(SalesXStream.class);
		xstream.alias("sales", List.class);
		return xstream.toXML(salesAnnotatedWithXStream.getSales());
	}

	// GSON
	private static String encodeWithGSON(Sales sales) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.disableHtmlEscaping();
		gsonBuilder.setDateFormat("dd/MM/yyyy");
		gsonBuilder.registerTypeAdapter(BigDecimal.class, new JsonSerializer<BigDecimal>() {
			@Override
			public JsonElement serialize(BigDecimal decimal, Type typeOfSrc, JsonSerializationContext context) {
				return new JsonPrimitive(new DecimalFormat("U$ 0.00").format(decimal));
			}
		});
		Gson gson = gsonBuilder.create();
		return gson.toJson(sales);
	}
}
