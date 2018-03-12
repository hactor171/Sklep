/* 
 *  Program SklepApplication
 *  Autor: Roman Kovalchuk
 *   Data: 28 pażdziernika 2016 r.
 */

import java.io.Serializable;


public class Towar implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

	private String nazwa;               // nazwa towaru
	private double price;              // cena towaru
	private int ilosc;                 // ilość towaru
    
	
	
    Towar(String nazwa){
    	this.nazwa = nazwa;
    	price=0;
    	ilosc=0;
    	
    }



	public String getNazwa() {
		return nazwa;
	}



	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}



	public double getPrice() {
	 
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}
	



	public int getIlosc() {
		return ilosc;
	}

	public void number(int num) throws Exception {
		
		ilosc -= num;
	}
	
	

	public void  setIlosc(int ilosc) {
		this.ilosc = ilosc;
	}
	
	
	
	
    
	public String toString(){
		return String.format("   Nazwa Towaru:%s - Cena:%szł - Ilosc-[%s] ", nazwa, price, ilosc);
	}  
    






















}