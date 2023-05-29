package app.entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import app.enums.OrderStatus;

public class Order {
	
	private Long id;
	private Double latitude;
	private Double longitude;
	private Instant moment;
	private OrderStatus stauts;
	
	private List<Product> products = new ArrayList<>();

	public Order(Long id, Double latitude, Double longitude, Instant moment, OrderStatus stauts) {
		super();
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.moment = moment;
		this.stauts = stauts;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public OrderStatus getStauts() {
		return stauts;
	}

	public void setStauts(OrderStatus stauts) {
		this.stauts = stauts;
	}

	public List<Product> getProducts() {
		return products;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + ", moment=" + moment
				+ ", stauts=" + stauts + "]";
	}
	
}
