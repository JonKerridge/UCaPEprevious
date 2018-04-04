package c15.net
// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel
import jcsp.lang.*
import groovyJCSP.*
import jcsp.net2.*
import jcsp.net2.tcpip.*
import jcsp.userIO.*

import c12.canteen.*

def chefNodeIP = "127.0.0.1"
def canteenNodeIP = "127.0.0.2"
def philosopherNodeIP = "127.0.0.3"

def canteenNodeAddr = new TCPIPNodeAddress(canteenNodeIP, 3000)
Node.getInstance().init (canteenNodeAddr)
def cooked = NetChannel.net2one() 
println "cooked location = ${cooked.getLocation()}"

def getOne = NetChannel.net2one()
println "getOne location = ${getOne.getLocation()}"

getOne.read()  // signal from the philosophers
def philosopherAddr = new TCPIPNodeAddress (philosopherNodeIP, 3002)
def gotOne = NetChannel.one2net(philosopherAddr, 50)

def processList = [
  new ClockedQueuingServery(service:getOne, deliver:gotOne, supply:cooked)
  ]
new PAR ( processList ).run()     
