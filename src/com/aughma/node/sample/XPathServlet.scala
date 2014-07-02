package com.aughma.node.sample

import javax.servlet.http._
import scala.xml.XML
import scala.xml.Node
import scala.xml.NodeSeq

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

    def XPath(xmlNode: Node, xPath: String) : NodeSeq = {
      xPath match {
        case "" => NodeSeq.Empty
        case p if p.startsWith("//") && !p.substring(2).contains('/') => xmlNode \\ p.substring(2)
        case p if p.startsWith("/") && !p.substring(1).contains('/') => xmlNode \ p.substring(1)
        case p if p.startsWith("//") => XPath(xmlNode \\ p.substring(2, p.indexOf('/', 2)), p.substring(p.indexOf('/', 2)))
        case p if p.startsWith("/") => XPath(xmlNode \ p.substring(1, p.indexOf('/', 1)), p.substring(p.indexOf('/', 1)))
        case p if p == xmlNode.label => xmlNode
        case p => throw new Exception("xPath string start with '/' or '//'")
      }
	}
	
	def XPath(xmlSeq: NodeSeq, xPath: String) : NodeSeq = {
	  xmlSeq match {
	    case x: Node => throw new Exception("This call should have gone to the other implementation of XPath")
	    case x => NodeSeq.fromSeq(xmlSeq.theSeq.map(n => XPath(n, xPath).theSeq).flatten)
	  }
	}
	
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