package com.aughma.dataflow

trait ConsumerBlock extends Block {
	val inputs: Map[String, InputPort]
}