package application;

public class FarmObjects {
	private String title;
	private int x_axis;
	private int y_axis;
	private int length;
	private int width;
	private int height;
	
	public FarmObjects(String title,int x_axis,int y_axis,int length, int width,int height,double marketVal,String parentName) {
		this.title = title;
		this.x_axis = x_axis;
		this.y_axis= y_axis;
		this.length = length;
		this.width = width;
		this.height = height;
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
	

}
