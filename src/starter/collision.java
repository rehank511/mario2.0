//package starter;
//
//public class collision {
//	public boolean collideRight, collideLeft, collideTop, collideBottom;
//	public boolean collision, Right, Left, Top, Bottom;
//	public void collision(Platform[] p) {
//		for (int i = 0; i < p.length; i++) {
//			collideTop = false;
//			collideBottom = false;
//			collideLeft = false;
//			collideRight = false;
//			if ((getMariobottom().getBounds()).intersects(p[i].getTop().getBounds())) {
//				onground++;
//
//				collideTop = true;
//				if (global.vertVelocity > 0)
//					global.vertVelocity = 0;
//				moveMario(0, p[i].getGround().getY() - Mario.getY() - HEIGHT);
//			}
//			if ((Mariotop.getBounds()).intersects(p[i].getBottom().getBounds())) {
//				collideBottom = true;
//				if (global.vertVelocity < 0)
//					global.vertVelocity = 0;
//				moveMario(0, p[i].getTop().getY() - Mario.getY() + p[i].getGround().getHeight());
//
//			}
//			if ((Marioright.getBounds()).intersects(p[i].getLeft().getBounds())) {
//				collideLeft = true;
//				if (global.horizVelocity > 0)
//					global.horizVelocity = 0;
//				moveMario(p[i].getGround().getX() - Mario.getX() - Mario.getWidth(), 0);
//			}
//			if ((Marioleft.getBounds()).intersects(p[i].getRight().getBounds())) {
//				collideRight = true;
//				if (global.horizVelocity < 0)
//					global.horizVelocity = 0;
//				moveMario(p[i].getGround().getX() + p[i].getGround().getWidth() - Mario.getX(), 0);
//			}
//		}
//	}
//	
//	public void collision(Enemies[] p) {
//		for (int i = 0; i < p.length; i++) {
//			if (p != null) {
//				collideTop = false;
//				collideBottom = false;
//				collideLeft = false;
//				global.collideRight = false;
//				if ((getMariobottom().getBounds()).intersects(p[i].getTop().getBounds())) {
//					onground++;
//
//					collideTop = true;
//					if (global.vertVelocity > 0)
//						global.vertVelocity = 0;
//					moveMario(0, p[i].getGoombaImg().getY() - Mario.getY() - HEIGHT);
//					global.vertVelocity -= global.jumpSpeed / 2;
//					p[i].DeleteGoomba();
//				}
//				if ((Mariotop.getBounds()).intersects(p[i].getBottom().getBounds())) {
//					collideBottom = true;
//					if (global.vertVelocity < 0)
//						global.vertVelocity = 0;
//					moveMario(0, p[i].getTop().getY() - Mario.getY() + p[i].getGoombaImg().getHeight());
//
//				}
//				if ((Marioright.getBounds()).intersects(p[i].getLeft().getBounds())) {
//					collideLeft = true;
//					if (global.horizVelocity > 0)
//						global.horizVelocity = 0;
//					moveMario(p[i].getGoombaImg().getX() - Mario.getX() - Mario.getWidth(), 0);
//				}
//				if ((Marioleft.getBounds()).intersects(p[i].getRight().getBounds())) {
//					global.collideRight = true;
//					if (global.horizVelocity < 0)
//						global.horizVelocity = 0;
//					moveMario(p[i].getGoombaImg().getX() + p[i].getGoombaImg().getWidth() - Mario.getX(), 0);
//				}
//			}
//		}
//	}
//	
//	public void collisionPipe(Platform[] pipe) {
//		for (int i = 0; i < pipe.length; i++) {
//			if ((getMariobottom().getBounds()).intersects(pipe[i].getTop().getBounds())) {
//				onground++;
//				collideTop = true;
//				if (global.vertVelocity > 0)
//					global.vertVelocity = 0;
//			} else
//				collideTop = false;
//			if ((Marioleft.getBounds()).intersects(pipe[i].getRight().getBounds())) {
//				collideBottom = true;
//				if (global.vertVelocity < 0)
//					global.vertVelocity = 0;
//			} else
//				collideBottom = false;
//			if ((Marioright.getBounds()).intersects(pipe[i].getLeft().getBounds())) {
//				if (global.horizVelocity > 0)
//					global.horizVelocity = 0;
//			}
//		}
//
//	}
//}
