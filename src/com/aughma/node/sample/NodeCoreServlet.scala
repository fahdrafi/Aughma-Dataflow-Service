package com.aughma.node.sample

import java.io.IOException
import javax.servlet.http._
import scala.xml
import scala.xml.Source
import scala.xml.Elem
import scala.xml.XML
import scala.io.Codec

class NodeCoreServlet extends HttpServlet {
	override def doGet(req: HttpServletRequest, resp: HttpServletResponse) = {
	  val nodeDescription = 
		<NodeCore>
			  <Stateful>false</Stateful>
			  <Description>
			  	This node takes a url as an input and maps it onto the url response.
			  	It works as a url fetcher.
			  </Description>
			  <Input type="string" name="url" />
			  <Output type="string" name="data" />
		</NodeCore>
	  
		resp.setContentType("application/xml")
		resp.getWriter().print(nodeDescription)
	}
	
	def getRequestBody(req: HttpServletRequest) = {
	  val jb = new StringBuffer()
	  val reader = req.getReader()
	  var line = reader.readLine()
	  while (line != null) {
	    jb.append(line)
	    line = reader.readLine()
	  }
	  jb.toString()
	}
	
	override def doPost(req: HttpServletRequest, resp: HttpServletResponse) = {
	  val reqBody = getRequestBody(req)
	  val reqXML = XML.loadString(reqBody)
	  
	  val url = (reqXML \\ "url").text
	  val data = io.Source.fromURL(url)(Codec.UTF8).mkString
	  
	  resp.setContentType("application/xml")
	  resp.getWriter().print(
		<Output>
			  <data>{data}</data>
		</Output>
	  )
	}
}
