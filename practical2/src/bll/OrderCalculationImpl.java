package bll;

import java.util.ArrayList;
import java.util.HashMap;

import dal.ProductFactory;
import models.OrderCalculationResult;
import models.Product;
import models.ProductsList;

public class OrderCalculationImpl implements OrderCalculation {

	public OrderCalculationResult getTotal(HashMap<Integer, Integer> productCount, HashMap<Integer, Product> productSet,
			int dcpn_value, String dcpn_code) {
		// TODO Auto-generated method stub
		int totalInclGst = 0;
		int totalShipCharge = 0;
		int totalShipChargeGst = 0;
		int totalDiscount = 0;
		int totalProductsPrice = 0;
		int order_total = 0;
		ProductsList products = new ProductsList();
		ArrayList<Integer> hsn = new ArrayList<>();
		for (Product pr : productSet.values()) {
			hsn.add(pr.getHsn());
			products.add(pr);
		}
		ArrayList<Integer> gstList = ProductFactory.getProductsDALImpl().getgst(hsn);
		for (int i = 0; i < products.size(); i++) {

			int productQty = productCount.get(products.get(i).getPid());
			Product p = products.get(i);

			// gst inclusive for product
			int productPriceWithoutGst = (int) (((double) (p.getPrice())
					/ (double) (1 + ((double) gstList.get(i) / 100))) * (double) productQty);
			totalInclGst += (p.getPrice() * productQty - productPriceWithoutGst);
			// total shipping charge
			int shipCharge = p.getShipping_charge() * productQty;
			totalShipCharge += shipCharge;
			// total shipping charge exclusive gst
			totalShipChargeGst += (int) ((double) shipCharge * ((double) gstList.get(i) / 100));
			// total discount
			totalDiscount += p.getDiscount() * productQty;
			totalProductsPrice += p.getPrice() * productQty;

		}
		System.out.println("value from ordercal:" + dcpn_value);
		order_total = totalProductsPrice + totalShipCharge + totalShipChargeGst - totalDiscount - dcpn_value;// total
																												// before
																												// applying
		// coupon
		OrderCalculationResult ocr = new OrderCalculationResult(totalInclGst, totalShipCharge, totalShipChargeGst,
				totalDiscount, totalProductsPrice, order_total, dcpn_value, dcpn_code);
		return ocr;

	}

}
