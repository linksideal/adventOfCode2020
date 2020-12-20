package adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

	public static void main(String[] args) throws Exception {
//		System.out.println(new Aufgabe1(leseDatei("src/adventofcode/input1.txt")).ermittle());
//		System.out.println(aufgabe1b());
//		System.out.println(aufgabe3a());
//		System.out.println(aufgabe3b());
		// System.out.println(aufgabe4a());
		// System.out.println(aufgabe4b());
		// testValideFields();
		// System.out.println(aufgabe5a());
		//System.out.println(aufgabe5b());
		//System.out.println(new Aufgabe6(leseDatei("src/adventofcode/input6.txt")).a());
		//System.out.println(new Aufgabe6(leseDatei("src/adventofcode/input6.txt")).b());
//		System.out.println(new Aufgabe7(leseDatei("src/adventofcode/input7.txt")).ermittle());
//		System.out.println(new Aufgabe8(leseDatei("src/adventofcode/input8.txt")).ermittle());
//		System.out.println(new Aufgabe9(leseDatei("src/adventofcode/input9.txt"), leseDatei("src/adventofcode/testinput9.txt")).ermittle());
		
		new Exercise10().printResults();
		new Exercise09().printResults();
		new Exercise08().printResults();
		new Exercise07().printResults();
		new Exercise06().printResults();
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

	private static List<Passport> ladePassports() throws IOException {
		String pfad = "src/adventofcode/input4.txt";
		List<String> zeilen = leseDatei(pfad);
		List<Passport> passports = ermittlePassports(zeilen);
		return passports;
	}

	private static int aufgabe4a() throws IOException {
		int anzahl = 0;
		for (Passport passport : ladePassports()) {
			if (passport.besitztAlleObligatorischenFields()) {
				anzahl++;
			}
		}
		return anzahl;

	}

	private static int aufgabe4b() throws IOException {
		int anzahl = 0;
		for (Passport passport : ladePassports()) {
			if (passport.besitztAlleObligatorischenFieldsUndDieseSindValide()) {
				anzahl++;
			}
		}
		return anzahl;
	}

	private static List<Passport> ermittlePassports(List<String> zeilen) {
		List<Passport> passports = new ArrayList<>();
		List<String> rohdaten = new ArrayList<>();
		for (String zeile : zeilen) {
			if (zeile.isBlank()) {
				passports.add(new Passport(rohdaten));
				rohdaten.clear();
			} else {
				rohdaten.add(zeile);
			}
		}
		passports.add(new Passport(rohdaten));
		return passports;
	}

	private static void testValideFields() {
		List<Field> valideFields = new ArrayList<>();
		valideFields.add(new Field("byr:2002"));
		valideFields.add(new Field("hgt:60in"));
		valideFields.add(new Field("hgt:190cm"));
		valideFields.add(new Field("hcl:#123abc"));
		valideFields.add(new Field("ecl:brn"));
		valideFields.add(new Field("pid:000000001"));

		for (Field field : valideFields) {
			System.out.println(field.istValide());
		}
	}

	private static void testSeatIds() throws Exception {
		List<Seat> seats = new ArrayList<>();
		seats.add(new Seat("FBFBBFFRLR"));
		seats.add(new Seat("BFFFBBFRRR"));
		seats.add(new Seat("FFFBBBFRRR"));
		seats.add(new Seat("BBFFBBFRLL"));
		List<Integer> erwartungen = new ArrayList<>();
		erwartungen.add(357);
		erwartungen.add(567);
		erwartungen.add(119);
		erwartungen.add(820);
		for (Seat seat : seats) {
			System.out.println(seat.id == erwartungen.get(seats.indexOf(seat)));
		}
	}

	private static int aufgabe5a() throws Exception {
		testSeatIds();

		List<Integer> seatIds = new ArrayList<>();
		for (Seat seat : ladeSeats()) {
			seatIds.add(seat.id);
		}
		return Collections.max(seatIds);
	}

	private static int aufgabe5b() throws Exception {
		List<Integer> freeSeatIds = freeSeatIds(ladeSeats());
		for (int seatId : freeSeatIds) {
			if (!nachbarAuchFrei(freeSeatIds, seatId))
				return seatId;
		}
		return -1;
	}

	private static boolean nachbarAuchFrei(List<Integer> freeSeatIds, int seatId) {
		return freeSeatIds.contains(seatId - 1) || freeSeatIds.contains(seatId + 1);
	}

	private static List<Integer> freeSeatIds(List<Seat> seats) {
		List<Integer> seatIds = seats.stream().map(x -> x.id).collect(Collectors.toList());
		Collections.sort(seatIds);
		int i = 0;
		List<Integer> freeSeatIds = new ArrayList<>();
		for (int seatId : seatIds) {
			while (i < seatId) {
				freeSeatIds.add(i);
				i++;
			}
			i++;
		}
		return freeSeatIds;
	}

	private static List<Seat> ladeSeats() throws Exception {
		String pfad = "src/adventofcode/input5.txt";
		List<String> zeilen = leseDatei(pfad);
		List<Seat> seats = new ArrayList<>();
		for (String zeile : zeilen) {
			seats.add(new Seat(zeile));
		}
		return seats;
	}

}
