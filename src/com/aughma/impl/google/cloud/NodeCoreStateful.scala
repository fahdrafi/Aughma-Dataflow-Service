package com.aughma.impl.google.cloud

import scala.xml.NodeSeq

trait NodeCoreStateful extends NodeCore {
  def GetState : Map[String, String] 
}