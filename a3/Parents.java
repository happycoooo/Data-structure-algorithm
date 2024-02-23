package assignment3;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Parents {
    public static class TreeNode {
        public int data;
        public TreeNode parent;
        public ArrayList<TreeNode> child = new ArrayList<>();
        public TreeNode(int key) {
            data = key;
        }
    }

    public static TreeNode contains(ArrayList<TreeNode> tree,int data){
        for (TreeNode treeNode : tree){
            if (treeNode.data == data) {
                return treeNode;
            }
        }
        return null;
    }

    public static void addEdge(ArrayList<TreeNode> tree, int v, int w) {
        if (contains(tree, v) != null ) { //树中已包含第一个结点，则v为父节点
            TreeNode curChild = new TreeNode(w);
            TreeNode curParent = contains(tree, v);
            curChild.parent = curParent;
            assert curParent != null;
            curParent.child.add(curChild);
            tree.add(curChild);
        }else if(contains(tree, w) != null){
            TreeNode curChild = new TreeNode(v);
            TreeNode curParent = contains(tree, w);
            curChild.parent = curParent;
            assert curParent != null;
            curParent.child.add(curChild);
            tree.add(curChild);
        }else{
            TreeNode curParent = new TreeNode(v);
            TreeNode curChild = new TreeNode(w);
            curChild.parent = curParent;
            curParent.child.add(curChild);
            tree.add(curChild);
            tree.add(curParent);
        }
    }

    public static int[] findParents ( int n, ArrayList<TreeNode> tree){
        int[] parents = new int[n];
        for(int i = 1 ; i <= n ; i ++) {
            TreeNode node = contains(tree, i);
            assert node != null;
            if (node.parent == null) {
                parents[i-1] = -1;
            } else {
                parents[i-1] = node.parent.data;
            }
        }
        return parents;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // 文件中第一个数字是数组长度，接下来N个数字才是数组元素。
        // 请根据实际情况更改文件路径
        File input = new File("src/test_data/Q1/A2.in");
        if (!input.exists()) {
            System.out.println("File isn't exist");
            System.exit(0);
        }
        Scanner in = new Scanner(input);
        int n = in.nextInt();  //the number of tree nodes
        ArrayList<TreeNode> tree = new ArrayList<>();//记录所有结点
        for (int i = 0; i < n-1 ; i++) {
            int v = in.nextInt();
            int w = in.nextInt();
            addEdge(tree, v, w);
        }

        int[] parents = findParents(n,tree);

        File output = new File("src/test_data/Q1/A2.out");
        in = new Scanner(output);
        boolean flag = true;

        for (int i = 0; i < n; i++) {
            if (in.nextInt() != parents[i]) {
                flag = false;
                break;
            }
        }
       System.out.println(flag);
    }
}