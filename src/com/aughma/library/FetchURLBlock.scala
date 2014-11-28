package com.aughma.library

import com.aughma.dataflow.TransformBlock
import scala.io.Source
import java.util.logging.Level

object FetchURLBlock extends TransformBlock {
  val INPUTURL = "url"
  val OUTPUTRESPONSE = "Response"
  val errorPort = new LogOutputPort("ERROR", Level.SEVERE)
  val inputPorts = Map(INPUTURL->new ConstantInputPort("http://www.google.com"))
  val outputPorts = Map(OUTPUTRESPONSE -> new LogOutputPort("FetchURL-Response"))
  override def Transform(params: Map[String, String]): Map[String, String] = {
	  val url = params(INPUTURL)
	  Map(OUTPUTRESPONSE->"Some response from server")
	}
}