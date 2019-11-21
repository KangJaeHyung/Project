package RhythmGame4;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
abstract class basic{
	abstract void doIt();
}
public class KeyListener extends KeyAdapter {
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(DynamicBeat.game==null) {
			return;
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			System.out.println("new Beat(StartTime + gap *"+ ((DynamicBeat.game.gameMusic.getTime()-1000)/40-3)+", \"a\",\"short\",0),");
			DynamicBeat.game.pressA();
		}else if(e.getKeyCode() == KeyEvent.VK_D) {
		
			System.out.println("new Beat(StartTime + gap *"+ ((DynamicBeat.game.gameMusic.getTime()-1000)/40-3)+", \"d\",\"short\",0),");
			DynamicBeat.game.pressD();
		}else if(e.getKeyCode() == KeyEvent.VK_J) {
		
			System.out.println("new Beat(StartTime + gap *"+ ((DynamicBeat.game.gameMusic.getTime()-1000)/40-3)+", \"j\",\"short\",0),");
			DynamicBeat.game.pressJ();
		}else if(e.getKeyCode() == KeyEvent.VK_K) {
		
			System.out.println("new Beat(StartTime + gap *"+ ((DynamicBeat.game.gameMusic.getTime()-1000)/40-3)+", \"i\",\"short\",0),");
			DynamicBeat.game.pressI();
		}else if(e.getKeyCode() == KeyEvent.VK_L) {
		
			System.out.println("new Beat(StartTime + gap *"+ ((DynamicBeat.game.gameMusic.getTime()-1000)/40-3)+", \"l\",\"short\",0),");
			DynamicBeat.game.pressL();
			
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(DynamicBeat.game==null) {
			return;
		}
	if(e.getKeyCode() == KeyEvent.VK_A) {
	
		DynamicBeat.game.releaseA();
		}else if(e.getKeyCode() == KeyEvent.VK_D) {
		
			DynamicBeat.game.releaseD();
		}else if(e.getKeyCode() == KeyEvent.VK_J) {
			
			DynamicBeat.game.releaseJ();
		}else if(e.getKeyCode() == KeyEvent.VK_I) {
			
			DynamicBeat.game.releaseI();
		}else if(e.getKeyCode() == KeyEvent.VK_L) {
			
			DynamicBeat.game.releaseL();
		}
	}
	
	
}
