package dip.clever.web;

public class Option extends Tag{
	public Option() {
		head = new Head() {
			public boolean selected = false;
		};
	}
	
	public static void main(String[] args){
		Tag tag = new Option();
		
		System.out.println(tag);
	}
}
