
class Node:

    left_node = None
    right_node = None
    data = None

    def __init__(self):
        self.data=None

    def get_data(self):
        return self.data

    def set_data(self,data):
        self.data=data
    
    def get_right_node(self):
        return self.right_node

    def get_left_node(self):
        return self.left_node

    def set_right_node(self, right_node):
        self.right_node = right_node

    def set_left_node(self, left_node):
        self.left_node = left_node

    def __str__(self):
        return str(self.data)

    def __iter__(self):
        return inOrderTraversal(self)





class BinarySearchTree:

    name = None
    root = None

    def __init__(self, name, root):
        self.name = name
        self.root=root

    def set_root(self,root):
        self.root = root

    def get_root(self):
        return self.root

    def add_all(self, *list):
        self.root=None
        self.root=Node()
        self.root.set_data(list[0])
        for x in range (1,len(list)):
            self.add_element(list[x],self.root,Node())


    def add_element(self, element ,currentNode, node_to_be_added):

        if currentNode.get_data() > element:
            if currentNode.get_left_node() is None:
                node_to_be_added.set_data(element)
                currentNode.set_left_node(node_to_be_added)
            else:
                self.add_element(element,currentNode.get_left_node(),node_to_be_added)

        else:
            if currentNode.get_right_node() is None:
                node_to_be_added.set_data(element)
                currentNode.set_right_node(node_to_be_added)
            else:
                self.add_element(element,currentNode.get_right_node(),node_to_be_added)


    def __str__(self):
        return "["+self.name+"]"+self.preOrderTraversal("",self.root)



    def preOrderTraversal(self,str,currentNode):
        str=str+currentNode.__str__()
        if currentNode.get_left_node() is not None:
            str=str+" L:("
            str=self.preOrderTraversal(str,currentNode.get_left_node())+")"


        if currentNode.get_right_node() is not None:
            str=str+" R:("
            str=self.preOrderTraversal(str,currentNode.get_right_node())+")"

        return str

    """
    def inorderTraversal(self,currentNode,list):
        if currentNode.get_left_node() is not None:
            list= self.inorderTraversal(currentNode.get_left_node(),list)

        list.append(currentNode)

        if currentNode.get_right_node() is not None:
            list=self.inorderTraversal(currentNode.get_right_node(),list)

        return list
    """
"""
def inorder(t):


    if t:
        for x in inorder(t.left_node):
            yield x

        yield t.data

        for x in inorder(t.right_node):
            yield x
            """




def inOrderTraversal(currentNode):

    if currentNode.get_left_node() is not None:
        for d in currentNode.get_left_node().__iter__():
            yield d

    yield currentNode

    if currentNode.get_right_node() is not None:
        for d in currentNode.get_right_node().__iter__():
            yield d

class Merger:

    def merge(t1,t2):
        merged_list=[]

        list1 = []
        list2 = []

        for x in t1.root:
            list1.append(x)

        for x in t2.root:
            list2.append(x)

        index1 = 0
        index2 = 0

        remaining_index = 0

        while index1<len(list1) and index2<len(list2):
            if(list1[index1].get_data()<list2[index2].get_data()):
                merged_list.append(list1[index1].get_data())
                index1=index1+1

            else:
                merged_list.append(list2[index2].get_data())
                index2=index2+1


        if(index1==len(list1)):
            remaining_index=len(list2)-index2
            for i in range(0,remaining_index):
                merged_list.append(list2[index2].get_data())
                index2 = index2 + 1

        else:
            remaining_index = len(list1) - index1
            for i in range(0, remaining_index):
                merged_list.append(list1[index1].get_data())
                index1= index1 + 1

        return merged_list


if __name__ == "__main__":
    t1 = BinarySearchTree(name="Oak", root=Node())
    t2 = BinarySearchTree(name="Birch", root=Node())
    t3= BinarySearchTree(name="One Node Tree",root=Node())

    t1.add_all(5, 3, 9, 0)
    t2.add_all(1, 0, 10, 2, 3, 6, 5, 7, 8, 4, 9)
    t3.add_all(20)
    print(t1)
    print(t2)
    print(t3)

    for x in t1.root:
        print(x)
    for x in t2.root:
        print(x)
    for x in t3.root:
        print(x)
    print(Merger.merge(t1, t2))

    tree_one = BinarySearchTree(name="merge1", root=Node())
    tree_two = BinarySearchTree(name="merge2", root=Node())

    tree_one.add_all(14, 11, 8, 5, 6, 9, 12, 15, 18)
    tree_two.add_all(10, 9, 16, 13, 17)

    print(tree_one)
    print(tree_two)
    print(Merger.merge(tree_one,tree_two))









