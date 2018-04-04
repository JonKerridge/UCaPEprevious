package c15.net
// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel
import jcsp.lang.*
import groovyJCSP.*
import jcsp.net2.*
import jcsp.net2.tcpip.*
import jcsp.userIO.*

def getNodeIP = "127.0.0.2"

def getNodeAddr = new TCPIPNodeAddress(getNodeIP, 3000)
Node.getInstance().init (getNodeAddr)

def comms = NetChannel.net2one()
def pList = [ new Get ( inChannel: comms , id: 0 ) ]

new PAR ( pList ).run()
