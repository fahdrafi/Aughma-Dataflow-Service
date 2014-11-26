package com.aughma.dataflow

trait TransformBlock extends ConsumerBlock with GeneratorBlock {
	def Transform(parameters: Map[String, String]): Map[String, String]
}