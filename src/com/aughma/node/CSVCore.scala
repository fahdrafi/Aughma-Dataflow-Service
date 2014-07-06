package com.aughma.node

import scala.xml._

class CSVCore extends NodeCore {
	val description = <NodeDescription></NodeDescription> 
	def ProcessInput(input: NodeSeq): NodeSeq = {
		NodeSeq.Empty
	}
}