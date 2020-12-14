package adventofcode;

public class Seat {
	int row;
	int column;
	int id;

	public Seat(String seatCode) throws Exception {
		row = mapRow(seatCode);
		column = mapColumn(seatCode);
		id = row * 8 + column;
	}
	
	private int mapColumn(String seatCode) throws Exception {
		String columnCode = seatCode.substring(7);
		String columnBinary = "";
		for (char c : columnCode.toCharArray()) {
			columnBinary = columnBinary.concat(mapLeftRight(c));
		}

		return Integer.parseInt(columnBinary, 2);
	}

	private int mapRow(String seatCode) throws Exception {
		String rowCode = seatCode.substring(0, 7);
		String rowBinary = "";
		for (char c : rowCode.toCharArray()) {
			rowBinary = rowBinary.concat(mapFrontBack(c));
		}
		return Integer.parseInt(rowBinary, 2);
	}
	
	private String mapFrontBack(char c) throws Exception {
		switch (c) {
		case 'F':
			return "0";
		case 'B':
			return "1";
		default:
			throw new Exception();
		}
	}
	
	private String mapLeftRight(char c) throws Exception {
		switch (c) {
		case 'L':
			return "0";
		case 'R':
			return "1";
		default:
			throw new Exception();
		}
	}
}
