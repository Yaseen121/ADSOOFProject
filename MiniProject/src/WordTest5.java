import java.util.Scanner;

class WordTest5 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		WordGen.initialise(input);
		System.out.print("Enter the number of words you wish to generate initially: ");
		int n = input.nextInt();
		WordStore words = new WordStoreImp(n);
		for (int i = 0; i < n; i++) {
			words.add(WordGen.make());
		}
		String line = input.nextLine();
		System.out.println("Enter words to test, empty line to exit");
		line = input.nextLine();
		while (!line.equals("")) {
			String[] wordlist = line.split(" ");
			for (int i = 0; i < wordlist.length; i++) {
				int count2 = words.count(wordlist[i]);
				System.out.print("\"" + wordlist[i] + "\" ");
				if (count2 == 0) {
					System.out.println("NOT generated");
				} else if (count2 == 1) {
					System.out.println("generated once");
				} else {
					System.out.println("generated " + count2 + " times ");
				}
			}
			line = input.nextLine();
		}
		
		String toRemove;
		do {
			System.out.print("Enter a words you wish to remove: ");
			toRemove = input.nextLine();
			words.remove(toRemove);
		} while (!toRemove.equals(""));

		line = input.nextLine();
		System.out.println("Enter words to test, empty line to exit");
		line = input.nextLine();
		while (!line.equals("")) {
			String[] wordlist = line.split(" ");
			for (int i = 0; i < wordlist.length; i++) {
				int count1 = words.count(wordlist[i]);
				System.out.print("\"" + wordlist[i] + "\" ");
				if (count1 == 0) {
					System.out.println("NOT generated");
				} else if (count1 == 1) {
					System.out.println("generated once");
				} else {
					System.out.println("generated " + count1 + " times ");
				}
			}
			line = input.nextLine();
		}

		System.out.println("Enter words to test, empty line to exit");
		line = input.nextLine();
		while (!line.equals("")) {
			String[] wordlist = line.split(" ");
			for (int i = 0; i < wordlist.length; i++) {
				int count2 = words.count(wordlist[i]);
				System.out.print("\"" + wordlist[i] + "\" ");
				if (count2 == 0) {
					System.out.println("NOT generated");
				} else if (count2 == 1) {
					System.out.println("generated once");
				} else {
					System.out.println("generated " + count2 + " times ");
				}
			}
			line = input.nextLine();
		}
	}
}

