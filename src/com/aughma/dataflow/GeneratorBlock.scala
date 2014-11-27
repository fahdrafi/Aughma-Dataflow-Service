package com.aughma.dataflow

trait GeneratorBlock extends Block {
	val outputs: Map[String, OutputPort]
	def Generate: Map[String, String]
	override def trigger = {
	  Generate.foreach(output => outputs(output._1).post(output._2))
	}
}