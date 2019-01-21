package list

import "github.com/pkg/errors"

// 循环链表

type CircularNode struct {
	data interface{}
	next *CircularNode
}

type CircularList struct {
	head *CircularNode
	tail *CircularNode
}

func (l *CircularList) AddNode(value interface{}) {
	newNode := &CircularNode{data: value}
	if nil == l.head {
		l.head = newNode
	} else {
		l.tail.next = newNode
	}
	l.tail = newNode
	l.tail.next = l.head
}

// 尾部删除
func (l *CircularList) Remove() (interface{}, error) {
	var removeValue interface{}
	if nil == l.head {
		return nil, errors.New("the list is empty")
	}
	tempNode := l.head
	for nil != tempNode.next && l.head != tempNode.next.next {
		tempNode = tempNode.next
	}
	if nil == tempNode.next {
		l.head, l.tail = nil, nil
		removeValue = tempNode.data
	} else {
		removeValue = tempNode.next.data
		tempNode.next = nil
		l.tail = tempNode
		l.tail.next = l.head
	}
	return removeValue, nil
}

func (l *CircularList) RemoveByIndex(index int) (interface{}, error) {
	var removeValue interface{}
	var tempNode *CircularNode
	if nil == l.head {
		return nil, errors.New("the list is empty")
	}
	if index < 0 {
		return nil, errors.New("index invalid,root index is 1")
	} else if index == 0 {
		removeValue = l.head.data
		l.head, l.tail = nil, nil
	} else {
		for i := 1; i < index; i++ {
			// 循环链表,这里不可能未nil,
			// 循环链表的删除需求与一般链表的删除操作不同,因为是一个环,所以会一直循环知道一个次数
			// 这里可以通过 index=index %size 进行优化,减少次数
			return nil, errors.New("IndexOutOfBoundException")
		}
		tempNode = tempNode.next
	}
	// 判断这个值是否存在
	if nil == tempNode.next {
		return nil, errors.New("IndexOutOfBoundException")
	}
	removeValue = tempNode.next.data
	// 判断是否是尾节点
	if l.tail == tempNode.next {
		l.tail = tempNode
		l.tail.next = l.head
	} else {
		tempNode.next = tempNode.next.next
	}
	return removeValue, nil
}

func (l *CircularList) IterateNode() ([]interface{}, error) {
	if nil == l.head {
		return nil, errors.New("NullPointerException")
	}
	tempNode := l.head
	values := make([]interface{}, 0)
	for nil != tempNode && tempNode != l.tail {
		values = append(values, tempNode.data)
		tempNode = tempNode.next
	}
	values = append(values, tempNode.data)
	return values, nil
}
