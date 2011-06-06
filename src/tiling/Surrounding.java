package tiling;

import tiling.Cardinal.Horizontal;
import tiling.Cardinal.Vertical;

public class Surrounding {
	private boolean[][] occupied = new boolean[3][3];
	
	public boolean get(Horizontal h, Vertical v) {
		//System.out.println("get " + h + " " + v + " " + ((occupied[h.i+1][v.i+1]) ? "occupied" : "free"));
		return occupied[h.i+1][v.i+1];
	}
	
	public void set(Horizontal h, Vertical v, boolean b) {
		//System.out.println("set " + h + " " + v + " " + ((b) ? "occupied" : "free"));
		occupied[h.i+1][v.i+1] = b;
	}
}
