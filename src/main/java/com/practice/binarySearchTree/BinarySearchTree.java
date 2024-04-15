package main.java.com.practice.binarySearchTree;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree {
    Node root;

    public BinarySearchTree(){
        this.root = null;
    }

    class Node {
        private int value;
        private Node left;
        private Node right;

        Node(int value){
            this.value = value;
        }
    }

    public boolean insert(int value){
        Node newNode  = new Node(value);
        if(root == null){
            root = newNode;
            return true;
        }

        Node tmp = root;
        while(true){
            if(tmp.value == newNode.value){
                return false;
            }
            if (newNode.value < tmp.value) {
                if(tmp.left == null){
                    tmp.left = newNode;
                    return true;
                }else{
                    tmp = tmp.left;
                }
            }else {
                if(tmp.right == null){
                    tmp.right = newNode;
                    return true;
                }else{
                    tmp = tmp.right;
                }
            }
        }
    }

    public boolean contains(int value){
        if(root == null){
            return false;
        }

        Node tmp = root;
        while(tmp!=null){
            if(value < tmp.value){
                tmp = tmp.left;
            } else if (value > tmp.value) {
                tmp = tmp.right;
            } else {
                return true;
            }
        }
        return false;
    }

    private boolean rContains(Node currentNode, int value){
        if(currentNode == null){
            return false;
        }
        if(currentNode.value == value){
            return true;
        }

        if(value < currentNode.value){
            return rContains(currentNode.left, value);
        }else{
            return rContains(currentNode.right, value);
        }
    }

    public boolean rContains(int value){
        return rContains(root, value);
    }

    private Node cInsert(Node currentNode, int value){
        if(currentNode == null){
            return new Node(value);
        }
        if(value < currentNode.value){
            currentNode.left = cInsert(currentNode.left, value);
        }else {
            currentNode.right = cInsert(currentNode.right, value);
        }
        return currentNode;
    }

    public void cInsert(int value){
        if(root == null){
            root = new Node(value);
        }
        cInsert(root, value);
    }

    private Node cDelete(Node currentNode, int value){
        //value is not in the tree
        if(currentNode == null){
            return null;
        }
        //tree traversing
        if(value < currentNode.value){
            currentNode.left = cDelete(currentNode.left, value);
        }else if(value > currentNode.value){
            currentNode.right = cDelete(currentNode.right, value);
        }else{
            // we found that Node that we wnt to delete!!!
            if(currentNode.left == null && currentNode.right == null){
                //leaf
                return null;
            }else if(currentNode.left == null){
                //open on a left
                currentNode = currentNode.right;
            }else if(currentNode.right == null){
                //open on a right
                currentNode = currentNode.left;
            }else{
                // not open (has left and right children)
                int subTreeMin = findMinimum(currentNode.right);
                currentNode.value = subTreeMin;
                currentNode.right = cDelete(currentNode.right, subTreeMin);


            }
        }
        return currentNode;
    }

    private int findMinimum(Node currentNode){
        while(currentNode.left!=null){
            currentNode = currentNode.left;
        }
        return currentNode.value;
    }

    public void cDelete(int value){
        cDelete(root, value);
    }

    public void invert(){
        cInvert(root);
    }

    private void cInvert(Node currentNode){
        if(currentNode == null){
            return;
        }

        Node tmp = currentNode.left;
        currentNode.left = currentNode.right;
        currentNode.right = tmp;

        cInvert(currentNode.left);
        cInvert(currentNode.right);
    }

    //1 2 3 4 5
    public Node createBSTfromSortedArray(int nums[], int s, int e){
        if (s > e){
            return null;
        }

        int mid = (s + e)/2;
        Node node = new Node(nums[mid]);
        root.left = createBSTfromSortedArray(nums, s, mid-1);
        root.right = createBSTfromSortedArray(nums, mid+1, e);;

        return node;
    }

    ////////////////////////////////////////////////////////////////

    public ArrayList<Integer> BFS(){
        Node currentNode = root;
        Queue<Node> queue = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();
        queue.add(currentNode);

        while(queue.size()>0){
             currentNode = queue.remove();
            result.add(currentNode.value);

            if(currentNode.left!=null){
                queue.add(currentNode.left);
            }
            if(currentNode.right!=null){
                queue.add(currentNode.right);
            }
        }
        return result;
    }

    ////////////////////////////////////////////////////////////////


    public ArrayList<Integer> DFSPreOrder(){
        ArrayList<Integer> result = new ArrayList<>();

        class Traverse{
            Traverse(Node currentNode){
                result.add(currentNode.value);

                if(currentNode.left!=null){
                    new Traverse(currentNode.left);
                }

                if(currentNode.right!=null){
                    new Traverse(currentNode.right);
                }
            }
        }

        new Traverse(root);
        return result;
    }
    ////////////////////////////////////////////////////////////////


    public ArrayList<Integer> DFSInOrder(){
        ArrayList<Integer> result = new ArrayList<>();

        class Traverse{
            Traverse(Node currentNode){
                if(currentNode.left!=null){
                    new Traverse(currentNode.left);
                }
                result.add(currentNode.value);

                if(currentNode.right!=null){
                    new Traverse(currentNode.right);
                }
            }
        }

        new Traverse(root);
        return result;
    }

    public boolean isValidBST(){
        ArrayList<Integer> nodeValues = DFSInOrder();
        for (int i = 1; i < nodeValues.size(); i++) {
            if(nodeValues.get(i) <= nodeValues.get(i-1)){
                return false;
            }
        }
        return true;
    }

    public Integer kthSmallest(int k){
        Stack<Node> stack = new Stack<>();
        Node node = this.root;

        while(!stack.isEmpty() || node!=null){
            while(node != null){
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            k-=1;
            if(k == 0){
                return node.value;
            }
            node = node.right;
        }
        return null;
    }

    ////////////////////////////////////////////////////////////////


    public ArrayList<Integer> DFSPostOrder(){
        ArrayList<Integer> result = new ArrayList<>();

        class Traverse{
            Traverse(Node currentNode){
                if(currentNode.left!=null){
                    new Traverse(currentNode.left);
                }

                if(currentNode.right!=null){
                    new Traverse(currentNode.right);
                }
                result.add(currentNode.value);
            }
        }

        new Traverse(root);
        return result;
    }

    ////////////////////////////////////////////////////////////////
}
