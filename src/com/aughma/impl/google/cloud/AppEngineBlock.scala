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
import com.aughma.dataflow._

trait AppEngineBlock extends ExtendedServlet with Block {
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