package com.aughma.dataflow

trait HasInputPorts {
  protected val inputPorts: Map[String, InputPort]
  def Post(portName: String, value: String) = inputPorts(portName).Post(value)
}