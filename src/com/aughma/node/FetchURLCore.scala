package com.aughma.node

import javax.servlet.http._
import scala.xml._
import scala.io.Codec


class FetchURLCore extends NodeCore {
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
