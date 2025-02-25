class Polynomial {
    private Node head;

    public Polynomial() {
        this.head = null;
    }

    public Polynomial(String poly) {
        this.head = null;
        parsePolynomial(poly);
    }

    public Polynomial(Polynomial otherPoly) {
        this.head = copyList(otherPoly.head);
    }

    private void parsePolynomial(String poly) {
        String[] terms = poly.split("\\+");
        for (String term : terms) {
            term = term.trim();
            int coefficient, exponent;
            if (term.contains("x^")) {
                String[] parts = term.split("x\\^");
                coefficient = Integer.parseInt(parts[0]);
                exponent = Integer.parseInt(parts[1]);
            } else if (term.contains("x")) {
                coefficient = Integer.parseInt(term.replace("x", ""));
                exponent = 1;
            } else {
                coefficient = Integer.parseInt(term);
                exponent = 0;
            }
            insertTerm(coefficient, exponent);
        }
    }

    private void insertTerm(int coefficient, int exponent) {
        Node newNode = new Node(coefficient, exponent);
        if (head == null || head.exponent < exponent) {
            newNode.nextNode = head;
            head = newNode;
        } else {
            Node current = head;
            while (current.nextNode != null && current.nextNode.exponent > exponent) {
                current = current.nextNode;
            }
            newNode.nextNode = current.nextNode;
            current.nextNode = newNode;
        }
    }

    private Node copyList(Node node) {
        if (node == null) return null;
        Node newNode = new Node(node.coefficient, node.exponent);
        newNode.nextNode = copyList(node.nextNode);
        return newNode;
    }

    public void print() {
        Node current = head;
        while (current != null) {
            System.out.print(current.coefficient + "x^" + current.exponent + " ");
            if (current.nextNode != null) System.out.print("+ ");
            current = current.nextNode;
        }
        System.out.println();
    }

    public static Polynomial add(Polynomial poly1, Polynomial poly2) {
        Polynomial result = new Polynomial();
        Node p1 = poly1.head, p2 = poly2.head;

        while (p1 != null || p2 != null) {
            if (p1 == null) {
                result.insertTerm(p2.coefficient, p2.exponent);
                p2 = p2.nextNode;
            } else if (p2 == null) {
                result.insertTerm(p1.coefficient, p1.exponent);
                p1 = p1.nextNode;
            } else if (p1.exponent > p2.exponent) {
                result.insertTerm(p1.coefficient, p1.exponent);
                p1 = p1.nextNode;
            } else if (p1.exponent < p2.exponent) {
                result.insertTerm(p2.coefficient, p2.exponent);
                p2 = p2.nextNode;
            } else {
                result.insertTerm(p1.coefficient + p2.coefficient, p1.exponent);
                p1 = p1.nextNode;
                p2 = p2.nextNode;
            }
        }
        return result;
    }
}
