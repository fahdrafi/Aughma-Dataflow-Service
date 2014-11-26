package com.aughma.dataflow

trait OutputPort extends Port {
	val links: List[Link]
}