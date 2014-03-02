package br.com.dgimenes.jsonlibscomparison.entity.simple;

import java.math.BigDecimal;
import java.util.Date;

public class Sale {
    private Integer id;
    private String title;
    private BigDecimal price; // pattern "U$ 0.00"
    private Date deadline; // pattern "dd/MM/yyyy"
    private String productName;
    
    public Sale() {
	}

	public Sale(Integer id, String title, String productName, BigDecimal price, Date deadline) {
		super();
		this.id = id;
		this.title = title;
		this.productName = productName;
		this.price = price;
		this.deadline = deadline;
	}

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getProductName() {
		return productName;
	}

	public BigDecimal getPrice() {
		return price;
	}

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
		Sale other = (Sale) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sale [id=" + id + "]";
	}
}