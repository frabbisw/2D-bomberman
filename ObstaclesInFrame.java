package spl;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObstaclesInFrame 
{
	Obstacle arrayOfObstacles [][], tmp;
	boolean [][] obstacleExisted; 
	Random rand = new Random();
	public int CH,CW,tmpNumber=30;
	int FrameWidth,FrameHeight,error=50;
	ArrayList <Obstacle> garbages = new <Obstacle> ArrayList();
	
	
	public ObstaclesInFrame(int FrameWidth, int FrameHeight, int error)
	{
		this.FrameWidth=FrameWidth;
		this.FrameHeight=FrameHeight;
		this.error=error;
		
		tmp = new PermanentObstacle();
		CH=FrameHeight/tmp.height;
		CW=FrameWidth/tmp.width;
		
		arrayOfObstacles = new Obstacle[16][16];
		obstacleExisted = new boolean [FrameWidth+50][FrameHeight+50];
		//number_of_obstacles+=2;
		ObstacleGenerator();
	}
	
	private void ObstacleGenerator()
	{
		for(int i=0; i<CW; i++)
		{
			for(int j=0; j<CH; j++)
			{
				if((i%2==1)&(j%2==1))	
					arrayOfObstacles[i][j] = new PermanentObstacle(i*tmp.width, j*tmp.height);
			}
		}
		
		for(int i=0; i<tmpNumber; i++)
		{
			int tmp_a = rand.nextInt(14)+1, tmp_b=rand.nextInt(14)+1;
			while(arrayOfObstacles[tmp_a][tmp_b]!=null)
			{
				tmp_a = rand.nextInt(14);
				tmp_b=rand.nextInt(14);
			}
			arrayOfObstacles[tmp_a][tmp_b] = new TemporaryObstacle(tmp_a*tmp.width, tmp_b*tmp.height);
		}
		
		for(int i=0; i<=FrameWidth; i++)
		{
			for(int j=0; j<=FrameHeight; j++)
			{
				obstacleExisted[i][j]=false;
			}
		}
		
		for(int i=0; i<CW; i++)
		{
			for(int j=0; j<CH; j++)
			{
				if(arrayOfObstacles[i][j]!=null)	
				{
					for(int k=i*arrayOfObstacles[i][j].width; k<(i+1)*arrayOfObstacles[i][j].width; k++)
					{
						for(int l=j*arrayOfObstacles[i][j].height; l<(j+1)*arrayOfObstacles[i][j].height; l++)
						{
							obstacleExisted[k][l]=true;
						}
					}	
				}
			}
		}
	}
	
	public Obstacle getObstacle(int x, int y)
	{
		if(arrayOfObstacles[x][y]!=null)	return arrayOfObstacles[x][y];
		return null;
	}
	
	public void destroy(int a, int b)
	{
		if(a>=0&b>=0)
		{
			if(arrayOfObstacles[a][b]!=null)
			{
				if(arrayOfObstacles[a][b].type==2)
				{
					garbages.add(arrayOfObstacles[a][b]);
					arrayOfObstacles[a][b]=null;
					for(int i=a*tmp.width; i<(a+1)*tmp.width; i++)
					{
						for(int j=b*tmp.height; j<(b+1)*tmp.height; j++)
						{
							obstacleExisted[i][j]=false;
						}
					}
				}
			}
		}
	}
	
	public void clearGarbages()
	{
		if(garbages.size()!=0)	
		{
			while(!garbages.isEmpty())
			{
				garbages.remove(0);
			}
		}	
	}
	
	public boolean canSet(int x, int y)
	{
		return !obstacleExisted[x][y];
	}
}