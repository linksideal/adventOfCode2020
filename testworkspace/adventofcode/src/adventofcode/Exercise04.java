package adventofcode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Exercise04 extends Exercise {

	public Exercise04() throws IOException {
		super("src/adventofcode/input4.txt", "src/adventofcode/testinput4.txt");

		exNumber = 4;

		expectationTestA = true;
		expectationA = 204;

		expectationB = 179;
	}

	@Override
	protected void solveA() throws Exception {
		solutionTestA = testValideFields();
		
		int anzahl = 0;
		for (Passport passport : ladePassports()) {
			if (passport.besitztAlleObligatorischenFields()) {
				anzahl++;
			}
		}
		solutionA = anzahl;
	}

	@Override
	protected void solveB() throws Exception {
		int anzahl = 0;
		for (Passport passport : ladePassports()) {
			if (passport.besitztAlleObligatorischenFieldsUndDieseSindValide()) {
				anzahl++;
			}
		}
		solutionB = anzahl;
	}

	private boolean testValideFields() throws IOException {
		List<Field> valideFields = testInput.stream().map(Field::new).collect(Collectors.toList());

		for (Field field : valideFields) {
			if( !field.istValide()) {
				return false;
			}
		}
		return true;
	}
	
	private List<Passport> ladePassports() {
		List<Passport> passports = new ArrayList<>();
		List<String> rohdaten = new ArrayList<>();
		for (String zeile : input) {
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

}
