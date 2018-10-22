package spl;
	
import java.awt.Color;
	
public abstract class Ball {
	public int x=0,y=0,vx=0,vy=0,radias=0;
	Color color;
	ObstaclesInFrame obstacles;
	boolean alive=true;
	
	public Ball(int x, int y)
	{
		this.x=x;
		this.y=y;
	}
	
	public boolean isAlive()
	{
		return alive;
	}
	public void death()
	{
		this.alive=false;
	}
	
	public void setObstacles(ObstaclesInFrame obstacles)
	{
		this.obstacles=obstacles;
	}
	
	public void setPosition(int x, int y)
	{
		this.x=x;
		this.y=y;
	}
	public void moveLeft()
	{
		x=x-vx;
	}
	public void moveRight()
	{
		x=x+vx;
	}
	public void moveUp()
	{
		y=y-vy;
	}
	public void moveDown()
	{
		y=y+vy;
	}
	
	public boolean canGoLeft() 
	{
		if(x>vx)
			if(obstacles.canSet(x-vx, y)&obstacles.canSet(x-vx, y+radias))	return true;
		return false;
	}
	
	public boolean canGoRight() {
		if(x+vx+radias<obstacles.FrameWidth)
			if(obstacles.canSet(x+vx+radias, y)&obstacles.canSet(x+vx+radias, y+radias))	return true;
		return false;
	}
	
	public boolean canGoUp() {
		if(y>vy)
			if(obstacles.canSet(x, y-vy)&obstacles.canSet(x+radias, y-vy))	return true;
		return false;
	}
	
	public boolean canGoDown() {
		if(y+radias+vy<obstacles.FrameHeight)
			if(obstacles.canSet(x, y+vy+radias)&obstacles.canSet(x+radias, y+vy+radias))	return true;
		return false;
	}
	
	public boolean canGo(int direction)
	{
		if(direction==0)	return canGoLeft();
		else if(direction==1)	return canGoUp();
		else if(direction==2)	return canGoRight();
		else if(direction==3)	return canGoDown();
		
		return false;
	}
	public void move(int direction)
	{
		if(direction==0)	moveLeft();
		if(direction==1)	moveUp();
		if(direction==2)	moveRight();
		if(direction==3)	moveDown();
	}
	
	public abstract void step();
	
}