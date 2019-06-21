//putBookInfo.java
import java.lang.math;
import java.util.*;
class Book{
	int pages;
	double price;
	void setPages(int pages){
		this.pages = pages;
	}
	int getPages(){
		return pages;
	}
	void setPrice(double price){
		this.price = price;
	}
	double getPrice(){
		return price;
	}
}

class Novel extends Book{
	Novel(){
		System.out.println("Name:Trinity");
	}
	static String category = "Scince fiction";
}

class Magazine extends Book{
	Magazine(){
		System.out.println("Name:TheWeekend");
	}
	static String number = "CN44-0003";
}

class Textbook extends Book{
	Textbook(){
		System.out.println("Name:DataStructure");
	}
	static String reader = "University students";
}

public class putBookInfo{
	public static void main(String[] args){
		int rand;
		rand = (int)((Math.random())*3);
		Book book = null;
		switch (rand){
			case 0:
			    book = new Novel();
				book.setPages(1024);
				book.setPrice(198.98);
				break;
			case 1:
			    book = new Magazine();
				book.setPages(128);
				book.setPrice(24.99);
				break;
			case 2:
			    book = new Textbook();
				book.setPages(512);
				book.setPrice(49.99);
				break;
	    }
        System.out.println("Pages:"+book.getPages()+"\nPrice:"+book.getPrice());
		switch (rand){
			case 0:
			    System.out.println("Category:"+Novel.category);
				break;
			case 1:
			    System.out.println("Number:"+Magazine.number);
				break;
			case 2:
			    System.out.println("Reader:"+Textbook.reader);
		}
	}
}