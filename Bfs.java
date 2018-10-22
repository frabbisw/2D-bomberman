package spl;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Bfs {
	Node source,destination;
	Node [][] arrayOfNodes;
	Obstacle obstacle;
	ObstaclesInFrame obstacles;
	Ball target, follower;
	ArrayList <Integer> returnList;
	
	int sx,sy,dx,dy;
	int width=600, height=600, ex=5, ey=5, wlimit=width, hlimit=height, wspace, hspace;
	
	public Bfs(ObstaclesInFrame obstacles, Ball target, Ball follower)
	{
		this.obstacles=obstacles;
		this.target=target;
		this.follower=follower;
		obstacle = new TemporaryObstacle();
		
		width=obstacles.FrameWidth;
		height=obstacles.FrameHeight;
		hspace=obstacle.height;
		wspace=obstacle.width;
		wlimit=width/wspace;
		hlimit=height/hspace;
		
		ex=follower.vx;
		ey=follower.vy;
		
		sx=follower.x-(follower.x%wspace)+ex;
		sy=follower.y-(follower.y%hspace)+ey;
		dx=target.x-(target.x%wspace)+ex;
		dy=target.y-(target.y%hspace)+ey;
		
		arrayOfNodes=new Node[width][height];
		createNodes();
		LinkNodes();
		BfsVisit();
		arrayOfNodes=null;
		//Visit();
		makeTrack();
	}
	private void createNodes()
	{
		for(int i=0; i<wlimit; i++)
		{
			for(int j=0; j<hlimit; j++)
			{
				arrayOfNodes[i][j] = new Node(i*wspace+ex,j*hspace+ey);
			}
		}
	}
	private void LinkNodes()
	{
		for(int i=0; i<wlimit; i++)
		{
			for(int j=0; j<hlimit; j++)
			{
				if(i>0)
					if(obstacles.canSet(arrayOfNodes[i-1][j].x, arrayOfNodes[i-1][j].y))	arrayOfNodes[i][j].addNeighbor(arrayOfNodes[i-1][j]);
				if(i<wlimit-1)
					if(obstacles.canSet(arrayOfNodes[i+1][j].x, arrayOfNodes[i+1][j].y))	arrayOfNodes[i][j].addNeighbor(arrayOfNodes[i+1][j]);
				if(j>0)
					if(obstacles.canSet(arrayOfNodes[i][j-1].x, arrayOfNodes[i][j-1].y))	arrayOfNodes[i][j].addNeighbor(arrayOfNodes[i][j-1]);
				if(j<hlimit-1)
					if(obstacles.canSet(arrayOfNodes[i][j+1].x, arrayOfNodes[i][j+1].y))	arrayOfNodes[i][j].addNeighbor(arrayOfNodes[i][j+1]);
									
				if(arrayOfNodes[i][j].x==sx&arrayOfNodes[i][j].y==sy)	source=arrayOfNodes[i][j];
				if(arrayOfNodes[i][j].x==dx&arrayOfNodes[i][j].y==dy)	destination=arrayOfNodes[i][j];
				arrayOfNodes[i][j].visited=false;
			}
		}
	}
	private void BfsVisit()
	{
		Queue <Node> queue = new LinkedList <Node>();
		
		destination.parent=null;
		queue.add(destination);
		while(!queue.isEmpty())
		{
			Node a = queue.remove();
			a.visited=true;
			
			while(!a.neighbors.isEmpty())
			{
				Node b = a.neighbors.remove();
				if(!b.visited)
				{
					b.parent=a;
					queue.add(b);
				}
			}
		}
	}
	
	private void makeTrack()
	{
		returnList = new ArrayList<Integer>();
		
		Node tail = source;		
		while(tail.parent!=null)
		{
			if(tail.x==tail.parent.x)
			{
				if(tail.y>tail.parent.y)	
					for(int j=0; j<wspace; j++)
					{
						returnList.add(1);
					}
				else if(tail.y<tail.parent.y)	
					for(int j=0; j<wspace; j++)
					{
						returnList.add(3);
					}
			}
			else if(tail.y==tail.parent.y)
			{
				if(tail.x>tail.parent.x)	
					for(int j=0; j<hspace; j++)
					{
						returnList.add(0);
					}
				else if(tail.x<tail.parent.x)	
					for(int j=0; j<hspace; j++)
					{
						returnList.add(2);
					}
			}
			tail=tail.parent;
		}
		
		if(!returnList.isEmpty())
		{
				for(int i=0; i<(follower.x-source.x); i++)
				{
					if(returnList.get(0)==2)	returnList.remove(0);
					else returnList.add(0,0);
				}
			
				for(int i=0; i<(follower.y-source.y); i++)
				{
					if(returnList.get(0)==3)	returnList.remove(0);
					else returnList.add(0,1);
				}
		}
		else
		{
			for(int i=0; i<(follower.x-source.x); i++)
			{
				returnList.add(0,0);
			}
			for(int i=0; i<(follower.y-source.y); i++)
			{
				returnList.add(0,1);
			}
		}
		
	}
	public ArrayList <Integer> getTrack()
	{
		return returnList;
	}
}