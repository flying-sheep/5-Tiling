package tiling;

public class Cardinal {
	public static enum Horizontal {
		LEFT(-1), CENTER(0), RIGHT(1);
		public final int i;
		
		Horizontal(int i) { this.i = i; }
		
		public static Horizontal valueOf(int i) throws ValueNotCoveredException {
			switch (i) {
				case -1: return LEFT;
				case  0: return CENTER;
				case  1: return RIGHT;
			}
			throw new ValueNotCoveredException(i);
		}
	}
	
	public static enum Vertical {
		TOP(-1), MIDDLE(0), BOTTOM(1);
		public final int i;
		
		Vertical(int i) { this.i = i; }
		
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
		
		public ValueNotCoveredException(int i) { this.i = i; }
		
		@Override
		public String getMessage() {
			return "Direction " +
				((this.i != 0) ? i : h.name().toLowerCase() + ", " + v.name().toLowerCase()) +
				" not covered";
		}
	}
}
