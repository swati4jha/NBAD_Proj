package com.rent.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RentItemUser implements Serializable {


	private static final long serialVersionUID = 698223449323600936L;
	
	
	private long rentItemUserPk;
	private String renterId;
    private long itemPk;
    private Date dueDate;
    private String itemStatus;
    private BigDecimal amountDue;

    public RentItemUser() {
    	rentItemUserPk = 0l;
    	renterId = "";
    	itemPk = 0l;
    	dueDate = new Date();
    	itemStatus = "";
    	amountDue = new BigDecimal(0);
    }

	public RentItemUser(long rentItemUserPk, String renterId, long itemPk, Date dueDate, String itemStatus,
			BigDecimal amountDue) {
		super();
		this.rentItemUserPk = rentItemUserPk;
		this.renterId = renterId;
		this.itemPk = itemPk;
		this.dueDate = dueDate;
		this.itemStatus = itemStatus;
		this.amountDue = amountDue;
	}

	public long getRentItemUserPk() {
		return rentItemUserPk;
	}

	public void setRentItemUserPk(long rentItemUserPk) {
		this.rentItemUserPk = rentItemUserPk;
	}

	public String getRenterId() {
		return renterId;
	}

	public void setRenterId(String renterId) {
		this.renterId = renterId;
	}

	public long getItemPk() {
		return itemPk;
	}

	public void setItemPk(long itemPk) {
		this.itemPk = itemPk;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	public BigDecimal getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(BigDecimal amountDue) {
		this.amountDue = amountDue;
	}

  
}
