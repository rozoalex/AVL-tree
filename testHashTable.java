/**
 * Created by rozoa on 7/1/2016.
 */
public class testHashTable {
    public static void main(String [ ] args){
        HashTable ht=new HashTable(20);
        ht.put("Yuanze Hu","Yuanze Hu");
        ht.put("is","is");
        ht.put("in","in");
        ht.put("cosi21A","cosi21A");
        ht.put(".",".");
        ht.put("His","His");
        ht.put("grade","grade");
        ht.put("100","100");
        ht.put(";",";");
        ht.put("The","The");
        ht.put("next","next");
        ht.put("COSI","COSI");
        ht.put("class","class");
        ht.put("will-be","will-be");
        ht.put("29","29");
        System.out.println("size is "+ht.size());
        printArray(ht.keys());
        printArray(ht.distribution());


        System.out.println();
        System.out.println();
        HashTable hht=new HashTable(20);
        String[] st={"23","11","98","56","13","33","58","45","100","7","99999","35738","234","246","2","1","203","24"};
        for(int i=0;i<st.length;i++){
            hht.put(st[i],st[i]);
        }
        System.out.println("size is "+hht.size());
        System.out.print("keys: ");
        printArray(hht.keys());
        System.out.print("distribution: ");
        printArray(hht.distribution());

        hht.remove("13");
        System.out.println("remove 13");
        System.out.print("keys: ");
        printArray(hht.keys());
        System.out.print("distribution: ");
        printArray(hht.distribution());

        hht.remove("203");
        System.out.println("remove 203");
        System.out.print("keys: ");
        printArray(hht.keys());
        System.out.print("distribution: ");
        printArray(hht.distribution());

    }

    public static void printArray(String[] anArray){
        for (int i = 0; i < anArray.length; i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(anArray[i]);
        }
        System.out.println( );
    }

    public static void printArray(int[] anArray){
        for (int i = 0; i < anArray.length; i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(anArray[i]);
        }
        System.out.println( );
    }


}
