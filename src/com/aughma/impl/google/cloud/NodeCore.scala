package com.aughma.impl.google.cloud

import javax.servlet.http._
import scala.xml._
import com.aughma.dataflow._

trait AEProcessingNode extends ExtendedServlet with ProcessingNode {
    val description: NodeSeq
    def ProcessInput(input: NodeSeq): NodeSeq
    
	final override def doGet(req: HttpServletRequest, resp: HttpServletResponse) = {
      assert(!description.isEmpty, "Must provide description for AughmaServlet: "+ this.getClass().getName())
      
      resp.setContentType("application/xml")
      resp.getWriter().print(description)
    }
    
    final override def doPost(req: HttpServletRequest, resp: HttpServletResponse) = {
      val requestBody = XML.loadString(getRequestBody(req))
      
      resp.setContentType("application/xml")
      resp.getWriter().print(ProcessInput(requestBody))
    }
}