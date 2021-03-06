package oht.chess.unit;

import oht.chess.shared.Faction;
import oht.chess.shared.Chesspiece;
import oht.chess.shared.IActor;
import org.junit.Test;
import static org.junit.Assert.*;

public class UnitFactoryTest {
	IActor _unit;

	@Test
	public void makePawn() {
		_unit = UnitFactory.make(Chesspiece.Pawn, Faction.White);
		assertEquals(Chesspiece.Pawn, _unit.base());
	}

	@Test
	public void makeRook() {
		_unit = UnitFactory.make(Chesspiece.Rook, Faction.White);
		assertEquals(Chesspiece.Rook, _unit.base());
	}

	@Test
	public void makeKnight() {
		_unit = UnitFactory.make(Chesspiece.Knight, Faction.White);
		assertEquals(Chesspiece.Knight, _unit.base());
	}

	@Test
	public void makeBishop() {
		_unit = UnitFactory.make(Chesspiece.Bishop, Faction.White);
		assertEquals(Chesspiece.Bishop, _unit.base());
	}

	@Test
	public void makeKing() {
		_unit = UnitFactory.make(Chesspiece.King, Faction.White);
		assertEquals(Chesspiece.King, _unit.base());
	}

	@Test
	public void makeQueen() {
		_unit = UnitFactory.make(Chesspiece.Queen, Faction.White);
		assertEquals(Chesspiece.Queen, _unit.base());
	}

	@Test
	public void correctFaction() {
		_unit = UnitFactory.make(Chesspiece.Pawn, Faction.White);
		assertEquals(Faction.White, _unit.faction());

		_unit = UnitFactory.make(Chesspiece.Pawn, Faction.Black);
		assertEquals(Faction.Black, _unit.faction());
	}

	@Test
	public void everyUnitCanBeMade() {
		for (Chesspiece value : Chesspiece.values()) {
			UnitFactory.make(value, Faction.White);
		}
	}
}
