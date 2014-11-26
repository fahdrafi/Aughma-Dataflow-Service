package com.aughma.dataflow

trait GeneratorBlock extends Block {
	val outputs: Map[String, OutputPort]
}