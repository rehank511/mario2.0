package starter;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class MenuPane extends GraphicsPane {
	private MainApplication program; // you will use program to get access to
										// all of the GraphicsProgram calls
	private GImage menu;

	public MenuPane(MainApplication app) {
		super();
		program = app;
		menu = new GImage("menu.png", 0, 0);
		menu.setSize(850, 600);
	}

	@Override
	public void showContents() {
		program.add(menu);
	}

	@Override
	public void hideContents() {
		program.remove(menu);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			program.switchToMenu();
		}
	}
}
