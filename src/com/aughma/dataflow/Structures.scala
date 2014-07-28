package com.aughma.dataflow

import java.util.UUID
import scala.xml.Node

trait Datum extends Serializable {
  val _id: UUID
  def toXml: Node
  val fromXml: Node => Datum
}

trait DataSource {
  def Start
  val Target: Pipeline
}

trait ProcessingNode {
  def Process(datum: Datum) : Seq[Datum]
}

trait Adaptor {
  val ProcessingNode: ProcessingNode
  val Outputs: Seq[Adaptor]
  def Post(datum: Datum)
}

trait Pipeline extends ProcessingNode