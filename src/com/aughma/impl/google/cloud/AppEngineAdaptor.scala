/*
 * Copyright 2014 Aughma Limited
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.aughma.impl.google.cloud
 
import javax.servlet.http._
import scala.xml._
import com.google.appengine.api.taskqueue._
import com.google.appengine.api.taskqueue.TaskOptions.Builder._
import com.aughma.dataflow.Adaptor

/**
 * Node Adaptor is responsible for:
 * 1. Accepting an input pushed to it.
 * 2. Storing it in a queue
 * 3. Managing execution according to the defined parameters (parallelism etc)
 * 4. Connecting the output to another Node adaptor (if need be) 
 */
trait AppEngineAdaptor extends ExtendedServlet with Adaptor {
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
		resp.getWriter().print(ReceiveInput(requestBody))
	}
}
