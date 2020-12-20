package adventofcode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Exercise03 extends Exercise {

	public Exercise03() throws IOException {
		super("src/adventofcode/input3.txt");

		exNumber = 3;

		expectationA = 153;

		expectationB = 2421944712L;
	}

	@Override
	protected void solveA() throws Exception {
		int dx = 3;
		int dy = 1;
		solutionA = anzahlBaume(dy, dx);
	}

	private int anzahlBaume(int dy, int dx) {
		int hoehe = input.size();
		int breite = input.get(0).length();
		int[][] wald = wandleUmInArray(input);
		int x = 0;
		int y = 0;
		int count = 0;
		while (y < hoehe) {
			if (istBaum(wald, y, x)) {
				count++;
			}
			x = (x + dx) % breite;
			y += dy;
		}
		return count;
	}

	int BAUM = 1;
	int WIESE = 0;

	private boolean istBaum(int[][] wald, int y, int x) {
		return wald[y][x] == BAUM;
	}

	private int[][] wandleUmInArray(List<String> wald) {
		int hoehe = wald.size();
		int breite = wald.get(0).length();
		int[][] ergebnis = new int[hoehe][breite];
		for (String zeile : wald) {
			for (int i = 0; i < zeile.length(); i++) {
				ergebnis[wald.indexOf(zeile)][i] = wandleCharInBinaerUm(zeile.charAt(i));
			}
		}
		return ergebnis;
	}

	private int wandleCharInBinaerUm(Character c) {
		if (c.equals('.')) {
			return WIESE;
		} else {
			return BAUM;
		}
	}

	@Override
	protected void solveB() throws Exception {

		List<Integer[]> slopes = new ArrayList<>();
		slopes.add(new Integer[] { 1, 1 });
		slopes.add(new Integer[] { 1, 3 });
		slopes.add(new Integer[] { 1, 5 });
		slopes.add(new Integer[] { 1, 7 });
		slopes.add(new Integer[] { 2, 1 });

		long product = 1;

		for (Integer[] slope : slopes) {
			int dy = slope[0];
			int dx = slope[1];
			product *= anzahlBaume(dy, dx);
		}

		solutionB = product;
	}

}
