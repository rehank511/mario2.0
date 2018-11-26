package starter;

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.TimerTask;

import javax.swing.Timer;

public class Level extends GraphicsProgram{
	public Platform[][] levelPlatform;
	public Platform[] Ground;
	public Platform[][] Pipe;


//	public GImage[] platarr;
//	public GImage[] pipearr;
	
	
//	GImage background;
//	GImage[] ground;
	GImage flagImage;
	GImage castleImage;

	public void level1()
	{
	
//
//				flagImage = new GImage("flag.png",0,0);
//				flagImage.setImage("flag.png");
//				flagImage.setSize(25,80);
//				flagImage.setBounds(8570, 100, 50, 500);
//				castleImage = new GImage("castle.png",0,0);
//			
//				castleImage.setSize(30,30);
//				castleImage.setBounds(8725, 350, 250, 250);



		levelPlatform = new Platform[1][50];
		for (int a = 0; a < levelPlatform.length; a++)
			for (int i = 0; i < levelPlatform[0].length; i++) {
				levelPlatform[a][i] = new Platform();
			}
		Ground = new Platform[10];
		for (int i = 0; i < Ground.length; i++) {
			Ground[i] = new Platform();

		}

		Pipe = new Platform[50][10];
		for (int a = 0; a < Pipe.length; a++)
			for (int i = 0; i < Pipe[0].length; i++) {
				Pipe[a][i] = new Platform();
			}


		levelPlatform[0][0].InitilizePlatform(400,400,50,50,3);
		levelPlatform[0][1].InitilizePlatform(600,400,50,50,3);
		levelPlatform[0][2].InitilizePlatform(650,400,50,50,3);
		levelPlatform[0][3].InitilizePlatform(700,400,50,50,3);
		levelPlatform[0][4].InitilizePlatform(750,400,50,50,3);
		levelPlatform[0][5].InitilizePlatform(800,400,50,50,3);
		levelPlatform[0][6].InitilizePlatform(850,400,50,50,3);
		levelPlatform[0][7].InitilizePlatform(700,200,50,50,3);

		levelPlatform[0][8].InitilizePlatform(3400,400,50,50,3);
		levelPlatform[0][9].InitilizePlatform(3500,200,50,50,3);
		levelPlatform[0][10].InitilizePlatform(3550,200,50,50,3);
		levelPlatform[0][11].InitilizePlatform(3600,200,50,50,3);
		levelPlatform[0][12].InitilizePlatform(3650,200,50,50,3);
		levelPlatform[0][13].InitilizePlatform(3700,200,50,50,3);
		levelPlatform[0][14].InitilizePlatform(3750,200,50,50,3);
		levelPlatform[0][15].InitilizePlatform(3800,200,50,50,3);


		levelPlatform[0][16].InitilizePlatform(4000,200,50,50,3);
		levelPlatform[0][17].InitilizePlatform(4100,200,50,50,3);
		levelPlatform[0][18].InitilizePlatform(4150,200,50,50,3);
		levelPlatform[0][19].InitilizePlatform(4200,200,50,50,3);
		levelPlatform[0][20].InitilizePlatform(4250,200,50,50,3);
		levelPlatform[0][21].InitilizePlatform(4250,400,50,50,3);

		levelPlatform[0][22].InitilizePlatform(4800,400,50,50,3);
		levelPlatform[0][23].InitilizePlatform(4850,400,50,50,3);

		levelPlatform[0][24].InitilizePlatform(5400,400,50,50,3);
		levelPlatform[0][25].InitilizePlatform(5600,400,50,50,3);
		levelPlatform[0][26].InitilizePlatform(5800,400,50,50,3);


		levelPlatform[0][27].InitilizePlatform(6200,200,50,50,3);
		levelPlatform[0][28].InitilizePlatform(6250,200,50,50,3);
		levelPlatform[0][29].InitilizePlatform(6300,200,50,50,3);
		levelPlatform[0][30].InitilizePlatform(6350,200,50,50,3);
		levelPlatform[0][31].InitilizePlatform(6600,200,50,50,3);
		levelPlatform[0][32].InitilizePlatform(6650,200,50,50,3);
		levelPlatform[0][33].InitilizePlatform(6700,200,50,50,3);
		levelPlatform[0][34].InitilizePlatform(6750,200,50,50,3);
		levelPlatform[0][35].InitilizePlatform(6650,400,50,50,3);
		levelPlatform[0][36].InitilizePlatform(6700,400,50,50,3);


		levelPlatform[0][37].InitilizePlatform(6700,400,50,50,3);
		levelPlatform[0][38].InitilizePlatform(6700,400,50,50,3);
		levelPlatform[0][39].InitilizePlatform(7300,400,50,50,3);
		levelPlatform[0][40].InitilizePlatform(7350,400,50,50,3);



		levelPlatform[0][41].InitilizePlatform(8000,550,50,50,3);
		levelPlatform[0][42].InitilizePlatform(8050,500,50,50,3);
		levelPlatform[0][43].InitilizePlatform(8100,450,50,50,3);
		levelPlatform[0][44].InitilizePlatform(8150,400,50,50,3);
		levelPlatform[0][45].InitilizePlatform(8200,350,50,50,3);
		levelPlatform[0][46].InitilizePlatform(8250,300,50,50,3);
		levelPlatform[0][47].InitilizePlatform(8300,250,50,50,3);
		levelPlatform[0][48].InitilizePlatform(8350,250,50,50,3);

		
//		platarr = new GImage[100];
//		
//		platarr[0]= new GImage("Plat1 .png",400,400);	
//		platarr[1]= new GImage("Plat1 .png",600,400);	
//		platarr[2]= new GImage("Plat1 .png",650,400);	
//		platarr[3]= new GImage("Plat1 .png",700,400);	
//		platarr[4]= new GImage("Plat1 .png",750,400);	
//		platarr[5]= new GImage("Plat1 .png",800,400);	
//		platarr[6]= new GImage("Plat1 .png",850,400);	
//		platarr[7]= new GImage("Plat1 .png",700,200);	
//		
//		platarr[8]= new GImage("Plat1 .png",3400,400);	
//		platarr[9]= new GImage("Plat1 .png",3500,200);	
//		platarr[10]= new GImage("Plat1 .png",3550,200);	
//		platarr[11]= new GImage("Plat1 .png",3600,200);	
//		platarr[12]= new GImage("Plat1 .png",3650,200);	
//		platarr[13]= new GImage("Plat1 .png",3700,200);	
//		platarr[14]= new GImage("Plat1 .png",3750,200);	
//		platarr[15]= new GImage("Plat1 .png",3800,200);	
//		
//		platarr[16]= new GImage("Plat1 .png",4000,200);	
//		platarr[17]= new GImage("Plat1 .png",4100,200);	
//		platarr[18]= new GImage("Plat1 .png",4150,200);	
//		platarr[19]= new GImage("Plat1 .png",4200,200);	
//		platarr[20]= new GImage("Plat1 .png",4250,200);	
//		platarr[21]= new GImage("Plat1 .png",4250,400);	
//		
//		platarr[22]= new GImage("Plat1 .png",4800,400);	
//		platarr[23]= new GImage("Plat1 .png",4850,400);	
//		
//		platarr[24]= new GImage("Plat1 .png",5400,400);	
//		platarr[25]= new GImage("Plat1 .png",5600,400);	
//		platarr[26]= new GImage("Plat1 .png",5800,400);	
//		
//		platarr[27]= new GImage("Plat1 .png",6200,200);	
//		platarr[28]= new GImage("Plat1 .png",6250,200);	
//		platarr[29]= new GImage("Plat1 .png",6300,200);	
//		platarr[30]= new GImage("Plat1 .png",6350,200);	
//		platarr[31]= new GImage("Plat1 .png",6600,200);	
//		platarr[32]= new GImage("Plat1 .png",6650,200);	
//		platarr[33]= new GImage("Plat1 .png",6700,200);	
//		platarr[34]= new GImage("Plat1 .png",6750,200);	
//		platarr[35]= new GImage("Plat1 .png",6650,400);	
//		platarr[36]= new GImage("Plat1 .png",6700,400);	
//		
//		platarr[37]= new GImage("Plat1 .png",6700,400);	
//		platarr[38]= new GImage("Plat1 .png",6700,400);	
//		platarr[39]= new GImage("Plat1 .png",7300,400);	
//		platarr[40]= new GImage("Plat1 .png",7350,400);	
//		
//		platarr[41]= new GImage("Plat1 .png",8000,550);	
//		platarr[42]= new GImage("Plat1 .png",8050,500);	
//		platarr[43]= new GImage("Plat1 .png",8100,450);	
//		platarr[44]= new GImage("Plat1 .png",8150,400);	
//		platarr[45]= new GImage("Plat1 .png",8200,350);	
//		platarr[46]= new GImage("Plat1 .png",8250,300);	
//		platarr[47]= new GImage("Plat1 .png",8300,250);	
//		platarr[48]= new GImage("Plat1 .png",8350,250);	
//		
//		platarr[49]= new GImage("Plat1 .png",8050,550);	
//		platarr[50]= new GImage("Plat1 .png",8100,550);	
//		platarr[51]= new GImage("Plat1 .png",8150,550);	
//		platarr[52]= new GImage("Plat1 .png",8200,550);	
//		platarr[53]= new GImage("Plat1 .png",8250,550);	
//		platarr[54]= new GImage("Plat1 .png",8300,550);	
//		platarr[55]= new GImage("Plat1 .png",8350,550);	
//		
//		platarr[56]= new GImage("Plat1 .png",8100,500);	
//		platarr[57]= new GImage("Plat1 .png",8150,500);	
//		platarr[58]= new GImage("Plat1 .png",8200,500);	
//		platarr[59]= new GImage("Plat1 .png",8250,500);	
//		platarr[60]= new GImage("Plat1 .png",8300,500);	
//		platarr[61]= new GImage("Plat1 .png",8350,500);	
//		
//		platarr[62]= new GImage("Plat1 .png",8150,450);	
//		platarr[63]= new GImage("Plat1 .png",8200,450);	
//		platarr[64]= new GImage("Plat1 .png",8250,450);	
//		platarr[65]= new GImage("Plat1 .png",8300,450);	
//		platarr[66]= new GImage("Plat1 .png",8350,450);	
//		
//		platarr[67]= new GImage("Plat1 .png",8200,400);	
//		platarr[68]= new GImage("Plat1 .png",8250,400);	
//		platarr[69]= new GImage("Plat1 .png",8300,400);	
//		platarr[70]= new GImage("Plat1 .png",8350,400);	
//		
//		platarr[71]= new GImage("Plat1 .png",8250,350);	
//		platarr[72]= new GImage("Plat1 .png",8300,350);	
//		platarr[73]= new GImage("Plat1 .png",8350,350);	
//		
//		platarr[74]= new GImage("Plat1 .png",8300,300);	
//		platarr[75]= new GImage("Plat1 .png",8350,300);	
//		
//		for(int i =0;i<76;i++) {
//		platarr[i].setSize(50,50);
//		}
		
		
//		pipearr = new GImage[6];
//		
//		pipearr[0]= new GImage("pipe.png",1000, 540);	
//		pipearr[1]= new GImage("pipe.png",1400, 520);	
//		pipearr[2]= new GImage("pipe.png",1800, 500);	
//		pipearr[3]= new GImage("pipe.png",2200, 480);	
//		pipearr[4]= new GImage("pipe.png",7100, 540);	
//		pipearr[5]= new GImage("pipe.png",7900, 540);	
//		
//		pipearr[0].setSize(60,60);
//		pipearr[1].setSize(60,80);
//		pipearr[2].setSize(60,100);
//		pipearr[3].setSize(60,120);
//		pipearr[4].setSize(60,60);
//		pipearr[5].setSize(60,60);
		
		
		
		//background = new GImage("bg.png", 0, 0);
		//background.setSize(850, 600);
		
//		background = new GImage("bg.png",0,0);
//		background.setImage("bg.png");
//		background.setSize(850, 600);
//		background.setBounds(0,0 ,850, 600);
		
		//ground = new GImage("ground.png", 0, 600);
		//ground.setSize(850, 100);
		
//		ground = new GImage[3];
//		ground[0] = new GImage("ground.png",0,0);
		//ground[0].setImage("ground.png");
		//ground[0].setSize(850, 1000);
//		ground[0].setBounds(0,600 ,850, 100);
		
		
		Ground[0].InitilizePlatform(0, 600,2800, 200, 3);
		Ground[1].InitilizePlatform(3000, 600,800, 200, 3);
		Ground[2].InitilizePlatform(4000, 600,7000, 200, 3);

		Pipe[0][0].InitilizePlatform(1000, 540, 60, 60, 3);
		Pipe[0][1].InitilizePlatform(1400, 520, 60, 80, 3);
		Pipe[0][2].InitilizePlatform(1800, 500, 60, 100, 3);
		Pipe[0][3].InitilizePlatform(2200, 480, 60, 120, 3);
		Pipe[0][4].InitilizePlatform(7100, 540, 60, 60, 3);
		Pipe[0][5].InitilizePlatform(7900, 540, 60, 60, 3);

		levelPlatform[0][49].InitilizePlatform(8550, 590,5, 5, 3);

	}


	
	 public void run()
	 {
		 level1();
//		 platpic();
	 }




}






