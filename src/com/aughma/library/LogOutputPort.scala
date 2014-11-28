package com.aughma.library

import com.aughma.dataflow.OutputPort
import java.util.logging.Logger
import java.util.logging.Level

class LogOutputPort(
    loggerName: String = "DefaultLogger",
    loggerLevel: Level = Level.INFO
    ) extends OutputPort {
  val linkedPorts = List.empty
  def Post(message: String) = {
    val logger = Logger.getLogger(loggerName)
    logger.log(loggerLevel, message)
  }
}