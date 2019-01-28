package common

import "github.com/pkg/errors"

var (
	NilPointerError    = errors.New("Nil value")
	NoSuchElementError = errors.New("No Such Element")
)
