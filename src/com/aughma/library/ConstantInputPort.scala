package com.aughma.library

import com.aughma.dataflow.InputPort

class ConstantInputPort(value: String) extends InputPort {
  def Post(value: String) = {}
  def Retrieve = value;
}