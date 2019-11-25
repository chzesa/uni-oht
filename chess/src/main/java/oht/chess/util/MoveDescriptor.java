package oht.chess.util;

public class MoveDescriptor {
	Tcoord origin;
	int key;

	public MoveDescriptor(Tcoord origin, int key) {
		this.origin = origin;
		this.key = key;
	}

	public Tcoord origin() {
		return this.origin;
	}
	
	public int key() {
		return this.key;
	}
}