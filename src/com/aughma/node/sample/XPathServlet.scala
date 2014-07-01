package com.aughma.node.sample

import javax.servlet.http._
import scala.xml.XML
import scala.io.Codec
import scala.xml.Elem

class XPathServlet extends AughmaServlet {
	override def doGet(req: HttpServletRequest, resp: HttpServletResponse) = {
	  val nodeDescription = 
		<NodeCore>
			  <Stateful>false</Stateful>
			  <Description>
			  	This node extracts xpath query from an xml
			  </Description>
			  <Input type="string" name="xml" />
			  <Output type="string" name="data" />
		</NodeCore>
	  
		resp.setContentType("application/xml")
		resp.getWriter().print(nodeDescription)
	}

//	def XPath(xmlElement: Elem, xPaths: List[String]): Any = {
//	  xPaths match {
//	    case Nil => None
//	    case head :: tail => Some(List(XPath(xmlElement, head))++XPath(xmlElement, tail))
//	  }
//	}
//	
//	def XPath(xmlElement: Elem, xPath: String): Any = {
//	  xPath match {
//	    case x if "[^/]*//".r.findFirstIn(x).isDefined =>  
//	  }
//	}
	
	override def doPost(req: HttpServletRequest, resp: HttpServletResponse) = {
	  val reqBody = getRequestBody(req)
	  val reqXML = XML.loadString(reqBody)
	  
	  val xml = XML.loadString((reqXML \\ "xml").text)
	  
//	  val data = io.Source.fromURL(url)(Codec.UTF8).mkString
	  
	  resp.setContentType("application/xml")
	  resp.getWriter().print(
		<Output>
			  <!--<data>{data}</data>-->
		</Output>
	  )
	}	
}