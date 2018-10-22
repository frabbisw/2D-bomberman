package spl;

public class ClearRectSingle {
	boolean change = false;
	Ball b;
	int x1,x2,y1,y2;
	public ClearRectSingle()
	{
		
	}
	public void setArea(Ball b)
	{
		this.b=b;
		x1=b.x;
		x2=b.radias;
		y1=b.y;
		y2=b.radias;
	}
	public void reset() 
	{
		this.change=false;
	}
	public void set() 
	{
		this.change=true;
	}
	public boolean needToUpdate()
	{
		return this.change;
	}
}
