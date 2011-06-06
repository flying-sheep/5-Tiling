package tiling;

import java.awt.image.BufferedImage;

import tiling.Cardinal.Horizontal;
import static tiling.Cardinal.Horizontal.*;
import tiling.Cardinal.Vertical;
import static tiling.Cardinal.Vertical.*;

/**
 * Wrapper around Bufferedimage to get and set (quarters of) tiles painlessly
 */
public class QuarterTiledImage {
	private final int tileWidth;
	private final int tileHeight;
	public final BufferedImage img;
	
	public QuarterTiledImage(BufferedImage img, int tileWidth, int tileHeight) {
		this.tileWidth  = tileWidth;
		this.tileHeight = tileHeight;
		this.img = img;
	}
	
	public class QuarteredTile {
		private final int x;
		private final int y;
		private BufferedImage img;
		
		public QuarteredTile(int x, int y) {
			this.x = x;
			this.y = y;
			this.img = QuarterTiledImage.this.img;
		}
		
		public void setQuarter(Horizontal h, Vertical v, QuarteredTile source) {
			int leftOff = (h == LEFT) ? 0 : (tileWidth / 2);
			int topOff  = (v == TOP ) ? 0 : (tileHeight / 2);
			
			for (int relX = leftOff; relX < leftOff + (tileWidth / 2); relX++)
				for (int relY = topOff; relY < topOff + (tileHeight / 2); relY++) {
					int sourceX = source.x * tileWidth + relX;
					int sourceY = source.y * tileHeight + relY;
					int sourceRGB = source.img.getRGB(sourceX, sourceY);
					//System.out.print(sourceX + "," + sourceY + " → ");
					
					int targetX = x * tileWidth + relX;
					int targetY = y * tileHeight + relY;
					img.setRGB(targetX, targetY, sourceRGB);
					//System.out.println(targetX + "," + targetY);
				}
			
			//System.out.println();
		}
	}
}
