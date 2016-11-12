public class Main {

    public static void main(String args[]){
        LinkedList<String> linkedList = new LinkedList<String>();

        linkedList.add("Daniel");
        linkedList.add("Micheal");
        linkedList.add("Vince");
        linkedList.add("Lucas");

        linkedList.delete("Lucas");

        System.out.println(linkedList);
    }
}
