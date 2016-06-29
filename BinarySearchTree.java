/**
 * Created by rozoa on 6/24/2016.
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

    public BinarySearchTree(Object value){
        setBST(value,null,null,null,null);
    }

    public BinarySearchTree(Object value,BinarySearchTree p){
        setBST(value,null,null,null,p);
    }

    public BinarySearchTree(Object value,String k,BinarySearchTree p){setBST(value,k,null,null,p);};

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
        return ((!hasLeft())&&(!hasRight())&&isRoot());
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
            return false;
        }else {
            return true;
        }
    }

    public BinarySearchTree findNode(String wantedKey){
        BinarySearchTree currentNode=this;
        findKey(currentNode,wantedKey);
        return currentNode;
    }

    private void findKey(BinarySearchTree currentTree, String wantedKey) {
        if (currentTree!=null){
            if(Double.parseDouble(currentTree.key)>Double.parseDouble(wantedKey)){
                findKey(currentTree.left,wantedKey);
            }else if(Double.parseDouble(currentTree.key)<Double.parseDouble(wantedKey)) {
                findKey(currentTree.right,wantedKey);
            }else {
                return;
            }
        }
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
        size++;
        if(this.isEmpty()){
            addRoot(key, value);
        }else{
            BinarySearchTree bstRoot=this;
            addKey(bstRoot,key,value);
            balance(bstRoot);

        }


    }


    private void addKey(BinarySearchTree current, String wantedkey, Object value) {
        if(current!=null){
            if(Double.parseDouble(current.key)>Double.parseDouble(wantedkey)){
                if(current.left!=null) {
                    current=current.left;
                    addKey(current, wantedkey, value);
                }else{
                    current.left=new BinarySearchTree(value,wantedkey,current.left);
                    current=current.left;
                    return;
                }
            }else if(Double.parseDouble(current.key)<Double.parseDouble(wantedkey)){
                if(current.right!=null) {
                    current=current.right;
                    addKey(current, wantedkey, value);
                }else{
                    current.right=new BinarySearchTree(value,wantedkey,current.right);
                    current=current.right;
                    return;
                }
            }else{
                throw new IllegalArgumentException("The key has already existed.");
            }
        }
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
        size--;
        BinarySearchTree bst=findNode(key);
        if (bst.left==null && bst.right==null){
            doDelete(bst);
        }else if(bst.left==null && bst.right!=null){
            bst.right.parent=bst.parent;
            doDelete(bst);
        }else if (bst.left!=null && bst.right==null){
            bst.left.parent=bst.parent;
            doDelete(bst);
        }else{
            BinarySearchTree successorBst=bst.right.findMin();
            delete(successorBst.key);
            successorBst.right=bst.right;
            successorBst.left=bst.left;
            successorBst.parent=bst.parent;
            successorBst.right.parent=successorBst;
            successorBst.left.parent=successorBst;
            if(successorBst.parent!=null && successorBst.parent.left==bst){
                successorBst.parent.left=successorBst;
            }else if(successorBst.parent!=null && successorBst.parent.right==bst){
                successorBst.parent.right=successorBst;
            }

        }
        //balance();
    }

    /**
     * the private method find if the current node is the right or left child of the parent
     * and delete the link
     */
    private void doDelete(BinarySearchTree bst){
        if(bst.parent!=null) {
            if (bst.parent.left == bst) {
                bst.parent.left = null;
            } else {
                bst.parent.right = null;
            }
        }
    }

    public int size(){
        return size;
    }

    public int balanceFactor(){
        int l=doHeight(this.left);
        int r=doHeight(this.right);
        return (l-r);
    }

    public int height(){
        return doHeight(this);
    }

    private int doHeight(BinarySearchTree current) {
        if(current==null){
            return -1;
        }
        int l=doHeight(current.left);
        int r=doHeight(current.right);
        return ((l>=r) ? l:r)+1;
    }

    public int depth(){
        return doDepth(this);
    }

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
        while(bst !=null && (balanceFactor!=2 || balanceFactor!=-2)){
            balanceFactor=bst.balanceFactor();
            bst=bst.parent;
        }
    }

    private void rightRotation(BinarySearchTree bst){
        BinarySearchTree p=bst.parent;
        BinarySearchTree l=bst.left;
        BinarySearchTree r=l.right;
        l.right=bst;
        bst.parent=l;
        bst.left=r;
        r.parent=bst;
        if (p!=null){
            if(p.left==bst){
                p.left=l;
            }else{
                p.right=l;
            }
        }
        l.parent=p;

    }

    private void leftRotation(BinarySearchTree bst){
        BinarySearchTree p=bst.parent;
        BinarySearchTree l=bst.right;
        BinarySearchTree r=l.left;
        bst.right=r;
        r.parent=bst;
        l.left=bst;
        bst.parent=l;

        if (p!=null){
            if(p.right==bst){
                p.right=l;
            }else{
                p.left=l;
            }
        }
        l.parent=p;
    }




}
