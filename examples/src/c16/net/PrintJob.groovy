package c16.net
 
// copyright 2012-18 Jon Kerridge
// Using Concurrency and Parallelism Effectively parts i & ii, 2014, bookboon.com

import jcsp.lang.*
import groovyJCSP.*
import jcsp.net2.*

class PrintJob  implements Serializable{
	
  def int userId
  def NetChannelLocation useLocation
}
