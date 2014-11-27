package com.aughma.library

import com.aughma.dataflow.TransformBlock
import scala.io.Source

object FetchURLBlock extends TransformBlock {
  val errorPort = new ConsoleOutputPort("ERROR")
  val inputPorts = Map("url"->new ConstantInputPort("http://www.google.com"))
  val outputPorts = Map("Response" -> new ConsoleOutputPort("FetchURL-Response"))
  override def Transform(params: Map[String, String]): Map[String, String] = {
	  val url = params("url")
	  Map("Response"->"Some response from server")
	}
}