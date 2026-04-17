package interfaces;

import java.util.HashMap;

public interface StatisticsInterface {
	int seqLength();

	HashMap<Character, Integer> seqContents();
}
