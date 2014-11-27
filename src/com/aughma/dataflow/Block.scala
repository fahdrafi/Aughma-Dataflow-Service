package com.aughma.dataflow

trait Block {
	val errorPort: OutputPort
	def trigger
}