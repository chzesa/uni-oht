package oht.chess.unit;

import oht.chess.Actor;
import oht.chess.Chesspiece;
import oht.chess.Faction;
import oht.chess.Role;
import oht.chess.Tcoord;

public class UnitFactory
{
	public static Actor make(Chesspiece base, Role role, Tcoord coord, Faction faction)
	{
		switch(base)
		{
			case Pawn: return new Pawn(role, coord, faction);
			case Rook: return new Rook(role, coord, faction);
			case Knight: return new Knight(role, coord, faction);
			case Bishop: return new Bishop(role, coord, faction);
			case King: return new King(role, coord, faction);
			case Queen: return new Queen(role, coord, faction);
		}
                throw new IllegalArgumentException();
	}
}