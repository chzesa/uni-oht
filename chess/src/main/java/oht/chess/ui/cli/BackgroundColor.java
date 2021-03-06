package oht.chess.ui.cli;

enum BackgroundColor {
	Default("\u001B[0m"),
	Black("\u001B[40m"),
	Red("\u001B[41m"),
	Green("\u001B[42m"),
	Yellow("\u001B[44m"),
	Blue("\u001B[44m"),
	Purple("\u001B[45m"),
	Cyan("\u001B[46m"),
	White("\u001B[47m");

	private final String color;
	BackgroundColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return this.color;
	}
}