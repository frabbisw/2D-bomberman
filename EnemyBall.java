package spl;

import java.awt.Color;

public abstract class EnemyBall extends Ball
{
	public EnemyBall(int x, int y)
	{
		super(x,y);
		this.x=x;
		this.y=y;
	}	
}