package main

func main() {

}

type treeNode struct {
	data       interface{}
	leftChild  *treeNode
	rightChild *treeNode
}

type BinaryTree struct {
	root *treeNode
}

func ( *BinaryTree)insert(treeNode *treeNode){



}

func createBinaryTree(arr []int) *BinaryTree {

	tree := &BinaryTree{
		root: &treeNode{data: arr[0]},
	}



}
