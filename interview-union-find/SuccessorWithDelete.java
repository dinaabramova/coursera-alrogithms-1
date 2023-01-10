/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.HashMap;
import java.util.Map;

public class SuccessorWithDelete {
    private Map<Integer, Integer> numbers = new HashMap<Integer, Integer>();

    public SuccessorWithDelete(int N) {
        for (int i = 0; i < N; i++) {
            numbers.put(i, i == N - 1 ? i : i + 1);
        }
    }

    public void delete(int number) {
        int link = numbers.get(number);
        numbers.remove(number);
        for (Map.Entry<Integer, Integer> entry : numbers.entrySet()) {
            if (entry.getValue() == number) {
                entry.setValue(link);
            }
        }
    }

    public int successor(int number) {
        return numbers.get(number);
    }
}
