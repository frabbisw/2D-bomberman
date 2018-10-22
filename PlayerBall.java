package spl;

import java.awt.Color;
import java.util.ArrayList;

public class PlayerBall extends Ball
{	
	boolean up=false,down=false,left=false,right=false,space=false;
	int power=1,time=3;
	ArrayList <Ball> playerList;
	
	public PlayerBall(int x, int y)
	{
		super(x,y);
		this.x=x;
		this.y=y;
		this.vx=1;
		this.vy=1;
		this.radias=20;
		this.color=Color.BLUE;
	}
	public void step()
	{
		if(left&canGoLeft())	moveLeft();
		else if(right&canGoRight())	moveRight();
		else if(up&canGoUp())	moveUp();
		else if(down&canGoDown())	moveDown();
		if(space)
		{
			createBomb();
			space=false;
		}
	}
	private void createBomb() 
	{
		BombThread thread = new BombThread(new Bomb(this.x, this.y, obstacles, power, time, playerList));
		
		thread.start();
	}
	public boolean moved()
	{
		return (left||right||up||down);
	}
	public void addPlayerList(ArrayList <Ball> playerList)
	{
		this.playerList=playerList;
	}
}