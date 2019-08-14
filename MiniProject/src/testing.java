import java.util.Scanner;

public class testing {
	public static void main (String[] args) {
		Scanner input = new Scanner(System.in);
		WordGen.initialise(input);
		System.out.print("Enter the number of words you wish to generate initially: ");
		int n = input.nextInt();
		WordStore words = new WordStoreImp(n);
		words.add("test1");
		words.add("test1");
		words.add("test2");
		words.add("test1");
		words.add("test3");
		words.add("test2");
		System.out.println("Enter words to test, empty line to exit");
		String line = input.nextLine();
		line = input.nextLine();
		while (!line.equals("")) {
			String[] wordlist = line.split(" ");
			for (int i = 0; i < wordlist.length; i++) {
				int count = words.count(wordlist[i]);
				System.out.print("\"" + wordlist[i] + "\" ");
				if (count == 0)
					System.out.println("NOT generated");
				else if (count == 1)
					System.out.println("generated once");
				else
					System.out.println("generated " + count + " times ");
			}
			line = input.nextLine();
		}
	}
}
