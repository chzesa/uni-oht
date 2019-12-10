package oht.chess.ui.cli;

import java.util.Scanner;

public class MainMenu extends Cli implements ICli {
	public MainMenu(Scanner scanner) {
		super(scanner);
	}

	void printHelp() {
		println("[1] Play Game");
		println("[2] Composition Editor");
		println("[3] Exit");
		print("> ");
	}

	@Override
	public ICli draw() {
		printHelp();
		String[] input = readSplit();
		int i = 0;

		for (String s : input) {
			try {
				i = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				continue;
			}

			switch (i) {
				case 1: return new PlayGameMenu(scan);
				case 2: return new CompositionMenu(scan);
				case 3: return null;
			}
		}

		return this;
	}
}