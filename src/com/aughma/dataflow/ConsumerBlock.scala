package com.aughma.dataflow

trait ConsumerBlock extends Block with HasInputPorts {
	def Consume(input: Map[String, String])
	override def trigger = {
	  Consume(inputPorts.mapValues(_.Retrieve))
	}
}