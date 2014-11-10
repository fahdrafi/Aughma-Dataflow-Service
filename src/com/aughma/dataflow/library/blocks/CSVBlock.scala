/*
 * Copyright 2014 Aughma Limited
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.aughma.dataflow.library.blocks

import javax.servlet.http._
import scala.xml._
import com.aughma.dataflow.impl.google.AppEngineBlock
import com.aughma.dataflow.platform.Block

class CSVBlock extends Block with AppEngineBlock {
  
	def Initialize(params: Map[String, String]) = {}
	
	def Process(datum: Datum): Seq[Datum] = {
	  
	}
  
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