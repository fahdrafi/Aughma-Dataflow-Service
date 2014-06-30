package com.aughma.node.sample

import java.io.IOException
import javax.servlet.http._
import scala.xml

class NodeCoreServlet extends HttpServlet {
	override def doGet(req: HttpServletRequest, resp: HttpServletResponse) = {
	  
	  val nodeDescription = 
		<NodeDescription>
			  <Stateful>false</Stateful>
			  <Inputs>
			  	<Input type="string">url</Input>
			  </Inputs>
			  <Outputs>
			  	<Output type="string">data</Output>
			  </Outputs>
		</NodeDescription>
	  
		resp.setContentType("application/xml")
		resp.getWriter().print(nodeDescription)
	}
}
