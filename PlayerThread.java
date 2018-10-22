package spl;
	
public class PlayerThread extends Thread{
	Ball b;
	ClearRectSingle cr;
	
	public PlayerThread(Ball b)
	{
		this.b=b;
		cr=new ClearRectSingle();
	}
	
	
	public void run()
	{
		while(b.isAlive())
		{
			try 
			{
				cr.setArea(b);
				cr.set();
				b.step();
				
				Thread.sleep(1);
				cr.reset();
				Thread.sleep(10);
				//cr.set();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}