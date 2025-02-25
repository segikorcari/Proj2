package proj2;

import java.util.*;
import java.io.*;

class Node {
    int coefficient;
    int exponent;
    Node nextNode;

    public Node(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
        this.nextNode = null;
    }
}


