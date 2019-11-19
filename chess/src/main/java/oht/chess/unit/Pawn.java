package oht.chess.unit;
import java.util.ArrayList;
import oht.chess.Actor;
import oht.chess.Chesspiece;
import oht.chess.Faction;
import oht.chess.Role;
import oht.chess.Tcoord;
import oht.chess.Vector;

class Pawn extends Actor
{
	Pawn(Role role, Tcoord coord, Faction faction)
	{
		super(Chesspiece.Pawn, role, coord, faction);
	}

	@Override
	public ArrayList<Vector> movementVectors()
	{
		ArrayList<Vector> ret = new ArrayList<>();
		ret.add(_fwd);
		return ret;
	}

	@Override
	public ArrayList<Vector> attackVectors()
	{
		ArrayList<Vector> ret = new ArrayList<>();
		Vector left = new Vector( Vector.add(_fwd, new Tcoord(-1, 0)), 1 );
		Vector right = new Vector( Vector.add(_fwd, new Tcoord(1, 0)), 1 );

		ret.add( left );
		ret.add( right );

		return ret;
	}

	@Override
	public char toChar() { return _faction == Faction.White ? '♙' : '♟'; }
}