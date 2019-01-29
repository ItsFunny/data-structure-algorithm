package hash_conflitct

/*
	解决hash冲突的第二种方法:链地址法
 */

type LinkNode struct {
	data interface{}
	next *LinkNode
}

type LinkedList struct {
	head *LinkNode
	tail *LinkNode
	size int
}

func (l *LinkedList) AddNode(value interface{}) {
	newNode := &LinkNode{data: value}
	if nil == l.head {
		l.head = newNode
	} else {
		l.tail.next = newNode
	}
	l.tail = newNode
	l.size++
}

func (l *LinkedList) PopNode() interface{} {
	if nil == l.head {
		return nil
	}
	value := l.head.data
	l.head = l.head.next
	return value
}

type HashMap struct {
	elements []*LinkedList
	size int
	loadFactor float32
}

