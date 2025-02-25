public class PolynomialCalculator {
    public static void main(String[] args) {
        List<Polynomial> polynomials = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File("polynomials.txt"))) {
            while (fileScanner.hasNextLine()) {
                polynomials.add(new Polynomial(fileScanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return;
        }

        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            System.out.println("List of Polynomials:");
            for (int i = 0; i < polynomials.size(); i++) {
                System.out.print(i + ": ");
                polynomials.get(i).print();
            }
            System.out.println("Which do you wish to add? Press -1 to Exit.");
            int choice1 = inputScanner.nextInt();
            if (choice1 == -1) break;
            int choice2 = inputScanner.nextInt();
            if (choice1 < 0 || choice2 < 0 || choice1 >= polynomials.size() || choice2 >= polynomials.size()) {
                System.out.println("Invalid input. Try again.");
                continue;
            }
            Polynomial sum = Polynomial.add(polynomials.get(choice1), polynomials.get(choice2));
            polynomials.add(sum);
        }
        inputScanner.close();
    }
}
