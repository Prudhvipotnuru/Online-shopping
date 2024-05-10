package dal;

import java.util.ArrayList;

import models.Customer;
import models.ProductsList;

public interface StoreDAL {
	public ProductsList getProducts(String category, String sort, Integer pages);

	public int setOrderDetails(Integer total, Integer cid);

	// public Integer getCid(String email);

	// public String getCname(String email);

	public void setProductDetails(Integer oid, ProductsList arrayList);

	public ArrayList<Integer> getgst(ArrayList<Integer> hsn);

	public Customer verifyUser(String cemail, String cpassword);

	public Boolean registerUser(Customer c);

	public ArrayList<Integer> getPriceList(ArrayList<Integer> keysList, ArrayList<Integer> gst);

	public ArrayList<Integer> getUnserviceableProductIds(ArrayList<Integer> pList, int pincode);

	public int getCouponValue(String dcpn_code);

}
