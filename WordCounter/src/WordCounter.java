

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordCounter {
	public static List<Pair> findIndexes(String needle, char[][] array) {
		List<Pair> pairs = new ArrayList<Pair>();
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (needle.charAt(0) == array[i][j]) {
					pairs.add(new Pair(j, i));
				}
			}
		}
		return pairs;
	}

	public static int leftToRight(int row, int col, String needle,
			char[][] array) {
		int counter = 0;
		int a = 0;
		while (row < array[col].length && a != needle.length()) {
			if (array[col][row++] != needle.charAt(a++)) {
				break;
			}
			counter++;
		}
		return counter == needle.length() ? 1 : 0;
	}

	public static int rightToLeft(int row, int col, String needle,
			char[][] array) {
		int counter = 0;
		int a = 0;
		while (row > 0 && a != needle.length()) {
			if (array[col][row--] != needle.charAt(a++)) {
				break;
			}
			counter++;
		}
		return counter == needle.length() ? 1 : 0;
	}

	public static int topToBottom(int row, int col, String needle,
			char[][] array) {
		int counter = 0;
		int a = 0;
		while (col < array.length && a != needle.length()) {
			if (array[col++][row] != needle.charAt(a++)) {
				break;
			}
			counter++;
		}
		return counter == needle.length() ? 1 : 0;
	}

	public static int bottomToTop(int row, int col, String needle,
			char[][] array) {
		int counter = 0;
		int a = 0;
		while (col >= 0 && a != needle.length()) {
			if (array[col--][row] != needle.charAt(a++)) {
				break;
			}
			counter++;
		}
		return counter == needle.length() ? 1 : 0;
	}

	public static int topLeftToBottomRight(int row, int col, String needle,
			char[][] array) {
		int a = 0;
		int counter = 0;
		while (col < array.length && row < array[col].length
				&& a != needle.length()) {
			if (array[col++][row++] != needle.charAt(a++)) {
				break;
			}
			counter++;
		}
		return counter == needle.length() ? 1 : 0;
	}

	public static int topRightToBottomLeft(int row, int col, String needle,
			char[][] array) {
		int a = 0;
		int counter = 0;
		while (col < array.length && row > 0 && a != needle.length()) {
			if (array[col++][row--] != needle.charAt(a++)) {
				break;
			}
			counter++;
		}
		return counter == needle.length() ? 1 : 0;
	}

	public static int bottomRightToTopLeft(int row, int col, String needle,
			char[][] array) {
		int counter = 0;
		int a = 0;
		while (col > 0 && row > 0 && a != needle.length()) {
			if (array[col--][row--] != needle.charAt(a++)) {
				break;
			}
			counter++;
		}
		return counter == needle.length() ? 1 : 0;
	}

	public static int bottomLeftToTopRight(int row, int col, String needle,
			char[][] array) {
		int counter = 0;
		int a = 0;
		while (col > 0 && row < array[col].length && a != needle.length()) {
			if (array[col--][row++] != needle.charAt(a++)) {
				break;
			}
			counter++;
		}
		return counter == needle.length() ? 1 : 0;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String searched = scanner.next();
		char[][] map;
		int n, m;
		n = scanner.nextInt();
		m = scanner.nextInt();
		map = new char[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = scanner.next().charAt(0);
			}
		}
		int counter = 0;
		for (Pair pair : findIndexes(searched, map)) {
			if (isPalindrome(searched)) {
				counter += leftToRight(pair.row, pair.col, searched, map);
				counter += topToBottom(pair.row, pair.col, searched, map);
				counter += topLeftToBottomRight(pair.row, pair.col, searched,
						map);
				counter += topRightToBottomLeft(pair.row, pair.col, searched,
						map);
			} else {
				counter += leftToRight(pair.row, pair.col, searched, map);
				counter += rightToLeft(pair.row, pair.col, searched, map);
				counter += topToBottom(pair.row, pair.col, searched, map);
				counter += bottomToTop(pair.row, pair.col, searched, map);
				counter += topLeftToBottomRight(pair.row, pair.col, searched,
						map);
				counter += topRightToBottomLeft(pair.row, pair.col, searched,
						map);
				counter += bottomRightToTopLeft(pair.row, pair.col, searched,
						map);
				counter += bottomLeftToTopRight(pair.row, pair.col, searched,
						map);
			}
		}
		System.out.println(counter);
		scanner.close();
	}

	public static boolean isPalindrome(String word) {
		for (int i = 0, a = word.length() - 1; i < word.length() - 1; i++, a--) {
			if (word.charAt(i) != word.charAt(a)) {
				return false;
			}
		}
		return true;
	}
}