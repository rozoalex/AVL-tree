import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Yuanze Hu on 6/24/2016.
 */
public class BinarySearchTree {
    Object data;
    String key;
    BinarySearchTree left;
    BinarySearchTree right;
    BinarySearchTree parent;
    private int size=0;

    public BinarySearchTree(){
        setBST(null,null,null,null,null);
    }

    public BinarySearchTree(Object value,String k,BinarySearchTree p){setBST(value,k,null,null,p);};

    public BinarySearchTree(Object data, String key,BinarySearchTree left,BinarySearchTree right,BinarySearchTree parent){
        setBST(data,key,left,right,parent);
    }

    private void setBST(Object data, String key,BinarySearchTree left,BinarySearchTree right,BinarySearchTree parent){
        this.data=data;
        this.key=key;
        this.left=left;
        this.right=right;
        this.parent=parent;
    }

    public boolean hasLeft(){
        return isNull(left);
    }

    public boolean hasRight(){
        return isNull(right);
    }

    public boolean isLeaf(){
        if (hasLeft() && hasRight() && data!=null){
            return true;
        }else{
            return false;
        }
    }

    public boolean isEmpty(){
        if(isNull(this)){
            return false;
        }
        return ((!isNull(this)) && size==0);
    }

    public boolean isRoot(){
        return isNull(parent);
    }

    public boolean isLeftChild(){
        return((parent.left).equals(this));
    }

    public boolean isRightChild(){
        return((parent.right).equals(this));
    }

    public boolean hasParent(){return (!isNull(parent));}

    private boolean isNull(BinarySearchTree bst){
        if (bst==null){
            return true;
        }else {
            return false;
        }
    }

    public BinarySearchTree findNode(String wantedKey){
        BinarySearchTree current=this;
        current=findKey(current,wantedKey);
        return current;
    }

    private BinarySearchTree findKey(BinarySearchTree currentTree, String wantedKey) {
        if (currentTree!=null){
            int temp=isFirstGreator(currentTree.key,wantedKey);
            if(temp>0){
                return findKey(currentTree.left,wantedKey);
            }else if(temp<0) {
                return findKey(currentTree.right,wantedKey);
            }else {
                return currentTree;
            }
        }return null;
    }

    private int isFirstGreator(String first,String second){
        if(isNumber(first)&&isNumber(second)){//handle the case we have all numbers
            //if both of the strings can be numbers, then use the default java double.compare
            Double f=Double.parseDouble(first);
            Double s=Double.parseDouble(second);
            return Double.compare(f,s);
        }else{//if one of the string cannot be a number, then use the default java string compareTo
            return first.compareTo(second);
        }
    }

