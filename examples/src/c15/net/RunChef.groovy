package c15.net
// copyright 2012-18 Jon Kerridge
// Using Concurrency and Parallelism Effectively parts i & ii, 2014, bookboon.com
import jcsp.lang.*
import groovyJCSP.*
import jcsp.net2.*
import jcsp.net2.tcpip.*
import jcsp.userIO.*

import c12.canteen.*

def chefNodeIP = "127.0.0.1"
def canteenNodeIP = "127.0.0.2"
def philosopherNodeIP = "127.0.0.3"

def TCPIPNodeAddress chefNodeAddr = new TCPIPNodeAddress(chefNodeIP,3003)
Node.getInstance().init (chefNodeAddr)
def  canteenAddress = new TCPIPNodeAddress(canteenNodeIP,3000)
def cooked = NetChannel.one2net(canteenAddress, 50)
println "cooked location = ${cooked.getLocation()}"

def processList = [ new Kitchen ( supply: cooked) ]
new PAR ( processList ).run()     
