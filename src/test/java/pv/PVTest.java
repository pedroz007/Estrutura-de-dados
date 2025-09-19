package pv;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PVTest {

    @Test
    void testEmptyTree() {
        PV tree = new PV();
        assertTrue(tree.isEmpty());
    }

    @Test
    void testCase1RootBecomesBlack() {
        PV tree = new PV();
        PV.Node root = tree.new Node(10);
        tree.checkCase(root);
        assertTrue(root.isBlack, "A raiz deve ser preta (caso 1).");
    }

    @Test
    void testCase3Recoloring() {
        PV tree = new PV();

        PV.Node avo = tree.new Node(20);
        avo.isBlack = true;

        PV.Node pai = tree.new Node(10);
        pai.parent = avo;
        avo.left = pai;
        pai.isBlack = false;

        PV.Node tio = tree.new Node(30);
        tio.parent = avo;
        avo.right = tio;
        tio.isBlack = false;

        PV.Node node = tree.new Node(5);
        node.parent = pai;

        // Aplica caso 3
        tree.checkCase(node);

        assertTrue(pai.isBlack);
        assertTrue(tio.isBlack);
        assertFalse(avo.isBlack);
    }

    @Test
    void testCase4RotationToCase5() {
        PV tree = new PV();

        PV.Node avo = tree.new Node(20);
        avo.isBlack = true;

        PV.Node pai = tree.new Node(10);
        pai.parent = avo;
        avo.left = pai;
        pai.isBlack = false;

        PV.Node node = tree.new Node(15);
        node.parent = pai;
        pai.right = node;

        // Caso zig-zag → rotação no pai
        tree.checkCase(node);

        assertEquals(pai, node.left);
        assertEquals(avo, node.right);
    }

    @Test
    void testCase5RotationAndRecoloring() {
        PV tree = new PV();

        PV.Node avo = tree.new Node(20);
        avo.isBlack = true;

        PV.Node pai = tree.new Node(10);
        pai.parent = avo;
        avo.left = pai;
        pai.isBlack = false;

        PV.Node node = tree.new Node(5);
        node.parent = pai;
        pai.left = node;

        // Caso zig-zig → rotação no avô
        tree.checkCase(node);

        assertTrue(pai.isBlack);
        assertFalse(avo.isBlack);
    }

    @Test
    void testBlackHeight() {
        PV tree = new PV();

        PV.Node root = tree.new Node(10);
        root.isBlack = true;

        PV.Node left = tree.new Node(5);
        left.parent = root;
        root.left = left;

        PV.Node right = tree.new Node(15);
        right.parent = root;
        root.right = right;

        left.isBlack = true;
        right.isBlack = true;

        int leftHeight = blackHeight(left);
        int rightHeight = blackHeight(right);

        assertEquals(leftHeight, rightHeight, "Altura preta deve ser igual em todos os caminhos.");
    }

    @Test
    void testIsValidPV() {
        PV tree = new PV();

        PV.Node root = tree.new Node(10);
        root.isBlack = true;

        PV.Node left = tree.new Node(5);
        left.parent = root;
        root.left = left;
        left.isBlack = false;

        PV.Node right = tree.new Node(15);
        right.parent = root;
        root.right = right;
        right.isBlack = false;

        assertTrue(isValidPV(root), "Árvore deve respeitar as propriedades de árvore PV.");
    }

    // ---- Helpers ----

    private int blackHeight(PV.Node node) {
        if (node == null) return 1;
        int left = blackHeight(node.left);
        int right = blackHeight(node.right);
        if (left != right) throw new IllegalStateException("Altura preta inconsistente!");
        return left + (node.isBlack ? 1 : 0);
    }

    private boolean isValidPV(PV.Node node) {
        if (node == null) return true;

        // Raiz preta
        if (node.parent == null && !node.isBlack) return false;

        // Nó vermelho não pode ter filho vermelho
        if (!node.isBlack) {
            if ((node.left != null && !node.left.isBlack) ||
                (node.right != null && !node.right.isBlack)) {
                return false;
            }
        }

        // Recursivo nos filhos
        return isValidPV(node.left) && isValidPV(node.right);
    }
}