    //determine if a string can be a double
    private boolean isNumber(String s) {
        try {
            Double.parseDouble(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }

    public BinarySearchTree findMin(){
        BinarySearchTree current=this;
        while(current.left!=null){
            current=current.left;
        }
        return current;
    }

    public BinarySearchTree findSuccessor(String key){
        BinarySearchTree current=findNode(key);
        if (current==null){
            return null;
        }
        if (current.right!=null){
            return current.right.findMin();
        }else{
            BinarySearchTree currentParent=current.parent;
            while ((currentParent!=null) && (current==currentParent.right)){
                current=currentParent;
                currentParent=currentParent.parent;
            }
            return currentParent;
        }

    }

    public void addRoot(String key, Object value){
        if (!this.isEmpty()){
            throw new IllegalArgumentException("The tree is not empty.");
        }
        setBST(value,key,null,null,null);
    }

    public void insert(String key, Object value){

        if(isNull(this)){
            addRoot(key, value);
            System.out.println("addRoot done  "+key);//test
        }else if(isEmpty()){
            setBST(value,key,null,null,null);
            System.out.println("setBST done  "+key);//test
        }else
        {
            BinarySearchTree bstRoot=this;
            bstRoot=addKey(bstRoot,key,value);
            System.out.println("addKey done  "+key+" "+bstRoot.parent.key);//test
            System.out.println("current bst at "+bstRoot.key);//test
            printInorder();//test
            printPreorder();//test
            balance(bstRoot);
            printInorder();//test
            printPreorder();//test
            advancedPrint(this);
            System.out.println("kkkkkkkkkk");
        }

        size++;
    }

    private BinarySearchTree addKey(BinarySearchTree current, String wantedkey, Object value) {
        if(current!=null &&current.key!=null){
            int temp=isFirstGreator(current.key,wantedkey);
            if(temp>0){
                if(current.left!=null) {
                    current=current.left;
                    current=addKey(current, wantedkey, value);
                }else{
                    current.left=new BinarySearchTree(value,wantedkey,current);
                    return current.left;

                }
            }else if(temp<0){
                if(current.right!=null) {
                    current=current.right;
                    current=addKey(current, wantedkey, value);
                }else{
                    current.right=new BinarySearchTree(value,wantedkey,current);
                    return current.right;
                }
            }else{
                throw new IllegalArgumentException("The key has already existed.");
            }
        }
        System.out.println("inside addKey "+current.key);
        return current;
    }

    public Object search(String key){
        BinarySearchTree bst=findNode(key);
        if(bst==null){
            return null;
        }else {
            return bst.data;
        }
    }


    /**
     * the method deletes a node from the tree
     */
    public void delete(String key){
        if(size ==0){
            throw new IllegalArgumentException("the tree is empty");
        }
        size--;
        BinarySearchTree bst=findNode(key);
        System.out.println(key+"find key "+bst.key);
        BinarySearchTree current;
        if (bst.left==null && bst.right==null){
            current=bst.parent;
            if(bst.parent!=null) {
                if (bst.parent.left == bst) {
                    bst.parent.left = null;
                } else {
                    bst.parent.right = null;
                }
            }
        }else if(bst.left==null && bst.right!=null){
            current=bst.right;
            bst.right.parent=bst.parent;
            if(bst.parent!=null) {
                if (bst.parent.left == bst) {
                    bst.parent.left = bst.right;
                } else {
                    bst.parent.right = bst.right;
                }
            }
        }else if (bst.left!=null && bst.right==null){
            current=bst.parent;
            bst.left.parent=bst.parent;
            if(bst.parent!=null) {
                if (bst.parent.left == bst) {
                    bst.parent.left = bst.left;
                } else {
                    bst.parent.right = bst.left;
                }
            }

        }else{
            BinarySearchTree successorBst=bst.right.findMin();
            System.out.println("replace "+bst.key+" with "+successorBst.key);
            current=successorBst.parent;
            doDelete(successorBst);//delete the successor

            successorBst.right=bst.right;
            successorBst.left=bst.left;
            successorBst.parent=bst.parent;
            //let the successor have the same connection as the deleted one

            if(successorBst.right!=null) {
                successorBst.right.parent = successorBst;
            }
            if(successorBst.left!=null) {
                successorBst.left.parent = successorBst;
            }
            if(successorBst.parent!=null && successorBst.parent.left==bst){
                successorBst.parent.left=successorBst;
            }else if(successorBst.parent!=null && successorBst.parent.right==bst){
                successorBst.parent.right=successorBst;
            }else if(successorBst.parent==null ){
                bst.key=successorBst.key;
                bst.data=successorBst.data;
                //swap the key and data, make a fake switch
                bst.right.parent=bst;
                bst.left.parent=bst;
                //make the kids back to bst
            }
        }
        balance(current);
    }

    public void doDelete(BinarySearchTree bst){
        if (bst.left==null && bst.right==null){
            if(bst.parent!=null) {
                if (bst.parent.left == bst) {
                    bst.parent.left = null;
                } else {
                    bst.parent.right = null;
                }
            }
        }else if(bst.left==null && bst.right!=null){
            bst.right.parent=bst.parent;
            if(bst.parent!=null) {
                if (bst.parent.left == bst) {
                    bst.parent.left = bst.right;
                } else {
                    bst.parent.right = bst.right;
                }
            }
        }else if (bst.left!=null && bst.right==null){
            bst.left.parent=bst.parent;
            if(bst.parent!=null) {
                if (bst.parent.left == bst) {
                    bst.parent.left = bst.left;
                } else {
                    bst.parent.right = bst.left;
                }
            }
        }
    }


    /**
     * return the size
     * @return
     */
    public int size(){
        return size;
    }


    /**
     * find out the balance factor of a bst
     */
    public int balanceFactor(){
        int l=doHeight(this.left);
        int r=doHeight(this.right);
        return (l-r);
    }

    /**
     * find the height of the give bst
     */
    public int height(){
        return doHeight(this);
    }
    //the one does the work
    //recursive
    private int doHeight(BinarySearchTree current) {
        if(current==null){
            return -1;
        }
        int l=doHeight(current.left);
        int r=doHeight(current.right);
        return ((l>=r) ? l:r)+1;
    }

    /**
     * find the depth of the give bst
     */
    public int depth(){
        return doDepth(this);
    }
    //the one does the work
    //recursive
    private int doDepth(BinarySearchTree current) {
        if(current==null){
            return -1;
        }
        return doDepth(current.parent)+1;
    }


    /**
     * the method balance the tree according to the algorithm of AVL tree
     * it takes one that was just added
     * and goes to top
     * stop until have done balance once
     */
    private void balance(BinarySearchTree bst){
        int balanceFactor =0;
        boolean isOut = isOutside(bst);
        while(bst !=null && (balanceFactor!=2 || balanceFactor!=-2)){
            balanceFactor=bst.balanceFactor();
            System.out.println("bst is "+bst.data+"  balanced");
            doRotation(isOut,balanceFactor,bst);
            bst=bst.parent;
        }
    }

    /**
     * this private method determines what to do for a bst
     * if the balace factor is 2 or -2, it will do sth
     * if the adding is outside, do single rotation
     * if the adding is inside, do double rotation
     * be called in only in the while loop of balance method
     */
    private void doRotation(boolean isOut, int balanceFactor, BinarySearchTree bst) {
        if (balanceFactor==2 && isOut){
            rightRotation(bst);
        }else if(balanceFactor==-2 && isOut){
            leftRotation(bst);
        }else if(balanceFactor==2 && (!isOut)){
            leftRotation(bst.left);
            rightRotation(bst);
        }else if(balanceFactor==-2 && (!isOut)){
            rightRotation(bst.right);
            leftRotation(bst);
        }
    }


    /**
     * this private method determine if the node just added is outside or inside
     */
    private boolean isOutside(BinarySearchTree bst) {
        if (bst.parent==null){
            return true;
        }
        if(bst.parent.left==bst && (bst.parent.parent==null||bst.parent.parent.left==bst.parent)){
            return true;
        }
        if(bst.parent.right==bst && (bst.parent.parent==null||bst.parent.parent.right==bst.parent)){
            return true;
        }
        return false;
    }

    /**
     * private
     * perform a right rotation to the given bst
     */
    private void rightRotation(BinarySearchTree bst){
        BinarySearchTree p = bst.parent;
        BinarySearchTree l = bst.left;
        BinarySearchTree r = l.right;
        if(bst.parent!=null) {

            bst.left = r;
            bst.parent = l;
            if (p.right == bst) {
                p.right = l;
            } else {
                p.left = l;
            }
            l.parent = p;
            l.right = bst;
            if (r != null) {
                r.parent = bst;
            }
        }else{
            bst.left=l.left;
            if(bst.left!=null) {
                bst.left.parent = bst;
            }
            l.left = l.right;
            l.right=bst.right;
            if(l.right!=null) {
                l.right.parent = l;
            }
            l.parent=bst;
            bst.right=l;
            String tempKey=bst.key;
            Object tempData=bst.data;
            bst.key=l.key;
            bst.data=l.data;
            l.key=tempKey;
            l.data=tempData;
            //to keep the root
            //instead of swapping the tree, swap the key and data
            //do a fake swapping
        }
        System.out.println("Right Rotation Done");//test


    }

    /**
     * private
     * perform a left rotation to the given bst
     */
    private void leftRotation(BinarySearchTree bst){
        BinarySearchTree p = bst.parent;
        BinarySearchTree l = bst.right;
        BinarySearchTree r = l.left;
        if(bst.parent!=null) {

            bst.right = r;
            bst.parent = l;
            if (p.right == bst) {
                p.right = l;
            } else {
                p.left = l;
            }
            l.parent = p;
            l.left = bst;
            if (r != null) {
                r.parent = bst;
            }
        }else{
            bst.right=l.right;
            if(bst.right!=null) {
                bst.right.parent = bst;
            }
            l.right = l.left;
            l.left=bst.left;
            if(l.left!=null){
                l.left.parent=l;
            }
            l.parent=bst;
            bst.left=l;
            String tempKey=bst.key;
            Object tempData=bst.data;
            bst.key=l.key;
            bst.data=l.data;
            l.key=tempKey;
            l.data=tempData;
            //to keep the root
            //instead of swapping the tree, swap the key and data
            //do a fake swapping
        }

        System.out.println("Left Rotation Done");//test



    }

    /**
     * Make the mirror image of the binary search tree
     * Obviously, the binary search property will not be retained
     * just for fun and homework purpose
     */
    public BinarySearchTree mirrorTree(){
        BinarySearchTree bst=this;
        BinarySearchTree mirrorTree=new BinarySearchTree();
        doMirrorTree(this,mirrorTree);
        return mirrorTree;
    }

    private void doMirrorTree(BinarySearchTree bst,BinarySearchTree mirrorTree){
        if(bst!=null) {
            if(bst.parent!=null){
                if(bst.parent.right==bst){
                    mirrorTree.left=new BinarySearchTree(bst.data,bst.key,mirrorTree);
                    doMirrorTree(bst.right,mirrorTree.left);
                    doMirrorTree(bst.left,mirrorTree.left);
                }else if(bst.parent.left==bst){
                    mirrorTree.right=new BinarySearchTree(bst.data,bst.key,mirrorTree);
                    doMirrorTree(bst.right,mirrorTree.right);
                    doMirrorTree(bst.left,mirrorTree.right);
                }
            }else{
                mirrorTree.addRoot(bst.key,bst.data);
                doMirrorTree(bst.right,mirrorTree);
                doMirrorTree(bst.left,mirrorTree);
            }
        }
    }

    public void printInorder(){
        System.out.print("Print inorder:");
        doInorder(this);
        System.out.println();
    }

    private void doInorder(BinarySearchTree binarySearchTree) {
        if(binarySearchTree!=null) {
            doInorder(binarySearchTree.left);
            System.out.print(binarySearchTree.data.toString()+" ");
            doInorder(binarySearchTree.right);
        }
    }

    public void printPreorder(){
        System.out.print("Print preorder:");
        doPreorder(this);
        System.out.println();
    }

    private void doPreorder(BinarySearchTree binarySearchTree) {
        if(binarySearchTree!=null) {
            System.out.print(binarySearchTree.data.toString()+" ");
            doPreorder(binarySearchTree.left);
            doPreorder(binarySearchTree.right);
        }
    }

    /**
     * level order print
     * using queue
     * that's why the class has imported the java.util package
     */
    public void advancedPrint(BinarySearchTree bst) {
        int h=bst.height();
        Queue<BinarySearchTree> queue=new LinkedList<BinarySearchTree>();
        queue.add(bst);
        while(!queue.isEmpty())
        {
            BinarySearchTree tempNode=queue.poll();
            System.out.print(tempNode.data+" ");
            if(tempNode.left!=null)
                queue.add(tempNode.left);
            if(tempNode.right!=null)
                queue.add(tempNode.right);
        }
    }



}
