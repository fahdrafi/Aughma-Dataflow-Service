package com.aughma.library

import com.aughma.dataflow.OutputPort

class ConsoleOutputPort(portName: String = "Default") extends OutputPort {
  val linkedPorts = List.empty
  def Post(value: String) = Console.out.println(portName + ": " + value)
}