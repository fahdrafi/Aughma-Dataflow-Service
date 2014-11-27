package com.aughma.dataflow

trait TransformBlock extends ConsumerBlock with GeneratorBlock {
  override def trigger = {
	val out = Transform(inputs.mapValues(_.retrieve))
	outputs.foreach(output => output._2.links.foreach(_.target.post(out(output._1))))
  }
  def Transform(parameters: Map[String, String]): Map[String, String]
}