package spl;

public class BombThread extends Thread{
	Bomb b;
	public BombThread(Bomb b)
	{
		this.b=b;
	}
	public void run()
	{
		try {
			Thread.sleep(b.time*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		b.destroy();
	}
}
