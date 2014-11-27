package com.aughma.dataflow

trait HasOutputPorts {
  protected val outputPorts: Map[String, OutputPort]
}