package com.code;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

public class PaginationHelper {
	
	private List list;
	private int limit;
	private int temp;
	private int totalCount;
	private int pageCount;
	private int itemCount;
	private int pageItemCount;
	private int pageIndex;
	
	
	  public void setParams(List list,int limit) {		 
		 this.limit = limit;
		 this.list=list;		  	  
	  }
	
	  public List getList() {		 
		 return list;
		  	  
	  }
	
	  public int getLimit() {		 
		 return limit;		  	  
	  }
	 
	
	  public void itemCount() {
		 itemCount = list.size();
		 System.out.println("itemCount  " + itemCount); 		  
	  }
  
	  
	  public void pageCount() {
		  int temp = itemCount/limit;
		  if(temp!=0)
		  {pageCount = temp+1;
		  System.out.println("pageCount  " + pageCount);}
		  else
		  System.out.println("pageCount is 1"); 				  
	  }
	  
	  
	  
	  public void pageItemCount(int count) {		  
			  if(count<pageCount)
			      System.out.println("PageItemCount of page is " + limit );
			  else if(count==pageCount)
			  { int h = itemCount-((pageCount-1)*limit);
				  System.out.println("PageItemCount of page is " + h );}
			  else
				  System.out.println("PageItemCount of page is -1" );		  		  
	  }
	  
	  
	  public void pageIndex(int index) {
	if(index<=itemCount) {
		  int temp = index/limit;
		  int rem = index%limit;
		  if(temp==0)
			  System.out.println("PageIndex is 1");
		  else if(rem==0)
			  System.out.println("PageIndex is " + temp );
			 else if(rem<limit) {
				   int page = temp+1;
				  System.out.println("PageIndex is " + page );
				  }			    			  	  
	  }
	  else
		   System.out.println("PageIndex is -1" );	
	  }   
	}
	

