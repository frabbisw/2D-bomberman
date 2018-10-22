package spl;

import java.awt.Color;
import java.util.ArrayList;

public class MediamEnemy extends EnemyBall{
	ArrayList <Integer> track;
	Bfs bfs;
	Ball target;
	public MediamEnemy(int x, int y) 
	{
		super(x, y);
		this.x=x;
		this.y=y;
		this.vx=1;
		this.vy=1;
		this.radias=20;
		this.color=Color.RED;
	}
	public void setTarget(Ball b)
	{
		target = b;
	}
	public Ball getTarget()
	{
		return this.target;
	}
	public void update()
	{
		track=null;
		bfs = new Bfs(obstacles, target, this);
		track = bfs.getTrack();
	}
	public void step() 
	{
		int direction=5;
		if(track!=null) if(!track.isEmpty())	direction = track.remove(0);
		move(direction);
	}
}
