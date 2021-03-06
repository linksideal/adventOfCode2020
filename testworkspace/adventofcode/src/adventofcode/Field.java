package adventofcode;

public class Field {
	String rohdaten;
	String name;
	String value;

	public Field(String rohdaten) {
		this.name = rohdaten.substring(0, 3);
		this.value = rohdaten.substring(4, rohdaten.length());
		this.rohdaten = rohdaten;
	}

	public String getName() {
		return name;
	}

	public boolean istValide() {
		Integer valueInt = 0;
		switch (name) {
		case "byr":
			valueInt = Integer.parseInt(value);
			return value.length() == 4 && valueInt >= 1920 && valueInt <= 2002;
		case "iyr":
			valueInt = Integer.parseInt(value);
			return value.length() == 4 && valueInt >= 2010 && valueInt <= 2020;
		case "eyr":
			valueInt = Integer.parseInt(value);
			return value.length() == 4 && valueInt >= 2020 && valueInt <= 2030;
		case "hgt":
			valueInt = Integer.parseInt(value.substring(0, value.length() - 2));
			if (value.endsWith("cm")) {
				return valueInt >= 150 && valueInt <= 193;
			} else if (value.endsWith("in")) {
				return valueInt >= 59 && valueInt <= 76;
			} else {
				return false;
			}
		case "hcl":
			return value.matches("#[0-9,a-f]{6}");
		case "ecl":
			return value.equals("amb") || value.equals("blu") || value.equals("brn") || value.equals("gry")
					|| value.equals("grn") || value.equals("hzl") || value.equals("oth");
		case "pid":
			return value.length() == 9 && value.matches("[0-9]{9}");
		default:
			break;
		}
		return true;
	}
}
