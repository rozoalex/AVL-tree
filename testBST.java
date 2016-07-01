
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

        System.out.println("delete 65");
        bst.delete("65");
        bst.printInorder();
        bst.printPreorder();
        System.out.println("xxxxxxxxxx");
        System.out.println();

        System.out.println("delete 30");
        bst.delete("30");
        bst.printInorder();
        bst.printPreorder();
        System.out.println("xxxxxxxxxx");
        System.out.println();

        System.out.println("delete 75");
        bst.delete("75");
        bst.printInorder();
        bst.printPreorder();
        System.out.println("xxxxxxxxxx");
        System.out.println();

        System.out.println("delete 150");
        bst.delete("150");
        bst.printInorder();
        bst.printPreorder();
        System.out.println("xxxxxxxxxx");
        System.out.println();

        System.out.println("delete 45");
        bst.delete("45");
        bst.printInorder();
        bst.printPreorder();
        System.out.println("xxxxxxxxxx");
        System.out.println();

        bst.printInorder();
        System.out.print("Do mirror image: ");
        bst.mirrorTree().printPreorder();
        System.out.print("Normal: ");
        bst.printPreorder();


    }
}
