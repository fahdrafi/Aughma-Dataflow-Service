package com.aughma.appengine

import javax.servlet.http.HttpServlet
import com.google.appengine.api.datastore._

object GAEQueueService {
  val datastore = DatastoreServiceFactory.getDatastoreService()
  
  def createQueue = {
    val entity = new Entity("Queue")
    datastore.put(entity).toString
  }

  def enqueue(queueId: String, value: String) = {
    val queue = datastore.get(KeyFactory.createKey("Queue", queueId))
    
  }
  
}