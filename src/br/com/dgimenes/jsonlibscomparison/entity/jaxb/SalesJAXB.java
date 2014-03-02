package br.com.dgimenes.jsonlibscomparison.entity.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sales")
public class SalesJAXB {
	private List<SaleJAXB> sales;

	public SalesJAXB() {
	}

	public SalesJAXB(List<SaleJAXB> sales) {
		super();
		this.sales = sales;
	}

	public List<SaleJAXB> getSales() {
		return sales;
	}

	public void setSales(List<SaleJAXB> sales) {
		this.sales = sales;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sales == null) ? 0 : sales.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalesJAXB other = (SalesJAXB) obj;
		if (sales == null) {
			if (other.sales != null)
				return false;
		} else if (!sales.equals(other.sales))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SalesJAXB [sales=" + sales + "]";
	}
}
