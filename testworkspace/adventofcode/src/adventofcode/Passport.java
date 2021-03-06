package adventofcode;

import java.util.ArrayList;
import java.util.List;

public class Passport {
	List<String> rohdaten;
	List<Field> fields;

	public Passport(List<String> rohdaten) {
		this.rohdaten = rohdaten;
		this.fields = new ArrayList<>();
		for (String zeile : rohdaten) {
			fields.addAll(ermittleFields(zeile));
		}
	}

	private List<Field> ermittleFields(String zeile) {
		List<Field> fields = new ArrayList<>();
		String zeileRest = zeile;
		while (zeileRest.indexOf(' ') > -1) {
			int i = zeileRest.indexOf(' ');
			Field field = new Field(zeileRest.substring(0, i));
			fields.add(field);
			zeileRest = zeileRest.substring(i + 1, zeileRest.length());
		}
		fields.add(new Field(zeileRest));
		return fields;
	}
	
	public boolean besitztAlleObligatorischenFields() {
		List<String> obligatorischeFields = new ArrayList<>();
		obligatorischeFields.add("byr");
		obligatorischeFields.add("iyr");
		obligatorischeFields.add("eyr");
		obligatorischeFields.add("hgt");
		obligatorischeFields.add("hcl");
		obligatorischeFields.add("ecl");
		obligatorischeFields.add("pid");
		
		for(String fieldname : obligatorischeFields) {
			if( !besitztField(fieldname)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean besitztAlleObligatorischenFieldsUndDieseSindValide() {
		return besitztAlleObligatorischenFields() && alleFelderValide();
	}
	
	private boolean alleFelderValide() {
		for(Field field : fields) {
			if(!field.istValide()) {
				return false;
			}
		}
		return true;
	}
	
	private boolean besitztField(String fieldname) {
		for(Field field : fields) {
			if( field.getName().equals(fieldname)) {
				return true;
			}
		}
		return false;
	}

}
