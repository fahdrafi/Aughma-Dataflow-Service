package com.aughma.library

import com.aughma.dataflow.TransformBlock

object FetchURLBlock extends TransformBlock {

  override def Transform(params: Map[String, String]): Map[String, String] = {
	  Map("Response"->"Some response from server")
	}
}