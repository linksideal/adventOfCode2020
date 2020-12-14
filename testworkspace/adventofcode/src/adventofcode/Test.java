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

	static List<Integer> EINGABE_AUFGABE1 = new ArrayList<>(List.of(1956, 1994, 457, 1654, 2003, 1902, 1741, 1494, 1597,
			1129, 1146, 1589, 1989, 1093, 1881, 1288, 1848, 1371, 1508, 1035, 1813, 1335, 1634, 1102, 1262, 1637, 1048,
			1807, 1270, 1528, 1670, 1803, 1202, 1294, 1570, 1640, 1484, 1872, 1140, 1207, 1485, 1781, 1778, 1772, 1334,
			1267, 1045, 1194, 1873, 1441, 1557, 1414, 1123, 1980, 1527, 1591, 1665, 1916, 1662, 1139, 1973, 1258, 1041,
			1134, 1609, 1554, 1455, 1124, 1478, 1938, 1759, 1281, 1410, 1511, 930, 1319, 1302, 1827, 1216, 1404, 1460,
			2002, 1590, 1817, 1341, 1631, 1608, 1382, 1158, 1594, 1049, 1804, 1555, 1753, 447, 1021, 1079, 609, 1766,
			1327, 1851, 1052, 1737, 1175, 1043, 1945, 1573, 1113, 1724, 1203, 1856, 1682, 1623, 1135, 1015, 1423, 1412,
			1315, 1375, 1895, 1351, 1530, 1758, 1445, 1518, 1819, 1567, 1305, 1919, 1952, 1432, 1099, 1476, 1883, 1871,
			1900, 1442, 1393, 1214, 1283, 1538, 1391, 1008, 1109, 1621, 1876, 1998, 1032, 1324, 1927, 481, 1732, 1370,
			1683, 1199, 1465, 1882, 1293, 1671, 1456, 1197, 1506, 1381, 1469, 1830, 1957, 1850, 1184, 1564, 1170, 1943,
			1131, 1867, 1208, 1788, 1337, 1722, 1760, 1651, 1069, 1574, 1959, 1770, 66, 1190, 1606, 1899, 1054, 980,
			1693, 1173, 1479, 1333, 1579, 1720, 1782, 1971, 1438, 1178, 1306));

	public static void main(String[] args) throws Exception {
//		System.out.println(aufgabe1a());
//		System.out.println(aufgabe1b());
//		System.out.println(aufgabe3a());
//		System.out.println(aufgabe3b());
		// System.out.println(aufgabe4a());
		// System.out.println(aufgabe4b());
		// testValideFields();
		// System.out.println(aufgabe5a());
		//System.out.println(aufgabe5b());
		System.out.println(new Aufgabe6(leseDatei("src/adventofcode/input6.txt")).a());
		System.out.println(new Aufgabe6(leseDatei("src/adventofcode/input6.txt")).b());
	}

	private static int aufgabe1a() {
		return ermittleProdukt(2020, EINGABE_AUFGABE1);
	}

	private static int ermittleProdukt(int zielwert, List<Integer> liste) {
		Collections.sort(liste);
		int untererIndex = 0;
		int obererIndex = liste.size() - 1;
		while (untererIndex < liste.size() && obererIndex > untererIndex) {
			while (obererIndex >= 0 && obererIndex > untererIndex) {
				int sum = liste.get(obererIndex) + liste.get(untererIndex);
				if (sum < zielwert) {
					untererIndex++;
				} else if (sum > zielwert) {
					obererIndex--;
				} else {
					return liste.get(obererIndex) * liste.get(untererIndex);
				}
			}
		}
		return -1;
	}

	private static int aufgabe1b() {
		Collections.sort(EINGABE_AUFGABE1);
		int untererIndex = 0;
		int obererIndex = EINGABE_AUFGABE1.size() - 1;
		int mittlererIndex = 1;
		while (obererIndex > 0) {
			int sum = EINGABE_AUFGABE1.get(untererIndex) + EINGABE_AUFGABE1.get(mittlererIndex)
					+ EINGABE_AUFGABE1.get(obererIndex);
			if (sum > 2020) {
				obererIndex--;
			} else if (sum == 2020) {
				return EINGABE_AUFGABE1.get(untererIndex) * EINGABE_AUFGABE1.get(mittlererIndex)
						* EINGABE_AUFGABE1.get(obererIndex);
			} else {
				int i = ermittleProdukt(2020 - EINGABE_AUFGABE1.get(obererIndex),
						EINGABE_AUFGABE1.subList(0, EINGABE_AUFGABE1.size() - 1));
				if (i > -1) {
					return i * EINGABE_AUFGABE1.get(obererIndex);
				} else {
					obererIndex--;
				}
			}
		}
		return 1;
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
