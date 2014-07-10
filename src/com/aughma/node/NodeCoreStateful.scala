package com.aughma.node

import scala.xml.NodeSeq

trait NodeCoreStateful extends NodeCore {
  def GetState : Map[String, String] 
}