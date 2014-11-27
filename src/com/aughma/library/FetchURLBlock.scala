package com.aughma.library

import com.aughma.dataflow.TransformBlock
import scala.io.Source

object FetchURLBlock extends TransformBlock {
  override val inputs
  override def Transform(params: Map[String, String]): Map[String, String] = {
	  val url = params("url")
	  Map("Response"->"Some response from server")
	}
}