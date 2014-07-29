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

package com.aughma.dataflow.library.blocks

import javax.servlet.http._
import scala.xml._
import scala.io.Codec
import com.aughma.dataflow.impl.google.AppEngineBlock


class FetchURLBlock extends AppEngineBlock {
  val description =
		<NodeCore>
		  <Stateful>false</Stateful>
		  <Description>
		  	This node takes a url as an input and maps it onto the url response.
		  	It works as a url fetcher.
		  </Description>
		  <Input type="string" name="url" />
		  <Output type="string" name="data" />
		</NodeCore>

	override def ProcessInput(input: NodeSeq): NodeSeq = {
	  val url = (input \\ "url").text
	  val data = io.Source.fromURL(url)(Codec.UTF8).mkString
	  <Output>
	  	<data>{data}</data>
	  </Output>
  	}
    
}
