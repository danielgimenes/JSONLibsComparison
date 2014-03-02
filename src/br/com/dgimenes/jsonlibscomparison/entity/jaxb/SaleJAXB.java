package br.com.dgimenes.jsonlibscomparison.entity.jaxb;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "sale")
@XmlType(propOrder = { "id", "title", "price", "deadline", "productName" })
public class SaleJAXB {
    private Integer id;
    private String title;
    private String productName;
    private BigDecimal price; // pattern "U$ 0.00"
    private Date deadline; // pattern "dd/MM/yyyy"
    
    public SaleJAXB() {
	}

	public SaleJAXB(Integer id, String title, String productName, BigDecimal price, Date deadline) {
		super();
		this.id = id;
		this.title = title;
		this.productName = productName;
		this.price = price;
		this.deadline = deadline;
	}

	@XmlAttribute(required = true)
	public Integer getId() {
		return id;
	}
	
	@XmlElement(required = true)
	public String getTitle() {
		return title;
	}

	@XmlElement(required = true)
	public String getProductName() {
		return productName;
	}

	@XmlElement(required = true)
	@XmlJavaTypeAdapter(JAXBBigDecimalAdapter.class)
	public BigDecimal getPrice() {
		return price;
	}

	@XmlAttribute(required = true)
	@XmlJavaTypeAdapter(JAXBDateAdapter.class)
	public Date getDeadline() {
		return deadline;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		SaleJAXB other = (SaleJAXB) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SaleJAXB [id=" + id + "]";
	}
}
