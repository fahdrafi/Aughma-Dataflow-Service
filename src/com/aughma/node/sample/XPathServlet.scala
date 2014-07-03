package com.aughma.node.sample

import javax.servlet.http._
import scala.xml.XML
import scala.xml.Node
import scala.xml.NodeSeq

sealed trait XPathResult
case class NS(ns: NodeSeq) extends XPathResult
case class Str(str: String) extends XPathResult
case class StrSeq(strSeq: Seq[String]) extends XPathResult
case class XPS(xps: Seq[XPathResult]) extends XPathResult

class XPathServlet extends AughmaServlet {
	override def doGet(req: HttpServletRequest, resp: HttpServletResponse) = {
	  val nodeDescription = 
		<NodeCore>
			  <Stateful>false</Stateful>
			  <Description>
			  	This node extracts xpath query from an xml
			  </Description>
			  <Input type="string" name="xml" />
			  <Parameter type="string" name="xpath" />
			  <Output type="string" name="data" />
		</NodeCore>
	  
		resp.setContentType("application/xml")
		resp.getWriter().print(nodeDescription)
	}

    def XPath(xmlNode: Node, xPath: String) : XPathResult = {
      xPath match {
        case "" => NS(NodeSeq.Empty)
        case "/text()" => Str(xmlNode.text)
        case p if p.startsWith("//") && !p.substring(2).contains('/') => NS(xmlNode \\ p.substring(2))
        case p if p.startsWith("/") && !p.substring(1).contains('/') => NS(xmlNode \ p.substring(1))
        case p if p.startsWith("//") => XPath(xmlNode \\ p.substring(2, p.indexOf('/', 2)), p.substring(p.indexOf('/', 2)))
        case p if p.startsWith("/") => XPath(xmlNode \ p.substring(1, p.indexOf('/', 1)), p.substring(p.indexOf('/', 1)))
        case p if xmlNode.isInstanceOf[Node] && p == xmlNode.asInstanceOf[Node].label => NS(xmlNode)
        case p => throw new Exception("xPath \""+ p + "\" string start with '/' or '//'")
      }
	}
	
	def XPath(xmlSeq: NodeSeq, xPath: String) : XPS = {
	  xmlSeq match {
	    case x: Node => throw new Exception("This call should have gone to the other implementation of XPath")
	    case x => XPS(xmlSeq.theSeq.map(n => XPath(n, xPath)))
	  }
	}
	
	def XPathResultToData(xpr: XPathResult): NodeSeq = {
	  xpr match {
	    case NS(ns) => NodeSeq.fromSeq(ns.map(n => <data>{n.toString}</data>))
	    case Str(s) => <data>{s}</data>
	    case StrSeq(ss) => NodeSeq.fromSeq(ss.map(s => <data>{s}</data>))
	    case XPS(xps) => xps.map(xpr => XPathResultToData(xpr)).fold(NodeSeq.Empty)(_++_)
	  }
	}
	
	override def doPost(req: HttpServletRequest, resp: HttpServletResponse) = {
	  val reqBody = getRequestBody(req)
	  val reqXML = XML.loadString(reqBody)
	  
	  val xml = XML.loadString((reqXML \\ "xml").text)
	  val xpath = (reqXML \\ "xpath").text
	  
	  val result = <Output>{XPathResultToData(XPath(xml, xpath))}</Output>
	  
	  resp.setContentType("application/xml")
	  resp.getWriter().print(result)
	}	
}