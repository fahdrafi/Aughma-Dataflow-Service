package com.aughma.xml.xpath

import scala.xml._

sealed trait XPathResult {
  def toNodeSeq : NodeSeq
}
case class NodeSeqResult(nodeSeq: NodeSeq) extends XPathResult {
  def toNodeSeq = NodeSeq.fromSeq(nodeSeq.map(n => <data>{n.toString}</data>))
}
case class StringResult(string: String) extends XPathResult {
  def toNodeSeq = <data>{string}</data>
}
case class StringSeqResult(stringSeq: Seq[String]) extends XPathResult {
  def toNodeSeq = NodeSeq.fromSeq(stringSeq.map(s => <data>{s}</data>))
}
case class XPathResultSeq(xPathResultSeq: Seq[XPathResult]) extends XPathResult {
  def toNodeSeq = xPathResultSeq.map(_.toNodeSeq).fold(NodeSeq.Empty)(_++_)
}

case class XPath(xPath: String) {
    def find(xmlNode: Node) : XPathResult = {
      xPath match {
        case "" => NodeSeqResult(NodeSeq.Empty)
        case "/text()" => StringResult(xmlNode.text)
        case p if p.startsWith("//") && !p.substring(2).contains('/') => NodeSeqResult(xmlNode \\ p.substring(2))
        case p if p.startsWith("/") && !p.substring(1).contains('/') => NodeSeqResult(xmlNode \ p.substring(1))
        case p if p.startsWith("//") => XPath(p.substring(p.indexOf('/', 2))).find(xmlNode \\ p.substring(2, p.indexOf('/', 2)))
        case p if p.startsWith("/") => XPath(p.substring(p.indexOf('/', 1))).find(xmlNode \ p.substring(1, p.indexOf('/', 1)))
        case p if xmlNode.isInstanceOf[Node] && p == xmlNode.asInstanceOf[Node].label => NodeSeqResult(xmlNode)
        case p => throw new Exception("xPath \""+ p + "\" string start with '/' or '//'")
      }
	}
	
	def find(xmlSeq: NodeSeq) : XPathResult = {
	  xmlSeq match {
	    case x: Node => throw new Exception("This call should have gone to the other implementation of XPath")
	    case x => XPathResultSeq(xmlSeq.theSeq.map(n => find(n)))
	  }
	}
	
}