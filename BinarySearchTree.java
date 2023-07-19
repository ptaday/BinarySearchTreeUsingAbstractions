import java.util.*;
import java.util.function.Consumer;

class Node<T extends Comparable>  {


    T data;
    Node left;
    Node right;

    boolean isVisited;

    Node()
    {
        isVisited=false;
    }

    Node(T t)
    {
        data=t;
        isVisited=false;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public T getData() {
        return data;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public boolean isVisited() {
        return isVisited;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}

public class BinarySearchTree<T extends Comparable> implements Iterable{

    String name;

    Node root;

    public Node getRoot() {
        return root;
    }

    int numberOfNodes;

    public int getNumberOfNodes()
    {
        return numberOfNodes;
    }
    BinarySearchTree(String name)
    {
        this.name=name;
    }

    BinarySearchTree()
    {

    }

    public void addAll(List<T> toBeAdded)
    {
        this.numberOfNodes=toBeAdded.size();

        if(toBeAdded!=null||toBeAdded.size()>0) {
             root = new Node( toBeAdded.get(0));
             listOfNodes.add(root);



            for(int i=1; i<toBeAdded.size();i++)
            {
               iterateOverTree(root,toBeAdded.get(i));
            }
        }
    }

    List<Node<T>> listOfNodes=new ArrayList<>();

    public  void iterateOverTree(Node currentNode, T data)
    {
        Node node=new Node(data);

        if(data.compareTo(currentNode.getData())<0)
        {
            if(currentNode.getLeft()!=null)
                iterateOverTree(currentNode.getLeft(), data);

            else {
                currentNode.setLeft(node);
                listOfNodes.add(node);
            }
        }
        else
        {
            if(currentNode.getRight()!=null)
                iterateOverTree(currentNode.getRight(), data);

            else {
                currentNode.setRight(node);
                listOfNodes.add(node);
            }
        }
    }

    int nextCounter=0;
    @Override
    public Iterator iterator() {

        Node start=this.root;

        BinarySearchTree<T> temp=this;

    Iterator<BinarySearchTree<T>> newIterator= new Iterator<BinarySearchTree<T>>() {
        @Override
        public boolean hasNext() {
            if(nextCounter< temp.getNumberOfNodes())
                return true;

            else return false;
        }

        @Override
        public BinarySearchTree<T> next() {

            try {
                nextCounter++;
                inorderTraversal(temp.root);
            } catch (IllegalArgumentException e) {

            } finally {


                Node<T> newNode = reference;

                BinarySearchTree<T> toBeReturned = new BinarySearchTree<>();

                toBeReturned.addAll(Arrays.asList(newNode.getData()));

                if (nextCounter == temp.numberOfNodes - 1) {
                    reference = null;
                }

                return toBeReturned;
            }
        }


    };


    return newIterator;

    }

    @Override
    public void forEach(Consumer action) {

        Iterator newIterator=this.iterator();

        while(newIterator.hasNext())
        {
            BinarySearchTree<T> binarySearchTree= (BinarySearchTree<T>) newIterator.next();

            System.out.println(binarySearchTree.root.toString());

        }
        nextCounter=0;

        this.resetSetVisited();
        /*for(int i=0;i<this.listOfNodes.size();i++)
        {
            listOfNodes.get(i).setVisited(false);
        }*/
    }

    @Override
    public String toString() {
        return printAll(this.root,"["+this.name.toString()+"]"+" ");
    }

    private String printAll(Node currentRoot,String s) {

        if(currentRoot.getLeft()!=null)
        s=s+currentRoot.getData().toString()+" ";

        else
            s=s+currentRoot.getData().toString()+"";


       if(currentRoot.getLeft()!=null)
        s=printAll(currentRoot.getLeft(),s+"L:(")+")";

        if(currentRoot.getRight()!=null) {
            s = printAll(currentRoot.getRight(), s + " R:(")+")";
        }

        return s;

    }

    Node<T> reference=new Node<>();

    public Node<T> getReference() {
        return reference;
    }

    public void inorderTraversal(Node currentNode) throws IllegalArgumentException
    {
       if(currentNode.left!=null)
           inorderTraversal(currentNode.left);

       if(currentNode.isVisited())
       {
           if(currentNode.right!=null)
               inorderTraversal(currentNode.right);
       }

        else{
            currentNode.setVisited(true);
            reference=currentNode;
            throw new IllegalArgumentException();

        }

    }


    public static <T extends Comparable<T>> List<T> merge(BinarySearchTree<T> t1, BinarySearchTree<T>t2)
    {

        List<T> listToBeAdded=new ArrayList<>();

       int firstTreeNodeCounter=0;
       int secondTreeNodeCounter=0;

        Thread firstThread=new Thread(new Runnable() {
           @Override
           public void run() {

               try {
                   t1.inorderTraversal(t1.getRoot());
               }
               catch (IllegalArgumentException e)
               {

               }
           }
       });

       Thread secondThread=new Thread(new Runnable() {
           @Override
           public void run() {

               try {
                   t2.inorderTraversal(t2.getRoot());
               }
               catch(IllegalArgumentException e)
               {

               }
           }
       });



       boolean firstTreeStopped=true;
       boolean secondTreeStopped=true;


       while(firstTreeNodeCounter<=t1.numberOfNodes-1&&secondTreeNodeCounter<=t2.numberOfNodes-1)
       {
          if(firstTreeStopped)
              firstThread.run();

          if(secondTreeStopped)
              secondThread.run();

          if(t1.getReference().getData().compareTo(t2.getReference().getData())<=0)
           {
               if(!firstTreeStopped)
                   firstTreeStopped=true;


              listToBeAdded.add(t1.reference.getData());
              firstTreeNodeCounter++;
              secondTreeStopped=false;
           }

           else
           {
               if(!secondTreeStopped)
                   secondTreeStopped=true;

               listToBeAdded.add(t2.reference.getData());
               secondTreeNodeCounter++;
               firstTreeStopped=false;
           }
       }
       int difference=0;

       if(firstTreeNodeCounter<t1.getNumberOfNodes()) {
           difference = t1.getNumberOfNodes() - firstTreeNodeCounter-1;
           listToBeAdded.add(t1.getReference().getData());
           firstTreeStopped=true;
           secondTreeStopped=false;
       }

       if(secondTreeNodeCounter<t2.getNumberOfNodes()) {
           difference = t2.getNumberOfNodes() - secondTreeNodeCounter-1;
           listToBeAdded.add(t2.getReference().getData());
           firstTreeStopped = false;
           secondTreeStopped = true;
       }

       for(int i=0;i<difference;i++)
       {
           if(firstTreeStopped&&!secondTreeStopped) {
               firstThread.run();
               listToBeAdded.add(t1.reference.getData());
           }

           else {
               secondThread.run();
               listToBeAdded.add(t2.reference.getData());
           }

       }

       t1.resetSetVisited();
       t2.resetSetVisited();

       return listToBeAdded;
    }

    public void resetSetVisited()
    {
        for(int i=0;i<this.listOfNodes.size();i++)
        {
            listOfNodes.get(i).setVisited(false);
        }
    }

    public static void main(String[] args) {
// each tree has a name, provided to its constructor
        BinarySearchTree<Integer> t1 = new BinarySearchTree<>("Oak");
// adds the elements to t1 in the order 5, 3, 0, and then 9
        t1.addAll(Arrays.asList(5, 3, 0, 9));
        BinarySearchTree<Integer> t2 = new BinarySearchTree<>("Maple");
// adds the elements to t2 in the order 9, 5, and then 10
        t2.addAll(Arrays.asList(9, 5, 10));
        System.out.println(t1);// see the expected output for exact format
        t1.forEach(System.out::println);// iteration in increasing order
        System.out.println(t2); // see the expected output for exact format
        t2.forEach(System.out::println); // iteration in increasing order
        BinarySearchTree<String> t3 = new BinarySearchTree<>("Cornucopia");
        t3.addAll(Arrays.asList("coconut", "apple", "banana", "plum", "durian",
                "no durians on this tree!", "tamarind"));
        System.out.println(t3); // see the expected output for exact format
        t3.forEach(System.out::println);

        BinarySearchTree<Double> t4 = new BinarySearchTree<>("Double");
        t4.addAll(Arrays.asList(3.2,6.3,9.4,12.2));
        System.out.println(t4); // see the expected output for exact format
        t4.forEach(System.out::println); // iteration in increasing order

        List<Integer> integerList=merge(t1,t2);

        for(int i=0; i<integerList.size();i++)
        {
            System.out.println(integerList.get(i));
        }
    }


}
