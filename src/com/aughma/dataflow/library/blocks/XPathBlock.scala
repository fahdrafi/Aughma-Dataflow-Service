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
import scala.xml.XML
import scala.xml.NodeSeq
import com.aughma.xml.xpath.XPath

class XPathBlock extends AppEngineBlock {
	val description =
		<NodeCore>
			  <Stateful>false</Stateful>
			  <Description>
			  	This node extracts xpath query from an xml
			  </Description>
			  <Input type="string" name="xml" />
			  <Parameter type="string" name="xpath" />
			  <Output type="string" name="data" />
		</NodeCore>
	  
	  
	def ProcessInput(input: NodeSeq): NodeSeq = {
	  val xml = XML.loadString((input\\"xml").theSeq.head.text)
	  val xpath = (input\\"xpath").theSeq.head.text
	  <Output>{
	    XPath(xpath).find(xml).toNodeSeq
	  }</Output>
	}
	
}