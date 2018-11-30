package starter;

import acm.graphics.*;

import acm.program.*;
import acm.util.*;
import java.awt.*;

import java.io.*;
import java.util.*;

public class LevelCreator extends GraphicsProgram {
	static String[][][] Levels = new String[5][13][210];
	private Scanner x;

	public int levelCount() {
		return Levels.length;
	}
	
	public void openFile() {
		try {
			x = new Scanner(new File("ABCustomLevels.txt"));
		} catch (Exception e) {
			System.out.println("could not find file");
		}
	}

	public void readFile(String[][][] Map) {
		int col = 0;
		int row = 0;
		int level = 0;
		while (x.hasNext()) {
			for (level = 0; level < Map.length; level++) {
				String a = x.next();
				System.out.print(a);
				System.out.print("\n");
				for (row = 0; row < Map[0].length; row++) {
					for (col = 0; col < Map[0][row].length; col++) {
						a = x.next();
						Map[level][row][col] = a;
						System.out.print(a);
					}
					System.out.print("\n");
				}
			}
		}
	}

	public void closeFile() {
		x.close();
	}

	public String[][][] getLevels() {
		return Levels;
	}

	public Platform[] LoadLevel(int Level) {
		int col = 0;
		int row = 0;
		int a = 0;
		int thickness = 3;
		int size = 50;
		int arrSize = 0;
		for (row = 0; row < Levels[Level].length; row++) {
			for (col = 0; col < Levels[Level][row].length; col++) {
				if (Levels[Level][row][col].charAt(0) == 'P') {
					arrSize++;
				}
			}
		}
		Platform[] platform = new Platform[arrSize];
		for (int i = 0; i < platform.length; i++) {
			platform[i] = new Platform();
		}
		for (row = 0; row < Levels[Level].length; row++) {
			for (col = 0; col < Levels[Level][row].length; col++) {
				if (Levels[Level][row][col].charAt(0) == 'P') {
					platform[a].InitilizePlatform(col * size, row * size, size, size, thickness);
					a++;
				}
			}
		}
		return platform;
	}

	public Platform[] LoadLevelGround(int Level) {
		int col = 0;
		int row = 0;
		int a = 0;
		int thickness = 3;
		int size = 50;
		int arrSize = 0;
		for (row = 0; row < Levels[Level].length; row++) {
			for (col = 0; col < Levels[Level][row].length; col++) {
				if (Levels[Level][row][col].charAt(0) == 'B') {
					arrSize++;
				}
			}
		}
		Platform[] platform = new Platform[arrSize];
		for (int i = 0; i < platform.length; i++) {
			platform[i] = new Platform();
		}
		for (row = 0; row < Levels[Level].length; row++) {
			for (col = 0; col < Levels[Level][row].length; col++) {
				if (Levels[Level][row][col].charAt(0) == 'B') {
					platform[a].InitilizePlatform(col * size, row * size, size, size, thickness);
					platform[a].changeToGround();
					a++;
				}
			}
		}
		return platform;
	}

	public Enemies[] LoadLevelGoomba(int Level) {
		int col = 0;
		int row = 0;
		int a = 0;
		int thickness = 3;
		int size = 50;
		int arrSize = 0;
		for (row = 0; row < Levels[Level].length; row++) {
			for (col = 0; col < Levels[Level][row].length; col++) {
				if (Levels[Level][row][col].charAt(0) == 'G') {
					arrSize++;
				}
			}
		}
		Enemies[] Goomba = new Enemies[arrSize];
		for (int i = 0; i < Goomba.length; i++) {
			Goomba[i] = new Enemies();
		}
		for (row = 0; row < Levels[Level].length; row++) {
			for (col = 0; col < Levels[Level][row].length; col++) {
				if (Levels[Level][row][col].charAt(0) == 'G') {
					Goomba[a].InitilizeGoomba(col * size, row * size, size, size, thickness);
					a++;
				}
			}
		}
		return Goomba;
	}

	public void run() {
		LevelCreator r = new LevelCreator();
		r.openFile();
		r.readFile(Levels);
		r.closeFile();

		Platform[] platform = r.LoadLevel(4);
		Platform[] ground = r.LoadLevelGround(4);
		Enemies[] goomba = r.LoadLevelGoomba(4);

		for (int i = 0; i < platform.length; i++) {
			add(platform[i].getPlatImg());
		}
		for (int i = 0; i < ground.length; i++) {
			add(ground[i].getPlatImg());
		}
		for (int i = 0; i < goomba.length; i++) {
			add(goomba[i].getGoombaImg());
		}
	}
}
