package com.aughma.node

import javax.servlet.http._
import scala.xml._

class CSVCore extends NodeCore {
	val description =
		<NodeCore>
			  <Stateful>false</Stateful>
			  <Description>
			  	This node takes a csv with headers and provides all rows as the output data.
			  </Description>
			  <Input type="string" name="csv" />
			  <Output type="dynamic" name="data"/>
		</NodeCore>
	  
	  
	def ProcessInput(input: NodeSeq): NodeSeq = {
	  val csv = (input\\"csv").theSeq.head.text
	  
	  val lines = csv
	    .split(List('\r', '\n').toArray)
	    .filter(_.length > 0)

	  val headers = lines
	  	.head
	  	.split(',')
	  	
	  val data = lines
	  	.tail
	  	.map(_.split(','))
	  	.map(_.zip(headers).toList)
	  	.toSeq
	 
	  <Output/>.copy(child = data.map(record => {
	    <dynamic/>.copy(child = record.map(item => <a>{item._1}</a>.copy(label = item._2)))
	  }))
	  	
	}
	
}