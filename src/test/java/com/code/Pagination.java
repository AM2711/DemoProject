package com.code;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.testng.annotations.AfterMethod;

public class Pagination {
	
	  private int limit;
	  private int index;
	  private String obj="";
	  private int count;
	  private int pageCount;
	  ArrayList list = new ArrayList();
	  Scanner scanner =new Scanner(System.in);
	  PaginationHelper helper = new PaginationHelper() ;
 
  @BeforeTest
  public void readInput() {
	  
	  System.out.println("Enter the limit per page");
	  limit = scanner.nextInt();
	  System.out.println("Enter the count of the objects");
	  count = scanner.nextInt();
	  System.out.println("Enter the objects");
	  for(int i=0; i<count; i++)  
	  {   
		  obj = scanner.next();
		  list.add(obj);
	  }  	  
  }
  
  @Test(priority=0)
  public void paramsDef() {	
		helper.setParams(list,limit);		  
  }
  
  
  @Test(priority=1)
  public void test() {		
		helper.itemCount();
        helper.pageCount();		  
  }
  
  @Test(priority=2)
  public void readItemCount() {  
	  System.out.println("Enter the page Item Count");
	  pageCount = scanner.nextInt();
	  helper.pageItemCount(pageCount);	
  }

  @Test(priority=3)
  public void readIndex() {  
	  System.out.println("Enter the index of the object");
	  index = scanner.nextInt();
	  helper.pageIndex(index);	  
  }
}
