package com.aughma.dataflow

trait ConsumerBlock extends Block {
	val inputs: Map[String, InputPort]
	def Consume(input: Map[String, String])
	override def trigger = {
	  Consume(inputs.mapValues(_.retrieve))
	}
}