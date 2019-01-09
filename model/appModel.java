package model;

public class appModel {
	public CorderDAO orderdao;
	public PriceDAO pricedao;
	public CustomerDAO customerdao;
	public MenuDAO menudao;
	
	public appModel(){
		orderdao = new CorderDAO();
		pricedao = new PriceDAO();
		customerdao = new CustomerDAO();
		menudao = new MenuDAO();
	}
}
