
/**
 * Created by rozoa on 6/29/2016.
 */
public class testBST {
    public static void main(String [ ] args){
        BinarySearchTree bst = new BinarySearchTree();

        bst.insert("30","30");
        bst.printInorder();
        System.out.println("xxxxxxxxxx");
        System.out.println();
        bst.insert("15","15");
        bst.printInorder();
        System.out.println("xxxxxxxxxx");
        System.out.println();
        bst.insert("35","35");
        bst.printInorder();
        System.out.println("xxxxxxxxxx");
        System.out.println();
        bst.insert("45","45");
        bst.printInorder();
        System.out.println("xxxxxxxxxx");
        System.out.println();
        bst.insert("65","65");
        bst.printInorder();
        System.out.println("xxxxxxxxxx");
        System.out.println();
        bst.insert("75","75");
        bst.printInorder();
        System.out.println("xxxxxxxxxx");
        System.out.println();
        bst.insert("95","95");
        bst.printInorder();
        System.out.println("xxxxxxxxxx");
        System.out.println();
        bst.insert("105","105");
        bst.printInorder();
        System.out.println("xxxxxxxxxx");
        System.out.println();
        bst.insert("150","150");
        bst.printInorder();
        System.out.println("xxxxxxxxxx");
        System.out.println();
        bst.delete("75");
        bst.printInorder();
        bst.printPreorder();
        System.out.println("xxxxxxxxxx");
        System.out.println();
        bst.delete("150");
        bst.printInorder();
        bst.printPreorder();
        System.out.println("xxxxxxxxxx");
        System.out.println();
        bst.delete("45");
        bst.printInorder();
        bst.printPreorder();
        System.out.println("xxxxxxxxxx");
        System.out.println();
        bst.printInorder();



    }
}
