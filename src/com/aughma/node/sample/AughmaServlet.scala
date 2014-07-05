package com.aughma.node.sample

import javax.servlet.http._
import scala.xml._

trait AughmaServlet extends HttpServlet {
    val description: NodeSeq
    
	final override def doGet(req: HttpServletRequest, resp: HttpServletResponse) = {
      assert(!description.isEmpty, "Must provide description for AughmaServlet: "+this.getClass().getName())
      
      resp.setContentType("application/xml")
      resp.getWriter().print(description)
    }
    
    def ProcessInput(input: NodeSeq): NodeSeq
    
    final override def doPost(req: HttpServletRequest, resp: HttpServletResponse) = {
      val requestBody = XML.loadString(getRequestBody(req))
      
      resp.setContentType("application/xml")
      resp.getWriter().print(ProcessInput(requestBody))
    }
      
	private def getRequestBody(req: HttpServletRequest) = {
	  val jb = new StringBuffer()
	  val reader = req.getReader()
	  var line = reader.readLine()
	  while (line != null) {
	    jb.append(line)
	    line = reader.readLine()
	  }
	  jb.toString()
	}
}