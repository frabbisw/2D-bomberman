package spl;
				
import java.awt.Color;
						
public abstract class Obstacle {
	int x1=0,y1=0,width,height,x2=0,y2=0,type;
	Color color;
	
	public Obstacle()
	{
		this.width=40;
		this.height=40;
	}
	public Obstacle(int x1, int y1)
	{
		this.width=40;
		this.height=40;
		this.x1=x1;
		this.y1=y1;
		this.x2=x1+width;
		this.y2=y1+height;
	}
}