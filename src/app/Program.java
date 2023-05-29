package app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import app.entities.Order;
import app.entities.Product;
import app.enums.OrderStatus;
import db.DB;

public class Program {

	public static void main(String[] args) throws SQLException {
		
		Connection conn = DB.getConnection();
	
		Statement st = conn.createStatement();
			
		ResultSet rs = st.executeQuery("select * from tb_product");
			
		while (rs.next()) {
			Product p = instantiateProduct(rs);
			System.out.println(p); //listando todos os produtos
		}
		System.out.println();
		
		ResultSet rs1 = st.executeQuery("select * from tb_order");
		
		while (rs1.next()) {
			Order order = instantiateOrder(rs1);
			System.out.println(order);  //listando todos os pedidos
			
		}
		System.out.println();
		
		ResultSet rs2 = st.executeQuery("SELECT * FROM tb_order "
				+ "INNER JOIN tb_order_product ON tb_order.id = tb_order_product.order_id "
				+ "INNER JOIN tb_product ON tb_product.id = tb_order_product.product_id");
		
		Map<Long, Order> map = new HashMap<>();
		Map<Long, Product> prods = new HashMap<>();
		
		while (rs2.next()) {
			
			Long orderId = rs2.getLong("order_id");
			
			if (map.get(orderId) == null) {
				Order order2 = instantiateOrder2(rs2);
				map.put(orderId, order2);
			}
			
			Long productId = rs2.getLong("product_id");
			if (prods.get(productId) == null) {
				Product p2 = instantiateProduct2(rs2);
				prods.put(productId, p2);
			}
			map.get(orderId).getProducts().add(prods.get(productId));
		}
		for (Long orderId : map.keySet()) {
			System.out.println(map.get(orderId));
			for (Product p : map.get(orderId).getProducts()) {
				System.out.println(p); //listando todos os produtos dentro de cada pedido
			}
			System.out.println();
		}
	}
	private static Order instantiateOrder(ResultSet rs) throws SQLException {
		Order order = new Order(rs.getLong("id"), rs.getDouble("latitude"), rs.getDouble("longitude"), rs.getTimestamp("moment").toInstant(), OrderStatus.values()[rs.getInt("status")]);
		return order;
	}
	private static Order instantiateOrder2(ResultSet rs) throws SQLException {
		Order order2 = new Order(rs.getLong("order_id"), rs.getDouble("latitude"), rs.getDouble("longitude"), rs.getTimestamp("moment").toInstant(), OrderStatus.values()[rs.getInt("status")]);
		return order2;
	}
	private static Product instantiateProduct(ResultSet rs) throws SQLException {
		Product p = new Product(rs.getLong("id"), rs.getString("name"), rs.getDouble("price"), rs.getString("description"), rs.getString("image_uri"));
		return p;
	}
	private static Product instantiateProduct2(ResultSet rs) throws SQLException {
		Product p2 = new Product(rs.getLong("product_id"), rs.getString("name"), rs.getDouble("price"), rs.getString("description"), rs.getString("image_uri"));
		return p2;
	}
}
