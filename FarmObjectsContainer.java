package application;

import java.util.ArrayList;

public class FarmObjectsContainer {
	private String title;
	private double cost;
	private int x_axis;
	private int y_axis;
	private int length;
	private int width;
	private int height;
	private boolean isParent;
	private String parentName;
	
	private ArrayList<FarmObjects> itemsCollection = new ArrayList<FarmObjects>();
	public FarmObjectsContainer(String title,int x_axis,int y_axis,int length, int width,int height, String parentName) {
		this.title = title;
		this.x_axis = x_axis;
		this.y_axis= y_axis;
		this.length = length;
		this.width = width;
		this.height = height;
		this.isParent = true;
		this.parentName = parentName;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getx_axis() {
		return x_axis;
	}
	public void setx_axis(int x_axis) {
		this.x_axis = x_axis;
	}
	public int gety_axis() {
		return y_axis;
	}
	public void sety_axis(int y_axis) {
		this.y_axis = y_axis;
	}
	public int getLength() {
		return length;
	}
	public void setItemsCollection(ArrayList<FarmObjects> itemsCollection) {
		this.itemsCollection = itemsCollection;
	}
	public boolean isParent() {
		return isParent;
	}
	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
	
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public ArrayList<FarmObjects> getItemsCollection() {
		return itemsCollection;
	}
	
	public void addItem(FarmObjects item) {
		itemsCollection.add(item);
	}
	
	
	
	@Override
	public String toString() {
		return this.title+","
				+this.cost+","
				+this.x_axis+","
				+this.y_axis+","
				+this.length+","
				+this.width+","
				+this.height+","
				+this.parentName+","
				+"itemContainer";
		
	}

}
