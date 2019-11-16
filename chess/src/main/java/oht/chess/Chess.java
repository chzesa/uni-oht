package oht.chess;

import oht.chess.GameState;
import oht.chess.ConsoleUi;
import oht.chess.Faction;
import oht.chess.Tcoord;
import oht.chess.Chesspiece;
import oht.chess.Role;
import oht.chess.ConsolePlayer;
import java.util.ArrayList;
import oht.chess.TargetSet;
import java.util.Random;

public class Chess
{
	public static void main(String[] args)
	{
		GameState state = new GameState(8, 8);
		Board b = state.board();
		ConsoleUi ui = new ConsoleUi();
		ConsolePlayer p = new ConsolePlayer();
		Random rand = new Random();
		Role roles[] = Role.values();

		for (int i = 0; i < 8; i++)
		{
			Tcoord pos = new Tcoord(i, 6);
			b.emplace(new Pawn(roles[rand.nextInt(roles.length)], pos, Faction.Black), pos);
			pos = new Tcoord(i, 1);
			b.emplace(new Pawn(roles[rand.nextInt(roles.length)], pos, Faction.White), pos);
		}

		Tcoord pos = new Tcoord(0, 0);
		b.emplace(new Rook(roles[rand.nextInt(roles.length)], pos, Faction.White), pos);
		pos = new Tcoord(1, 0);
		b.emplace(new Knight(roles[rand.nextInt(roles.length)], pos, Faction.White), pos);
		pos = new Tcoord(2, 0);
		b.emplace(new Bishop(roles[rand.nextInt(roles.length)], pos, Faction.White), pos);
		pos = new Tcoord(3, 0);
		b.emplace(new Queen(roles[rand.nextInt(roles.length)], pos, Faction.White), pos);
		pos = new Tcoord(4, 0);
		b.emplace(new King(roles[rand.nextInt(roles.length)], pos, Faction.White), pos);
		pos = new Tcoord(5, 0);
		b.emplace(new Bishop(roles[rand.nextInt(roles.length)], pos, Faction.White), pos);
		pos = new Tcoord(6, 0);
		b.emplace(new Knight(roles[rand.nextInt(roles.length)], pos, Faction.White), pos);
		pos = new Tcoord(7, 0);
		b.emplace(new Rook(roles[rand.nextInt(roles.length)], pos, Faction.White), pos);

		pos = new Tcoord(0, 7);
		b.emplace(new Rook(roles[rand.nextInt(roles.length)], pos, Faction.Black), pos);
		pos = new Tcoord(1, 7);
		b.emplace(new Knight(roles[rand.nextInt(roles.length)], pos, Faction.Black), pos);
		pos = new Tcoord(2, 7);
		b.emplace(new Bishop(roles[rand.nextInt(roles.length)], pos, Faction.Black), pos);
		pos = new Tcoord(3, 7);
		b.emplace(new Queen(roles[rand.nextInt(roles.length)], pos, Faction.Black), pos);
		pos = new Tcoord(4, 7);
		b.emplace(new King(roles[rand.nextInt(roles.length)], pos, Faction.Black), pos);
		pos = new Tcoord(5, 7);
		b.emplace(new Bishop(roles[rand.nextInt(roles.length)], pos, Faction.Black), pos);
		pos = new Tcoord(6, 7);
		b.emplace(new Knight(roles[rand.nextInt(roles.length)], pos, Faction.Black), pos);
		pos = new Tcoord(7, 7);
		b.emplace(new Rook(roles[rand.nextInt(roles.length)], pos, Faction.Black), pos);

		turn: while(true)
		{
			ui.setSelected(new ArrayList<>());
			ui.setSelectables(new ArrayList<>());
			ui.draw(state);
			MoveDescriptor desc = p.selectAbility(state);
			if (desc == null) { break; }
			System.out.println("User picked: \n\t"
				+ b.get(desc.origin()).toString()
				+ "\n\t" + b.get(desc.origin()).ability(desc.key()));

			Actor tar = b.get(desc.origin());
			if (tar == null) { continue; }
			IAbility a = tar.ability(desc.key());
			if (a == null) { continue; }

			AbilityTargeter t = a.beginUse(state);
			while (!t.isComplete())
			{
				TargetSet ws = t.current();

				ArrayList<Tcoord> tars = new ArrayList<>();
				for (Tcoord c : ws.targetables()) { tars.add(c); }
				ui.setSelectables(tars);

				ArrayList<Tcoord> sels = new ArrayList<>();
				for (Tcoord c : ws.targets()) { sels.add(c); }
				ui.setSelected(sels);

				ui.draw(state);

				t = p.targetAbility(state, a, t);
				if (t == null) { continue turn; }
			}

			System.out.println("Using " + a.toString());
			a.endUse(t);
			state.nextTurn();
		}
		System.out.println("Exiting...");
	}
}