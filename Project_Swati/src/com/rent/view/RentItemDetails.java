package com.rent.view;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mysql.jdbc.Blob;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class RentItemDetails implements Serializable {


	private static final long serialVersionUID = 603111218953173401L;
	
	private long rentItemPk;
    private String title;
    private String author;
    private String isbn;
    private String edition;
    private InputStream imageUrl;
    private String price;
    private String availableFrom;
    private String ownerId;
    private String imageBase;
    private String status;
    private String renter_id;

    public RentItemDetails() {
    	rentItemPk = 0l;
    	title = "";
    	author = "";
    	isbn = "";
    	edition = "";
    	imageUrl = null;
    	price = "";
    	availableFrom = "";
    	ownerId="";
    	imageBase = "";
    	status = "";
    	renter_id= "";
    }

	public long getRentItemPk() {
		return rentItemPk;
	}

	public void setRentItemPk(long rentItemPk) {
		this.rentItemPk = rentItemPk;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public InputStream getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(InputStream imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAvailableFrom() {
		return availableFrom;
	}

	public void setAvailableFrom(String availableFrom) {
		this.availableFrom = availableFrom;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getImageBase() {
		return imageBase;
	}

	public void setImageBase(String imageBase) {
		this.imageBase = imageBase;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
		
	}
	
	public void setStatusValue(String available_date) {
		if (null != available_date && !available_date.isEmpty()) {
			try {
				Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(available_date);
				if (date1.before(new Date())) {
					this.status = "Available";
				} else {
					this.status = "Not Available";
				}
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			this.status = "Not Available";
		}
	}

	public String getRenter_id() {
		return renter_id;
	}

	public void setRenter_id(String renter_id) {
		this.renter_id = renter_id;
	}
			
		
    
}
