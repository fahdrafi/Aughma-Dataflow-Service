package com.aughma.impl.google.cloud

import javax.servlet.http._

trait ExtendedServlet extends HttpServlet {
  	protected def getRequestBody(req: HttpServletRequest) = {
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