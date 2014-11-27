package com.aughma.dataflow

trait Port {
	def post(data: String)
	def retrieve: String
}