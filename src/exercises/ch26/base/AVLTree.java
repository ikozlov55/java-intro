package exercises.ch26.base;

import exercises.ch25.base.BST;

import java.util.Comparator;
import java.util.List;

public class AVLTree<E> extends BST<E> {
    public AVLTree() {
    }

    public AVLTree(Comparator<E> c) {
        super(c);
    }

    public AVLTree(E[] objects) {
        super(objects);
    }

    @Override
    protected AVLTreeNode<E> createNewNode(E e) {
        return new AVLTreeNode<>(e);
    }

    @Override
    public boolean insert(E e) {
        boolean successful = super.insert(e);
        if (!successful)
            return false;
        else {
            balancePath(e);
        }
        return true;
    }

    private void updateHeight(AVLTreeNode<E> node) {
        if (node.getLeft() == null && node.getRight() == null) {
            node.height = 0;
        } else if (node.getLeft() == null) {
            node.height = 1 + node.getRight().height;
        } else if (node.getRight() == null) {
            node.height = 1 + node.getLeft().height;
        } else {
            node.height = 1 + Math.max(node.getRight().height, node.getLeft().height);
        }
    }

    private void balancePath(E e) {
        List<AVLTreeNode<E>> path = path(e).stream()
                .map(t -> (AVLTreeNode<E>) t)
                .toList();
        for (int i = path.size() - 1; i >= 0; i--) {
            AVLTreeNode<E> A = path.get(i);
            updateHeight(A);
            AVLTreeNode<E> parentOfA = (A == root) ? null : path.get(i - 1);

            switch (balanceFactor(A)) {
                case -2:
                    if (balanceFactor(A.getLeft()) <= 0) {
                        balanceLL(A, parentOfA); // Perform LL rotation
                    } else {
                        balanceLR(A, parentOfA); // Perform LR rotation
                    }
                    break;
                case +2:
                    if (balanceFactor(A.getRight()) >= 0) {
                        balanceRR(A, parentOfA); // Perform RR rotation
                    } else {
                        balanceRL(A, parentOfA); // Perform RL rotation
                    }
            }
        }
    }

    public int balanceFactor(AVLTreeNode<E> node) {
        if (node.getRight() == null) { // node has no right subtree
            return -node.height;
        } else if (node.getLeft() == null) { // node has no left subtree
            return +node.height;
        } else {
            return node.getRight().height - node.getLeft().height;
        }
    }


    private void balanceLL(AVLTreeNode<E> A, AVLTreeNode<E> parentOfA) {
        AVLTreeNode<E> B = A.getLeft(); // A is left-heavy and B is left-heavy
        if (A == root) {
            root = B;
        } else {
            if (parentOfA.getLeft() == A) {
                parentOfA.setLeft(B);
            } else {
                parentOfA.setRight(B);
            }
        }
        A.setLeft(B.getRight()); // Make T2 the left subtree of A
        B.setRight(A); // Make A the left child of B
        updateHeight(A);
        updateHeight(B);
    }

    private void balanceRR(AVLTreeNode<E> A, AVLTreeNode<E> parentOfA) {
        AVLTreeNode<E> B = A.getRight(); // A is right-heavy and B is right-heavy

        if (A == root) {
            root = B;
        } else {
            if (parentOfA.getLeft() == A) {
                parentOfA.setLeft(B);
            } else {
                parentOfA.setRight(B);
            }
        }

        A.setRight(B.getLeft()); // Make T2 the right subtree of A
        B.setLeft(A);
        updateHeight(A);
        updateHeight(B);

    }

    private void balanceLR(AVLTreeNode<E> A, AVLTreeNode<E> parentOfA) {
        AVLTreeNode<E> B = A.getLeft(); // A is left-heavy
        AVLTreeNode<E> C = B.getRight(); // B is right-heavy

        if (A == root) {
            root = C;
        } else {
            if (parentOfA.getLeft() == A) {
                parentOfA.setLeft(C);
            } else {
                parentOfA.setRight(C);
            }
        }

        A.setLeft(C.getRight()); // Make T3 the left subtree of A
        B.setRight(C.getLeft()); // Make T2 the right subtree of B
        C.setLeft(B);
        C.setRight(A);

        // Adjust heights
        updateHeight(A);
        updateHeight(B);
        updateHeight(C);
    }

    private void balanceRL(AVLTreeNode<E> A, AVLTreeNode<E> parentOfA) {
        AVLTreeNode<E> B = A.getRight(); // A is right-heavy
        AVLTreeNode<E> C = B.getLeft(); // B is left-heavy

        if (A == root) {
            root = C;
        } else {
            if (parentOfA.getLeft() == A) {
                parentOfA.setLeft(C);
            } else {
                parentOfA.setRight(C);
            }
        }

        A.setRight(C.getLeft()); // Make T2 the right subtree of A
        B.setLeft(C.getRight()); // Make T3 the left subtree of B
        C.setLeft(A);
        C.setRight(B);

        // Adjust heights
        updateHeight(A);
        updateHeight(B);
        updateHeight(C);
    }

    @Override
    public boolean delete(E element) {
        if (root == null) {
            return false; // Element is not in the tree

        }
        // Locate the node to be deleted and also locate its parent node
        AVLTreeNode<E> parent = null;
        AVLTreeNode<E> current = (AVLTreeNode<E>) root;
        while (current != null) {
            if (c.compare(element, current.getElement()) < 0) {
                parent = current;
                current = current.getLeft();
            } else if (c.compare(element, current.getElement()) > 0) {
                parent = current;
                current = current.getRight();
            } else {
                break; // Element is in the tree pointed by current
            }
        }

        if (current == null)
            return false; // Element is not in the tree

        // Case 1: current has no left children (See Figure 23.6)
        if (current.getLeft() == null) {
            // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.getRight();
            } else {
                if (c.compare(element, parent.getElement()) < 0) {
                    parent.setLeft(current.getRight());
                } else {
                    parent.setRight(current.getRight());
                }
                // Balance the tree if necessary
                balancePath(parent.getElement());
            }
        } else {
            // Case 2: The current node has a left child
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent
            AVLTreeNode<E> parentOfRightMost = current;
            AVLTreeNode<E> rightMost = current.getLeft();

            while (rightMost.getRight() != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.getRight(); // Keep going to the right
            }

            // Replace the element in current by the element in rightMost
            current.setElement(rightMost.getElement());
            // Eliminate rightmost node
            if (parentOfRightMost.getRight() == rightMost) {
                parentOfRightMost.setRight(rightMost.getLeft());
            } else {
                // Special case: parentOfRightMost is current
                parentOfRightMost.setLeft(rightMost.getLeft());
            }

            // Balance the tree if necessary
            balancePath(parentOfRightMost.getElement());
        }

        size--;
        return true; // Element inserted
    }
}


