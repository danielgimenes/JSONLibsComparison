package br.com.dgimenes.jsonlibscomparison.entity.xstream;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class SalesXStream {
	private List<SaleXStream> sales;

	public SalesXStream() {
	}

	public SalesXStream(List<SaleXStream> sales) {
		super();
		this.sales = sales;
	}

	public List<SaleXStream> getSales() {
		return sales;
	}

	public void setSales(List<SaleXStream> sales) {
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
		SalesXStream other = (SalesXStream) obj;
		if (sales == null) {
			if (other.sales != null)
				return false;
		} else if (!sales.equals(other.sales))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SalesXStream [sales=" + sales + "]";
	}
}
