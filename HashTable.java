import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by YUANZE HU
 * @
 */
public class HashTable {
    private BinarySearchTree[] table;
    private int size;
    private int arrayLength;
    public HashTable(int size){
        table=new BinarySearchTree[size];
        arrayLength=size;
        this.size=0;
    }

    public int hash(String str){
        int l=str.length();//length of string
        int index=1;
        for(int i=0;i<l;i++){
            if(index>500){
                index=index%500;
            }
            if(i%2==0&&(str.charAt(i)-48)!=0){
                if(index==0){
                    index++;
                }
                index=index*((str.charAt(i)-48)+1);
            }else if(i%2==0&&(str.charAt(i)-48)==0) {
                index=index+10;
            } else if(i%2==1) {
                if(index<0){
                    index=index*(-1);
                }
                index=index-(str.charAt(i)-48);
            }
        }
        //multiply and add one by one
        //if 2 strings have the same chars but different order
        //they will end up with different index

        if(index<0){
            index=(index*(-1))%(arrayLength);
        }//make it positive

        if(index<(arrayLength/4)){
            index=index+str.charAt(0)*str.charAt(0);
        }//the data gathers around the first 25%
        //to deal with it add the sqrt of the first char
        //to shuffle again

        return index%arrayLength;
    }

    public void put(String key, Object value){
        int position=hash(key);
        if (table[position]==null){
            table[position]=new BinarySearchTree(value,key,null);
        }else{
            table[position].insert(key,value);
        }
        size++;
    }

    public Object get(String key){
        int position=hash(key);
        if (table[position]==null){
            return null;
        }else{
            return table[position].search(key);
        }
    }

    public boolean hasKey(String key){
        return (get(key)!=null);
    }

    public void remove(String key){
        int position=hash(key);
        if (table[position]!=null){
            table[position].delete(key);
        }
        size--;

    }

    public int size(){
        return size;
    }

    public int[] distribution(){
        int[] distr=new int[arrayLength];
        for(int i=0;i<arrayLength;i++){
            if(table[i]==null){
                distr[i]=0;
            }else{
                distr[i]=table[i].size();
            }
        }
        return distr;
    }

    public String[] keys(){
        String[] keysArray=new String[size];
        Queue<String> keysQue=new LinkedList<String>();
        for(int i=0;i<arrayLength;i++){
            if(table[i]!=null&&(table[i].key!=null)){
                toKeysQue(table[i],keysQue);
            }
        }
        for(int i=0;i<size;i++){
            keysArray[i]=keysQue.poll();
        }
        return keysArray;
    }

    private void toKeysQue(BinarySearchTree binarySearchTree, Queue<String> keysQue ){
        if(binarySearchTree!=null) {
            toKeysQue(binarySearchTree.left,keysQue);
            keysQue.add(binarySearchTree.key);
            toKeysQue(binarySearchTree.right,keysQue);
        }
    }





}
