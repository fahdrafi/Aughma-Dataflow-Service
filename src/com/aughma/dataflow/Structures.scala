package com.aughma.dataflow

import java.util.UUID

trait Datum {
  val _id: UUID = UUID.randomUUID
}

trait DataSource {
  def Start
  val Target: Pipeline
}

trait Node {
  def Process(datum: Datum) : Seq[Datum]
}

trait Adaptor {
  val ProcessingNode: Node
  val Outputs: Seq[Adaptor]
  def Post(datum: Datum)
}

trait Pipeline extends Node