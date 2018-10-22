package spl;

public class UpdateThread extends Thread{
	MediamEnemy enemyM;
	int tm;
	public UpdateThread(MediamEnemy m, int tm)
	{
		enemyM=m;
		this.tm=tm;
	}
	public void run()
	{
		while(true)
		{
			enemyM.update();
			try {
				Thread.sleep(tm);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}