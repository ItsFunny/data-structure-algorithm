package list_algorithm

/*
	判断一个链表是否有环
 */
type ListNode struct {
	data interface{}
	next *ListNode
}
type LinkedList struct {
	root *ListNode
	size int
}

func (l *LinkedList)Push(value interface{}){
	newNode:=&ListNode{
		data: value,
	}
	if nil==l.root {
		l.root=newNode
	}else{
		tempNode:=l.root
		for nil!=tempNode.next{
			tempNode=tempNode.next
		}
		tempNode.next=newNode
	}
	l.size++
}

// true :loop else non-loop
// 遇到的坑: 内部的quickNode 要多走一步,而不是所初始化的时候多走一步,如果内部不多走一步是永远与slowNode相连的
func (l *LinkedList)ValidIfLoop()bool{
	if l.size==0 {
		return false
	}
	slowNode:=l.root
	quickNode:=slowNode.next
	for {
		if nil==quickNode{
			return false
		}else if quickNode==slowNode{
			return true
		}else {
			// 遇到一个坑,这里不能直接把quickNode的值给他
			// 不过遇到这个坑的缘故在于少了 内部的if那块代码,如果没有那块代码,quickNode与slowNode相连
			slowNode=slowNode.next
			quickNode=quickNode.next
			if nil!=quickNode {
				quickNode=quickNode.next
			}
		}
	}
}