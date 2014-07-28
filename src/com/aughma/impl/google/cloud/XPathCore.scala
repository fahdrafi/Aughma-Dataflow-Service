package com.aughma.impl.google.cloud

import javax.servlet.http._
import scala.xml.XML
import scala.xml.NodeSeq
import com.aughma.xml.xpath.XPath

class XPathCore extends NodeCore {
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