package spl;

import java.awt.Color;

public class PermanentObstacle extends Obstacle{
	public PermanentObstacle()
	{
		super();
	}
	public PermanentObstacle(int x1, int y1)
	{
		super(x1,y1);
		type=1;
		this.color=Color.DARK_GRAY;
	}
}