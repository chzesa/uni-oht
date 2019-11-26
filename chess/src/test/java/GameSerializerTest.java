import java.util.Random;
import oht.chess.ability.Role;
import oht.chess.game.Entity;
import oht.chess.game.Game;
import oht.chess.game.GameSerializer;
import oht.chess.unit.Faction;
import oht.chess.unit.IActor;
import oht.chess.unit.Chesspiece;
import oht.chess.util.Tcoord;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class GameSerializerTest {
	Game g;
	Random rand;

	@Before
	public void setUp() {
		g = new Game(8, 8);
		rand = new Random(21589);
	}

	void spawnRandom(int w, int h) {
		Faction f = Faction.values()[rand.nextInt(Faction.values().length)];
		Chesspiece b = Chesspiece.values()[rand.nextInt(Chesspiece.values().length)];
		Role r = Role.values()[rand.nextInt(Role.values().length)];
		int x = rand.nextInt(w);
		int y = rand.nextInt(h);
		g.spawn(b, r, f, new Tcoord(x, y));
		Entity e = g.get(x, y);
		e.setHp(e.hp() + 1);
	}

	@Test
	public void testSerialize() {
		for (int i = 0; i < 10000; i++) {
			g = new Game(8, 8);
			int num = rand.nextInt(64);
			for (int n = 0; n < num; n++) {
				spawnRandom(8, 8);
			}

			String str = GameSerializer.serialize(g);
			Game result = GameSerializer.deserialize(str);
			assertNotNull(result);
			assertEquals(g, result);
		}
	}

	@Test
	public void testSerializeRandomCorrupt() {
		for (int i = 0; i < 10000; i++) {
			g = new Game(4, 4);
			int num = rand.nextInt(16);
			for (int n = 0; n < num; n++) {
				spawnRandom(4, 4);
			}

			String str = GameSerializer.serialize(g);
			StringBuilder sb = new StringBuilder(str);
			int position = rand.nextInt(str.length());
			sb.replace(position, position + 1, "&");
			Game result = GameSerializer.deserialize(sb.toString());
			if (result != null) {
				assertNotEquals(g, result);
			}
		}
	}
}