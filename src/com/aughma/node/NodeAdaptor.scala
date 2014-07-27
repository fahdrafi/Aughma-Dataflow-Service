package com.aughma.node

import javax.servlet.http._
import scala.xml._
import com.google.appengine.api.taskqueue._
import com.google.appengine.api.taskqueue.TaskOptions.Builder._

/**
 * Node Adaptor is responsible for:
 * 1. Accepting an input pushed to it.
 * 2. Storing it in a queue
 * 3. Managing execution according to the defined parameters (parallelism etc)
 * 4. Connecting the output to another Node adaptor (if need be) 
 */
trait NodeAdaptor extends AughmaServlet {
	val description: NodeSeq
	def ReceiveInput(input: NodeSeq): NodeSeq = {
		val queue = QueueFactory.getDefaultQueue();
        queue.add(withUrl("/pathToNodeCore").param("key", "value"));
        <InputReceived/>
	}
	
	def ForwardOutput(output: NodeSeq): NodeSeq = {
	  
	  <OutputForwarded/>
	}
	
	final override def doGet(req: HttpServletRequest, resp: HttpServletResponse) = {
      assert(!description.isEmpty, "Must provide description for AughmaServlet: "+ this.getClass().getName())
      
      resp.setContentType("application/xml")
      resp.getWriter().print(description)
    }

	final override def doPost(req: HttpServletRequest, resp: HttpServletResponse) = {
		val requestBody = XML.loadString(getRequestBody(req))
		
		resp.setContentType("application/xml")
		resp.getWriter().print(ReceiveMessage(requestBody))
	}
}
