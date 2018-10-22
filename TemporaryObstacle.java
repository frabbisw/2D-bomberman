package spl;
	
import java.awt.Color;
	
public class TemporaryObstacle extends Obstacle{
	public TemporaryObstacle()
	{
		super();
	}
	public TemporaryObstacle(int x1, int y1)
	{
		super(x1,y1);
		type=2;
		this.color=Color.YELLOW;
	}
}	