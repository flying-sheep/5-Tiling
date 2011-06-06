package tiling;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.*;
import javax.swing.*;

import tiling.Cardinal.Horizontal;
import tiling.Cardinal.ValueNotCoveredException;
import static tiling.Cardinal.Horizontal.*;
import tiling.Cardinal.Vertical;
import static tiling.Cardinal.Vertical.*;
import tiling.QuarterTiledImage.QuarteredTile;

public class Tiling {
	static final int TILESIDE = 0x20;
	
	public static void main(String[] args) throws ValueNotCoveredException {		
		final boolean[][] walldata = {
			{false, true,  false},
			{true,  true,  true},
			{false, false, false},
			{true,  true,  true},
			{true,  true,  true},
			{true,  true,  true}
		};
		
		final int X = walldata[0].length -1;
		final int Y = walldata   .length -1;
		
		final BufferedImage canvas = new BufferedImage((X+1)*TILESIDE, (Y+1)*TILESIDE, BufferedImage.TYPE_INT_ARGB);
		final QuarterTiledImage wall = new QuarterTiledImage(canvas, TILESIDE, TILESIDE);
		
		for (int y=0; y<=Y; y++)
			for (int x=0; x<=X; x++) {
				if (walldata[y][x]) {
					QuarteredTile tile = wall.new QuarteredTile(x, y); 
					
					final Surrounding occupied = new Surrounding();
					
					for (int dy=-1; dy<=1; dy++) {
						for (int dx=-1; dx<=1; dx++) {
							boolean o = true;
							
							try {
								o = walldata[y+dy][x+dx];
							
								if (dx==  LEFT.i) o &= x>0;
								if (dx== RIGHT.i) o &= x<X;
								if (dy==   TOP.i) o &= y>0;
								if (dy==BOTTOM.i) o &= y<Y;
							} catch (IndexOutOfBoundsException e) {
								o = false;
							}
							
							occupied.set(Horizontal.valueOf(dx), Vertical.valueOf(dy), o);
							
							if (dx != 0 || dy != 0)
								System.out.print(o?"█":"░");
							else
								System.out.print("▒");
						}
						System.out.println();
					}
					
					for (Horizontal h : new Horizontal[] {LEFT, RIGHT})
						for (Vertical v: new Vertical[] {BOTTOM, TOP})
							if (occupied.get(CENTER, v))
								if (occupied.get(h, MIDDLE))
									if (occupied.get(h, v))
										tile.setQuarter(h, v, FivePositions.EMPTY.getTile());
									else
										tile.setQuarter(h, v, FivePositions.CORNERS.getTile());
								else
									tile.setQuarter(h, v, FivePositions.SIDES.getTile());
							else
								if (occupied.get(h, MIDDLE))
									tile.setQuarter(h, v, FivePositions.FACES.getTile());
								else
									tile.setQuarter(h, v, FivePositions.ALL.getTile());
					
					System.out.println("———");
				}
			}
		
        JOptionPane.showMessageDialog(null,
        	new JLabel(
        		new ImageIcon(wall.img),
        		JLabel.CENTER
        	), "Tiles!", -1);
	}
	
	private static enum FivePositions {
		  ALL(0,0),   SIDES(1,0), EMPTY(2, 0),
		FACES(0,1), CORNERS(1,1);
		
		
		private QuarterTiledImage src;

		private FivePositions(int x, int y) {
			try {
				this.src = new QuarterTiledImage(ImageIO.read(Tiling.class.getResource("5.png")), TILESIDE, TILESIDE);
			} catch (IOException io) {
				throw new RuntimeException(io.getMessage());
			}
			this.x = x;
			this.y = y;
		}
		
		private final int x;
		private final int y;
		
		public QuarteredTile getTile() {
			return src.new QuarteredTile(x, y);
		}
	}
}
