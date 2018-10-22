package spl;
import java.awt.Color;
import java.util.ArrayList;

public class Bomb {
	int x1,x2,y1,y2,radias,power,time;
	Color color;
	ObstaclesInFrame obstacles;
	Obstacle tmp = new PermanentObstacle();
	ArrayList <Ball> playerList;
	
	public Bomb(int x1, int y1, ObstaclesInFrame obstacles,int power, int time, ArrayList <Ball> playerList)
	{
		this.x1=x1;
		this.y1=y1;
		this.radias=30;
		this.x2=x1+radias;
		this.y2=y1+radias;
		this.obstacles=obstacles;
		this.power=power;
		this.time=time;
		this.playerList=playerList;
	}
	public void destroy()
	{
		int a=x1/tmp.width;
		int b=y1/tmp.height;
		obstacles.destroy(a-1,b);
		obstacles.destroy(a+1,b);
		obstacles.destroy(a,b-1);
		obstacles.destroy(a,b+1);
		for(int i=0; i<playerList.size(); i++)
		{
			Ball tm = playerList.get(i);
			if((Math.abs(tm.x/tmp.width-a)<2)&(Math.abs(tm.y/tmp.height-b)<2))
			{
				tm.death();
				playerList.remove(i);
			}
		}
	}
}