package adventofcode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Exercise05 extends Exercise {

	public Exercise05() throws IOException {
		super("src/adventofcode/input5.txt", "src/adventofcode/testinput5.txt");

		exNumber = 5;

		expectationTestA = 820;
		expectationA = 880;

		expectationB = 731;
	}

	@Override
	protected void solveA() throws Exception {
		solutionTestA = readSeats(testInput).stream().map(seat -> seat.id).max(Integer::compare).get();
		solutionA = readSeats(input).stream().map(seat -> seat.id).max(Integer::compare).get();
	}

	@Override
	protected void solveB() throws Exception {
		solutionB = seatIdOfFirstFreeSeatWhosNeighboorIsNotFree(readSeats(input));
	}

	private List<Seat> readSeats(List<String> input) throws Exception {
		List<Seat> seats = new ArrayList<>();
		for (String zeile : input) {
			seats.add(new Seat(zeile));
		}
		return seats;
	}
	
	private int seatIdOfFirstFreeSeatWhosNeighboorIsNotFree(List<Seat> seats) {
		List<Integer> freeSeatIds = freeSeatIds(seats);
		for (int seatId : freeSeatIds) {
			if (!neighboorIstAlsoFree(freeSeatIds, seatId))
				return seatId;
		}
		return -1;
	}
	
	private  List<Integer> freeSeatIds(List<Seat> seats) {
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
	
	private boolean neighboorIstAlsoFree(List<Integer> freeSeatIds, int seatId) {
		return freeSeatIds.contains(seatId - 1) || freeSeatIds.contains(seatId + 1);
	}

}
