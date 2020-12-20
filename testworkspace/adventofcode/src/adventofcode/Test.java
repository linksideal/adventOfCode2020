package adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) throws Exception {
//		System.out.println(new Aufgabe1(leseDatei("src/adventofcode/input1.txt")).ermittle());
//		System.out.println(aufgabe1b());
//		System.out.println(aufgabe3a());
//		System.out.println(aufgabe3b());
		new Exercise10().printResults();
		new Exercise09().printResults();
		new Exercise08().printResults();
		new Exercise07().printResults();
		new Exercise06().printResults();
		new Exercise05().printResults();
		new Exercise04().printResults();
	}

	static int BAUM = 1;
	static int WIESE = 0;

	private static int aufgabe3a() throws IOException {
		int dx = 3;
		int dy = 1;
		return anzahlBaume(dy, dx);
	}

	private static long aufgabe3b() throws IOException {
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

		return product;
	}

	private static int anzahlBaume(int dy, int dx) throws IOException {
		List<String> waldText = leseDatei("src/adventofcode/input3.txt");
		int hoehe = waldText.size();
		int breite = waldText.get(0).length();
		int[][] wald = wandleUmInArray(waldText);
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

	private static boolean istBaum(int[][] wald, int y, int x) {
		return wald[y][x] == BAUM;
	}

	private static List<String> leseDatei(String pfad) throws IOException {
		Path path = Paths.get(pfad);
		return Files.readAllLines(path);
	}

	private static int[][] wandleUmInArray(List<String> wald) {
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

	private static int wandleCharInBinaerUm(Character c) {
		if (c.equals('.')) {
			return WIESE;
		} else {
			return BAUM;
		}
	}

}
