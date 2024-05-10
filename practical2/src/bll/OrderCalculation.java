package bll;

import java.util.HashMap;

import models.OrderCalculationResult;
import models.Product;

public interface OrderCalculation {
	public OrderCalculationResult getTotal(HashMap<Integer, Integer> productCount, HashMap<Integer, Product> productSet,
			int dcpn_value, String dcpn_code);
}
