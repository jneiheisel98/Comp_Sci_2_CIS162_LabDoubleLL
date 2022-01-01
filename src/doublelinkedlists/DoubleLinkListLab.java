package doublelinkedlists;

public class DoubleLinkListLab {
    private DNode top;
    private DNode tail;

    public DoubleLinkListLab() {
        top = null;
        tail = null;
    }
    /****************************************************************
     *
     * Determines the size, that is, the number of elements in the list
     *
     * @return  the size of the list
     *
     ****************************************************************/
    public int getLen() {
        int counter = 0;
        DNode temp = tail;
        if(temp == null){
            return 0;
        }
        counter++;
        while (temp.getPrev() != null){
            counter++;
            temp = temp.getPrev();
        }
        return counter;
    }



    /****************************************************************
     *
     * Inserts a node before a specific index.  When the list is empty
     * that is, top = null, then the index must be 0. After the first
     * element is added, index must be:  0 <= index < size of list
     *
     * @param index a specific index into the list.
     *
     * @throws MyIllegalArgumentException if index < 0 or
     *           index >= size of the list

     ****************************************************************/

    public void insertBefore (int index, String data) {
        DNode temp = top;
        if(temp == null && index ==0){
            tail = top = new DNode(data, null, null);
        }
        else if(temp == null && index < 0){
            throw new MyIllegalArgumentException();
        }

        else if(temp != null && index > 0 && index < getLen()){
            int tempCounter = index;
            while (tempCounter >1){
                temp = temp.getNext();
                tempCounter--;
            }
            temp.setNext(new DNode(data, temp.getNext(), temp));
                    temp = temp.getNext();
            if(temp.getNext()!= null) {
                temp.getNext().setPrev(temp);
            }

        }
        else if(temp != null && index == 0){
            if(temp == tail){
            top = new DNode(data, top,null);
tail = top.getNext();
                tail.setPrev(top);
            }
            else{
                top = new DNode(data, temp.getNext(), top);
            }
        }
        else{
            throw new MyIllegalArgumentException();
        }
    }



    /****************************************************************
     *
     * Inserts a node after a specific index.  When the list is empty
     * that is, top = null, then the index must be 0. After the first
     * element is added, index must be:  0 <= index < size of list
     *
     * @param index a specific index into the list.
     *
     * @throws MyIllegalArgumentException if index < 0 or
     *           index >= size of the list

     ****************************************************************/

    public void insertAfter (int index, String data) {
        DNode temp = top;
        if(temp == null && index ==0){
            top = new DNode(data, null, null);
        }
        else if(temp == null && index > 0){
            throw new MyIllegalArgumentException();
        }
        else if( index == 0 && temp !=null&& temp ==tail){
            temp.setNext(new DNode(data, temp.getNext(), temp));
            tail = top.getNext();

        }
        else if( index == 0 && temp !=null&& temp !=tail){
            temp.setNext(new DNode(data, temp.getNext(), temp));
            temp = temp.getNext();
            if(temp.getNext()!= null) {
                temp.getNext().setPrev(temp);
            }
        }



        else if(temp != null && index > 0 && index < getLen()){
            int tempCounter = index;
            while (tempCounter >0){
                temp = temp.getNext();
                tempCounter--;
            }
            temp.setNext(new DNode(data, temp.getNext(), temp));
            if(temp.getNext().getNext() == null){
                tail = temp.getNext();
            }
            temp.getNext().setPrev(temp);
            temp = temp.getNext();
            if(temp.getNext()!= null) {
                temp.getNext().setPrev(temp);
            }
        }

        else{
            throw new MyIllegalArgumentException();
        }
    }


    /****************************************************************
     *
     * Removes the top element of the list
     *
     * @return returns the element that was removed.
     *
     * @throws MyIllegalArgumentException if top == null, that is,
     *            there is no list.
     *
     ****************************************************************/

    public String removeTop () {
        DNode temp = top;
        String removed = null;
        if(temp ==null){
            throw new MyIllegalArgumentException();
        }
        else if(temp !=null){
            removed = temp.getData();
            top = temp.getNext();
            top.setPrev(null);
        }
        return removed;
    }


    /****************************************************************
     *
     * This Method removes a node at the specific index position.  The
     * first node is index 0.
     *
     * @param index the position in the linked list that is to be
     *           removed.  The first position is zero.
     *
     * @throws MyIllegalArgumentException if index < 0 or
     *           index >= size of the list
     *
     ****************************************************************/
    public String delAt(int index) {
        String temp = null;
        DNode temporaryNode = top;
        if(index<0 || index >= getLen()){
            throw new MyIllegalArgumentException();


        }

        else if (index ==0 && temporaryNode !=null){
            temp = temporaryNode.getData();
            top = temporaryNode.getNext();
        }
        else if (index ==0 && temporaryNode ==null){
            throw new MyIllegalArgumentException();
        }
        else{
            int tempCounter = index;
            while (tempCounter >1){
                temporaryNode = temporaryNode.getNext();
                tempCounter--;
            }
            temp = temporaryNode.getNext().getData();
            if(temporaryNode.getNext().getNext() == null){
                tail = temporaryNode;
            }
            temporaryNode.setNext(temporaryNode.getNext().getNext());
            if(temporaryNode.getNext()!=null) {
                temporaryNode.getNext().setPrev(temporaryNode);
            }
        }
        return temp;
    }

    // A simple testing program.  Not complete but a good start.
    public static void main (String[] args){
        DoubleLinkListLab list = new DoubleLinkListLab();

        list.display();
        System.out.println ("Current length = " + list.getLen());

        list.insertBefore(0, "apple");
        list.insertBefore(0, "pear");
        list.insertBefore(1, "peach");
        list.insertBefore(1, "cherry");
        list.insertBefore(3, "donut");
        list.display();

        list.insertAfter(4, "cookie");
        list.display();
        list.insertAfter(0, "pear");
        list.display();
        list.insertAfter(1, "peach");
        list.display();
        list.insertAfter(1, "cherry");
        list.display();
        list.insertAfter(3, "donut");
        list.display();
System.out.println(list.getLen());
        list.removeTop();
        System.out.println(list.getLen());
        System.out.println("Deleted pos 8 with value of: " +list.delAt(8));
        System.out.println("Deleted pos 4 with value of: " +list.delAt(4));
        System.out.println("Deleted pos 2 with value of: " +list.delAt(2));
        System.out.println("Deleted pos 0 with value of: " +list.delAt(0));
        list.removeTop();
        list.removeTop();

        list.display();
        list.displayFromTail();
        System.out.println ("Current length = " + list.getLen());
    }
    // Once you are done!
// Change this method to go from the tail up!
    public void display() {
        DNode temp = top;

        System.out.println ("___________ List ________________________");
        while (temp != null) {
            System.out.println (temp.getData());
            temp = temp.getNext();
        }
    }

    public void displayFromTail() {
        DNode temp = tail;

        System.out.println ("___________ List ________________________");
        while (temp != null) {
            System.out.println (temp.getData());
            temp = temp.getPrev();
        }
    }
}

