package spl;

import java.awt.Color;
import java.util.Random;

public class EasyEnemy extends EnemyBall{
	int direction=0;
	Random rand;
	public EasyEnemy(int x, int y) 
	{
		super(x,y);
		rand = new Random();
		this.x=x;
		this.y=y;
		this.vx=1;
		this.vy=1;
		this.radias=20;
		this.color=Color.GREEN;
	}
	
	private void changeDirection()
	{
		if(rand.nextInt(10)>5)	direction+=1;
		else if(rand.nextInt(10)>1)	direction+=3;
		else direction+=2;
		direction=direction%4;
	}
	
	public void step()
	{
		if(canGo(direction))
		{
			if(rand.nextInt(500)<3)	changeDirection();
			else	move(direction);
		}
		else changeDirection();
	}
}
