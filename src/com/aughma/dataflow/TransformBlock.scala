package com.aughma.dataflow

trait TransformBlock extends Block with HasInputPorts with HasOutputPorts {
  override def trigger = {
  	val results = Transform(inputPorts.mapValues(_.Retrieve))
    results.foreach(result => 
      outputPorts(result._1)
        .linkedPorts.foreach(linkedPort=>
          linkedPort.Post(result._2)
        )
    )
  }
  def Transform(parameters: Map[String, String]): Map[String, String]
}