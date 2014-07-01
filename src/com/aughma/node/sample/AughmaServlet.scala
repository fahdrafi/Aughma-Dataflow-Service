package com.aughma.node.sample

import javax.servlet.http._

trait AughmaServlet extends HttpServlet {
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
}