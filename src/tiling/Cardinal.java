package tiling;

public enum Cardinal {
	   TOPLEFT(Horizontal.LEFT, Vertical.TOP),       TOP(Horizontal.CENTER, Vertical.TOP),       TOPRIGHT(Horizontal.RIGHT, Vertical.TOP),
	      LEFT(Horizontal.LEFT, Vertical.MIDDLE), CENTER(Horizontal.CENTER, Vertical.MIDDLE),       RIGHT(Horizontal.RIGHT, Vertical.MIDDLE),
	BOTTOMLEFT(Horizontal.LEFT, Vertical.BOTTOM), BOTTOM(Horizontal.CENTER, Vertical.BOTTOM), BOTTOMRIGHT(Horizontal.RIGHT, Vertical.BOTTOM);
	
	public final Horizontal h;
	public final Vertical   v;
	
	Cardinal(Horizontal h, Vertical v) {
		this.h = h;
		this.v = v;
	}
	
	public static Cardinal valueOf(Horizontal h, Vertical v) throws ValueNotCoveredException {
		switch (h) {
		case LEFT:
			switch (v) {
			case TOP:
				return TOPLEFT;
			case MIDDLE:
				return LEFT;
			case BOTTOM:
				return BOTTOMLEFT;
			}
		case CENTER:
			switch (v) {
			case TOP:
				return TOP;
			case MIDDLE:
				return CENTER;
			case BOTTOM:
				return BOTTOM;
			}
		case RIGHT:
			switch (v) {
			case TOP:
				return TOPRIGHT;
			case MIDDLE:
				return RIGHT;
			case BOTTOM:
				return BOTTOMRIGHT;
			}
		}
		throw new ValueNotCoveredException(h, v);
	}

	public enum Horizontal {
		LEFT(-1), CENTER(0), RIGHT(1);
		public final int i;
		Horizontal(int i) {this.i = i;}
		public static Horizontal valueOf(int i) throws ValueNotCoveredException {
			switch (i) {
			case -1: return LEFT;
			case  0: return CENTER;
			case  1: return RIGHT;
			}
			throw new ValueNotCoveredException(i);
		}
	}

	public enum Vertical {
		TOP(-1), MIDDLE(0), BOTTOM(1);
		public final int i;
		Vertical(int i) {this.i = i;}
		public static Vertical valueOf(int i) throws ValueNotCoveredException {
			switch (i) {
			case -1: return TOP;
			case  0: return MIDDLE;
			case  1: return BOTTOM;
			}
			throw new ValueNotCoveredException(i);
		}
	}
	
	@SuppressWarnings("serial")
	public static class ValueNotCoveredException extends Exception {
		private Horizontal h;
		private Vertical v;
		private int i;
		
		public ValueNotCoveredException(Horizontal h, Vertical v) {
			this.h = h;
			this.v = v;
		}
		
		public ValueNotCoveredException(int i) {
			this.i = i;
		}
		
		@Override
		public String getMessage() {
			if (this.i != 0)
				return "Direction " + i + " not covered";
			else
				return "Direction " + h.name().toLowerCase() + ", " + v.name().toLowerCase() + "not covered";
		}
	}
}
