package oht.chess.unit;
import java.util.ArrayList;
import oht.chess.Actor;
import oht.chess.Chesspiece;
import oht.chess.Faction;
import oht.chess.Role;
import oht.chess.Tcoord;
import oht.chess.Vector;

class Knight extends Actor
{
	Knight(Role role, Tcoord coord, Faction faction)
	{
		super(Chesspiece.Knight, role, coord, faction);
	}

	@Override
	public ArrayList<Vector> movementVectors()
	{
		ArrayList<Vector> ret = new ArrayList<>();
		ret.add(new Vector(-1, 2, 1)); // up,left
		ret.add(new Vector(1, 2, 1)); // up,right

		ret.add(new Vector(2, 1, 1)); // right, up
		ret.add(new Vector(2, -1, 1)); // right, down

		ret.add(new Vector(1, -2, 1)); // down, right
		ret.add(new Vector(-1, -2, 1)); // down, left

		ret.add(new Vector(-2, -1, 1)); // left, down
		ret.add(new Vector(-2, 1, 1)); // left, up

		return ret;
	}

	@Override
	public ArrayList<Vector> attackVectors() { return movementVectors(); }

	@Override
	public char toChar() { return _faction == Faction.White ? '♘' : '♞'; }
}