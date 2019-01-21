package list

import (
	"fmt"
	"github.com/influxdata/influxdb/pkg/testing/assert"
	"testing"
)

var testArray = []interface{}{1, 2, 3, 4, 5, 6, 7, 8}
var emptyArray = []interface{}{}
var singleValueArray = []interface{}{0}

func TestCircularList_AddNode(t *testing.T) {
	circularList := CircularList{}
	for _, v := range testArray {
		circularList.AddNode(v)
	}
	values, e := circularList.IterateNode()
	if nil != e {
		panic(e)
	}
	for _, v := range values {
		fmt.Printf("%d->", v)
	}
	fmt.Println()
	successArray := []interface{}{1, 2, 3, 4, 5, 6, 7, 8}
	assert.Equal(t, values, successArray)
}

func TestCircularList_Remove(t *testing.T) {

}

func TestCircularList_RemoveByIndex(t *testing.T) {

}

func TestCircularList_IterateNode(t *testing.T) {

}
