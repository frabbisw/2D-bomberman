package spl;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

public class Game extends JFrame 
{
	Random rand = new Random();
	int width,height,error=50;
	PlayerBall player;
	EasyEnemy enemy,enemy2;
	MediamEnemy enemy3;
	ObstaclesInFrame obstacles;
	PlayerThread thread1, thread2, thread3, thread4;
	UpdateThread upThread;
	ArrayList <Ball> playerList;
	int cnt=0,mnt=0;
	boolean cheat1=false,cheat2=false, cheat3=false;
	
	public Game()
	{		
		width=600;
		height=600;
		obstacles = new ObstaclesInFrame(width, height, error);
		
		playerList = new ArrayList <Ball>();
		
		player = new PlayerBall(0,0);
		player.setPosition(player.vx, player.vy);
		player.setObstacles(obstacles);
		thread1 = new PlayerThread(player);
		thread1.start();
		playerList.add(player);
		
		enemy = new EasyEnemy(0, 0);
		enemy.setPosition(enemy.vx, enemy.vy+300);
		enemy.setObstacles(obstacles);
		thread2 = new PlayerThread(enemy);
		thread2.start();
		playerList.add(enemy);
		
		enemy2 = new EasyEnemy(0, 0);
		enemy2.setPosition(enemy2.vx, enemy2.vy+300);
		enemy2.setObstacles(obstacles);
		thread3 = new PlayerThread(enemy2);
		thread3.start();
		playerList.add(enemy2);
		
		enemy3 = new MediamEnemy(0, 0);
		enemy3.setPosition(enemy3.vx+300, enemy3.vy);
		enemy3.setObstacles(obstacles);
		enemy3.setTarget(player);
		upThread = new UpdateThread(enemy3, 1000);
		upThread.start();
		thread4 = new PlayerThread(enemy3);
		thread4.start();
		playerList.add(enemy3);
		
		player.addPlayerList(playerList);
		
		setTitle("TAB");
		setLayout(new FlowLayout());
		setVisible(true);
		setSize(width+2*error, height+2*error);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.CYAN);
		
		addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e) 
			{
				super.keyPressed(e);
				
				int keyCode = e.getKeyCode();
				
				if(keyCode == KeyEvent.VK_LEFT)	player.left=true;
				else if(keyCode == KeyEvent.VK_RIGHT)	player.right=true; 
				else if(keyCode == KeyEvent.VK_UP)	player.up=true;
				else if(keyCode == KeyEvent.VK_DOWN)	player.down=true;
				if(keyCode == KeyEvent.VK_SPACE)	player.space=true;
				
				if(keyCode == KeyEvent.VK_CONTROL)	cheat1=true;
				if(keyCode == KeyEvent.VK_R)	cheat2=true;
				if(keyCode == KeyEvent.VK_Q)	cheat3=true;
				//if(keyCode == KeyEvent.VK_Q)	cheat3=true;
				
				if(cheat1&cheat2)	if(enemy3.getTarget()==null)	enemy3.setTarget(player);
				else if(cheat1&cheat2)	if(enemy3.getTarget() instanceof EnemyBall)	enemy3.setTarget(player);
				else if(cheat1&cheat2)	if(enemy3.getTarget() instanceof PlayerBall)	enemy3.setTarget(enemy);
				
				
			}
			
			public void keyReleased(KeyEvent e)
			{
				super.keyReleased(e);
				int keyCode = e.getKeyCode();
				if(keyCode==KeyEvent.VK_LEFT)	player.left=false;
				if(keyCode==KeyEvent.VK_RIGHT)	player.right=false;
				if(keyCode==KeyEvent.VK_UP)	player.up=false;
				if(keyCode==KeyEvent.VK_DOWN)	player.down=false;
				
				if(keyCode == KeyEvent.VK_CONTROL)	cheat1=false;
				if(keyCode == KeyEvent.VK_R)	cheat2=false;
				if(keyCode == KeyEvent.VK_Q)	cheat3=false;
			}
		});
	}
		
	public void paint(Graphics g)
	{		
		
		
		
		g.setColor(Color.BLACK);
		g.drawRect(error-1, error-1, width+1, height+1);
		
		/*g.setColor(Color.DARK_GRAY);
		for(int i=0; i<obstacles.CW; i++)
		{
			for(int j=0; i<obstacles.CH; j++)
			{
				Obstacle tmp = obstacles.getObstacle(i, j);
				if(tmp!=null)
				{
					g.fillRect(tmp.x1+error, tmp.y1+error, tmp.width, tmp.height);
				}
			}
		}*/
		
		for(int i=0; i<obstacles.CW; i++)
		{
			for(int j=0; j<obstacles.CH; j++)
			{
				Obstacle tmp = obstacles.getObstacle(i, j);
				if(tmp!=null)
				{
					g.setColor(tmp.color);
					g.fillRect(tmp.x1+error, tmp.y1+error, tmp.width, tmp.height);
				}
			}
		}
		if(player.isAlive())
		{
			g.setColor(player.color);
			g.fillOval(player.x+error, player.y+error, player.radias, player.radias);
		}
		if(enemy.isAlive())
		{
			g.setColor(enemy.color);
			g.fillOval(enemy.x+error, enemy.y+error, enemy.radias, enemy.radias);
		}
		if(enemy2.isAlive())
		{	
			g.setColor(enemy2.color);
			g.fillOval(enemy2.x+error, enemy2.y+error, enemy2.radias, enemy2.radias);
		}
		if(enemy3.isAlive())
		{
			g.setColor(enemy3.color);
			g.fillOval(enemy3.x+error, enemy3.y+error, enemy3.radias, enemy3.radias);
		}
		if(thread1.cr.needToUpdate())	
		{
			Ball b = thread1.cr.b;
			g.clearRect(b.x+error-b.vx*2, b.y+error-b.vy*2, b.radias+4*b.vx, b.radias+4*b.vy);
		}
		
		if(thread2.cr.needToUpdate())	
		{
			Ball b = thread2.cr.b;
			g.clearRect(b.x+error-b.vx*2, b.y+error-b.vy*2, b.radias+4*b.vx, b.radias+4*b.vy);
		}
		
		if(thread3.cr.needToUpdate())	
		{
			Ball b = thread3.cr.b;
			g.clearRect(b.x+error-b.vx*2, b.y+error-b.vy*2, b.radias+4*b.vx, b.radias+4*b.vy);
		}
		
		if(thread4.cr.needToUpdate())	
		{
			Ball b = thread4.cr.b;
			g.clearRect(b.x+error-b.vx*2, b.y+error-b.vy*2, b.radias+4*b.vx, b.radias+4*b.vy);
		}
		
		if(obstacles.garbages!=null)
		{
			for(int i=0; i<obstacles.garbages.size(); i++)
			{
				Obstacle tmp = obstacles.garbages.get(i);
				g.clearRect(tmp.x1+error, tmp.y1+error, tmp.width, tmp.height);
			}	
			obstacles.clearGarbages();
		}
		repaint();
	}
}