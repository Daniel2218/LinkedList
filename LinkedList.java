public class LinkedList<T> {
    public Node<T> linkedList;
    public int length = 0;

    public LinkedList() { }

    public boolean add (T data){

        if (linkedList == null) {
            linkedList = new Node(data);
            ++length;
            return true;
        }

        Node<T> current = linkedList;

        while (current.next != null) {
            current = current.next;
        }

        current.next = new Node(data);
        ++length;

        return true;
    }

    public boolean add (T data, int index) {
        Node current = linkedList;

        if (index == 0) {
            linkedList = new Node(data);
            linkedList.next = current;
        } else if (index == length - 1) {
            add(data);
        } else {
            while(current != null && index > 1) {
                current = current.next;
                index--;
            }

            Node theRest = current.next;
            current.next = new Node(data);
            current.next.next = theRest;
        }

        return true;
    }

    public boolean delete (int index) {
        Node current = linkedList;

        if (index == 0) {
            linkedList = linkedList.next;
        } else if (index == length - 1) {
            while (current.next.next != null) {
                current = current.next;
            }
            current.next = null;
        } else {
            while (current != null && index > 1) {
                current = current.next;
                index--;
            }
            current.next = current.next.next;
        }

        return true;
    }

    // need to fix when it deletes the last one
    public boolean delete(T data) {
        Node current = linkedList;
        if (current == null) {
            return false;
        } else if(linkedList.data.equals(data)){
            linkedList = linkedList.next;
        } else {
            while (current.next != null) {
                if(current.next.data.equals(data)){
                    if (current.next.next == null) {
                        current.next = null;
                        break;
                    } else {
                        current.next = current.next.next;
                    }
                }
                current = current.next;
            }
        }

        return true;
    }

    public boolean clear () {
        linkedList = null;
        return true;
    }

    public boolean search (T data) {
        Node current = linkedList;

        while (current != null) {
            if(current.data == data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public Node getRoot () {
        return linkedList;
    }

    public Node get (int index) {
        Node current = linkedList;

        while (current != null && index > 0) {
            current = current.next;
            index--;
        }

        return current;
    }

    public Node get (T val) {
        Node current = linkedList;

        while (current != null) {
            if (current.data == val) {
                return current;
            }
            current = current.next;
        }

        return null;
    }

    public Node sort () {
        return mergeSortLinkList(linkedList);
    }

    private Node mergeSortLinkList (Node linkedList) {

        // Break the list until list is null or only 1 element is present in List.
        if(linkedList == null || linkedList.next == null) return linkedList;

        // Break the linklist into 2 list.
        // Finding Middle node and then breaking the Linled list in 2 parts.
        // Now 2 list are, 1st list from start to middle and 2nd list from middle+1 to last.

        Node middle = getMiddle(linkedList);
        Node nextOfMiddle = middle.next;
        middle.next = null;

        // Again breaking the List until there is only 1 element in each list.
        Node left = mergeSortLinkList(linkedList);
        Node right = mergeSortLinkList(nextOfMiddle);

        // Once complete list is divided and contains only single element,
        // Start merging left and right half by sorting them and passing Sorted list further.
        Node sortedList = mergeTwoListRecursive(left, right);

        return sortedList;
    }

    // Recursive Approach for Merging Two Sorted List
    private Node mergeTwoListRecursive (Node leftStart, Node rightStart){
        if(leftStart==null) return rightStart;

        if(rightStart==null) return leftStart;

        Node temp = null;

        if(leftStart.compareTo(rightStart) < 0){
            temp = leftStart;
            temp.next = mergeTwoListRecursive(leftStart.next, rightStart);
        } else {
            temp = rightStart;
            temp.next = mergeTwoListRecursive(leftStart, rightStart.next);
        }

        return temp;
    }

    private Node getMiddle (Node startNode) {
        if(startNode == null){
            return startNode;
        }

        Node pointer1 = startNode;
        Node pointer2 = startNode;

        while(pointer2!=null && pointer2.next != null && pointer2.next.next != null){
            pointer1 = pointer1.next;
            pointer2 = pointer2.next.next;
        }

        return pointer1;
    }

    public String toString () {
        Node current = linkedList;
        String output = "";

        while (current.next != null) {
            output += current.data + " => ";
            current = current.next;
        }

        output += current.data;

        return output;
    }

    private class Node<T> implements Comparable<T>{

        private T data;
        private Node next;

        public Node (T data) {
            this.data = data;
            this.next = null;
        }

        public int compareTo (Node node) {
            return this.compareTo(node.data);
        }

        public int compareTo (Integer x) {
            return ((Integer) this.data).compareTo(x);
        }

        public int compareTo (Short x) {
            return ((Short) this.data).compareTo(x);
        }

        public int compareTo (Byte x) {
            return ((Byte) this.data).compareTo(x);
        }

        public int compareTo (Long x) {
            return ((Long) this.data).compareTo(x);
        }

        public int compareTo (String x) {
            return ((String) this.data).compareTo(x);
        }

        public int compareTo (Float x) {
            return ((Float) this.data).compareTo(x);
        }

        public int compareTo (Double x) {
            return ((Double) this.data).compareTo(x);
        }

        public int compareTo (Object o) {
            return 1;
        }

        public String toString () {
            Node current = this;
            String output = "";

            while (current.next != null) {
                output += current.data + " => ";
                current = current.next;
            }

            output += current.data;

            return output;
        }
    }
}
