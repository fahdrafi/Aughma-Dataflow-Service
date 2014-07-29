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

package com.aughma.dataflow.impl.google

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