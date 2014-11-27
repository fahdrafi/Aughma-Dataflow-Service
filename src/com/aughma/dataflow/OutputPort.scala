package com.aughma.dataflow

trait OutputPort extends Port {
	val linkedPorts: List[InputPort]
}