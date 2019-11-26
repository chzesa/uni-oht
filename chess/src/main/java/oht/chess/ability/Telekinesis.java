package oht.chess.ability;
import java.util.Set;
import oht.chess.unit.IActor;
import oht.chess.game.IBoard;
import oht.chess.util.Tcoord;

class Telekinesis implements IAbility {
	@Override
	public AbilityTargeter beginUse(IActor user, IBoard board) {
		Set<Tcoord> targets =
						AbilityUtil.filterNonempty(user.attackVectors(), user.pos(), board);
	
		return new AbilityTargeter(new TargetSet(targets, 1));
	}

	@Override
	public boolean endUse(AbilityTargeter t, IActor user, IBoard board) {
		// if (!isValid(t)) { return false; }
		Tcoord tar = t.get(0, 0);
		Tcoord to = t.get(1, 0);
		Effect.move(board.get(tar), to, board);
		return true;
	}

	@Override
	public TargeterState isComplete(AbilityTargeter t, IActor user, IBoard board) {
		if (t.size(0) == 1) {
			if (t.size() == 1) {
				Set<Tcoord> targets =
								AbilityUtil.filterEmpty(user.attackVectors(), user.pos(), board);

				TargetSet n = new TargetSet(targets, 1);
				t.append(n);
			} else if (t.size(1) == 1) {
				return TargeterState.Complete;
			}
		}

		return TargeterState.Incomplete;
	}

	@Override
	public boolean isValid(AbilityTargeter t, IActor user, IBoard board) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public String name() {
		return "Telekinesis";
	}

	@Override
	public String description() {
		return "Move target unit to target square.";
	}

	@Override public String toString() {
		return name() + ": " + description();
	}
}